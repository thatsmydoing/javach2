package edu.ateneo.javach;

import au.com.bytecode.opencsv.CSVWriter;
import edu.ateneo.javach.features.FeatureSet;

import java.io.*;
import java.util.Arrays;

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
    Arrays.sort(files);
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
      FeatureSet fs = edu.ateneo.javach.features.FeatureSet.extractFeature(f);
      
      String[] output = new String[NUM_COLS];

      String basename = f.getName();
      basename = basename.substring(0, basename.lastIndexOf("_"));
      output[NUM_COLS-2] = basename;
      output[NUM_COLS-1] = basename.substring(basename.indexOf("_")+1);

      if(fs != null) {
        output[EXTRA_FEATURES-4] = f.getName();
        output[EXTRA_FEATURES-3] = fs.errorCount + "";
        output[EXTRA_FEATURES+2] = fs.errors.get(0).characterType;
        output[EXTRA_FEATURES+1] = fs.errors.get(0).character + "";
        output[EXTRA_FEATURES-2] = fs.context.toString();
        output[EXTRA_FEATURES-1] = fs.outerContext.toString();
        output[EXTRA_FEATURES] = fs.errors.get(0).error;

        for(int i = 1; i < NUM_ERRORS; i++) {
          int offset = -10000;
          String errorMessage = "none";
          String errorChar = "none";
          String errorType = "none";

          if(i < fs.errors.size()) {
            errorMessage = fs.errors.get(i).error;
            errorChar = fs.errors.get(i).character + "";
            errorType = fs.errors.get(i).characterType;
            offset = fs.errors.get(i).offset;
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
}
