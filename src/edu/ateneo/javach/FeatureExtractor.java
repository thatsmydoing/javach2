package edu.ateneo.javach;

import au.com.bytecode.opencsv.CSVWriter;
import edu.ateneo.javach.parser.*;

import java.io.*;
import java.util.*;

public class FeatureExtractor {
  public static final int NUM_ERRORS = 3;
  public static final int FEATURES_PER_ERROR = 4;
  public static final int EXTRA_FEATURES = 4;
  public static final int NUM_COLS = 1 + EXTRA_FEATURES + NUM_ERRORS * FEATURES_PER_ERROR;
  public static String[] headers;
  static {
    headers = new String[NUM_COLS];
    headers[EXTRA_FEATURES-4] = "fileName";
    headers[EXTRA_FEATURES-3] = "errorCount";
    headers[EXTRA_FEATURES-2] = "context";
    headers[EXTRA_FEATURES-1] = "outerContext";
    headers[EXTRA_FEATURES] = "error1";
    headers[EXTRA_FEATURES+1] = "charAtError1";
    headers[EXTRA_FEATURES+2] = "charTypeAtError1";
    for(int i = 1; i < NUM_ERRORS; i++) {
      headers[i*FEATURES_PER_ERROR+EXTRA_FEATURES-1] = "error" + (i+1);
      headers[i*FEATURES_PER_ERROR+EXTRA_FEATURES] = "charAtError" + (i+1);
      headers[i*FEATURES_PER_ERROR+EXTRA_FEATURES+1] = "charTypeAtError" + (i+1);
      headers[i*FEATURES_PER_ERROR+EXTRA_FEATURES+2] = "offsetFromError" + i;
    }
    headers[NUM_COLS-2] = "specificClass";
    headers[NUM_COLS-1] = "generalClass";
  }

  public static void main(String[] args) {

    try {
      long totalTime = System.currentTimeMillis();
      FeatureExtractor fe = new FeatureExtractor(args[0]);
      fe.start();

      totalTime = System.currentTimeMillis() - totalTime;
      System.out.printf("Feature extraction took: %d.%d seconds\n", totalTime / 1000, totalTime % 1000);
    }
    catch (IOException ioe) {
      System.out.println("Could not write to file");
    }
  }

  final File rootDir;
  CSVWriter writer;

  public FeatureExtractor(String rootDir) {
    this.rootDir = new File(rootDir);
  }

  public void start() throws IOException {
    File[] files = rootDir.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.endsWith(".java");
      }
    });

    String lastClass = "";
    for(File f : files) {
      String currClass = f.getName();
      currClass = currClass.substring(0, currClass.indexOf("_"));
      if(!currClass.equals(lastClass)) {
        lastClass = currClass;
        setupWriter(currClass+".csv");
      }
      String[] toWrite = processFile(f);
      if(toWrite != null) writer.writeNext(toWrite);
    }
  }

  public void setupWriter(String fileName) throws IOException {
    if(writer != null) writer.close();
    writer = new CSVWriter(new FileWriter(fileName));
    writer.writeNext(headers);
  }

  public String[] processFile(File f) {
    try {
      System.out.printf("Processing %-60s", f.getName() + "...");
      String[] params = new String[2];
      params[0] = "javac";
      params[1] = f.getPath();
      Process p = Runtime.getRuntime().exec(params);
      Scanner sc = new Scanner(p.getErrorStream());

      DiagnosticParser dp = new DiagnosticParser();
      while (sc.hasNextLine()) {
        dp.parse(sc.nextLine());
      }

      Scanner fileScanner = new Scanner(f);
      fileScanner.useDelimiter("\\Z");
      SourceParser sp = new SourceParser(fileScanner.next());
      ContextFinder scf = new TopDownContextFinder(sp);

      String[] output = new String[NUM_COLS];

      String basename = f.getName();
      basename = basename.substring(0, basename.lastIndexOf("_"));
      output[NUM_COLS-2] = basename;
      output[NUM_COLS-1] = basename.substring(basename.indexOf("_")+1);

      List<Diagnostic> diagnostics = dp.getDiagnostics();
      if(diagnostics.size() > 0) {
        Diagnostic d = diagnostics.get(0);
        sp.goTo(d.lineNumber, d.columnNumber);
        int lastIndex = sp.getIndex();
        output[EXTRA_FEATURES-4] = f.getName();
        output[EXTRA_FEATURES-3] = diagnostics.size() + "";
        output[EXTRA_FEATURES+2] = getErrorCharType(sp.currChar());
        output[EXTRA_FEATURES+1] = (int)sp.currChar() + "";
        output[EXTRA_FEATURES-2] = scf.getContext(d.lineNumber, d.columnNumber).toString();
        output[EXTRA_FEATURES-1] = scf.getPreviousContext().toString();
        output[EXTRA_FEATURES] = d.errorClass;

        for(int i = 1; i < NUM_ERRORS; i++) {
          int offset = -10000;
          String errorMessage = "none";
          String errorChar = "none";
          String errorType = "none";

          if(i < diagnostics.size()) {
            d = diagnostics.get(i);
            sp.goTo(d.lineNumber, d.columnNumber);
            offset = sp.getIndex() - lastIndex;
            lastIndex = sp.getIndex();
            errorMessage = d.errorClass;
            errorChar = (int)sp.currChar() + "";
            errorType = getErrorCharType(sp.currChar());
          }

          output[i*FEATURES_PER_ERROR+EXTRA_FEATURES-1] = errorMessage;
          output[i*FEATURES_PER_ERROR+EXTRA_FEATURES] = errorChar;
          output[i*FEATURES_PER_ERROR+EXTRA_FEATURES+1] = errorType;
          output[i*FEATURES_PER_ERROR+EXTRA_FEATURES+2] = offset + "";
        }
      }
      System.out.println("OK");
      return output;
    }
    catch(Exception e) {
      e.printStackTrace();
      System.out.println("FAIL");
    }
    return null;
  }

  static String getErrorCharType(char input) {
    if(Character.isLetter(input)) return "letter";
    if(Character.isDigit(input)) return "digit";
    return (int)input + "";
  }
}

