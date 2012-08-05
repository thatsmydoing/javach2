package edu.ateneo.javach.features;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.ateneo.javach.parser.Context;
import edu.ateneo.javach.parser.ContextFinder;
import edu.ateneo.javach.parser.Diagnostic;
import edu.ateneo.javach.parser.SourceParser;
import edu.ateneo.javach.parser.TopDownContextFinder;

public class FeatureSet {
  public final String fileName;
  public final int errorCount;
  public final Context context;
  public final Context outerContext;
  public final List<ErrorFeature> errors;

  FeatureSet(String fileName, int errorCount, Context context, Context outerContext) {
    this.fileName = fileName;
    this.errorCount = errorCount;
    this.context = context;
    this.outerContext = outerContext;
    this.errors = new LinkedList<ErrorFeature>();
  }

  void addErrorFeature(ErrorFeature ef) {
    errors.add(ef);
  }

  public static FeatureSet extractFeatures(List<Diagnostic> diagnostics) {
    try {
      if(diagnostics.size() > 0) {
        File f = new File(diagnostics.get(0).fileName);
        Scanner fileScanner = new Scanner(f);
        fileScanner.useDelimiter("\\Z");
        SourceParser sp = new SourceParser(fileScanner.next());
        ContextFinder scf = new TopDownContextFinder(sp);

        Diagnostic d = diagnostics.get(0);
        FeatureSet fs = new FeatureSet(d.fileName, diagnostics.size(), scf.getContext(d.lineNumber, d.columnNumber), scf.getPreviousContext());
        int prevIndex = 0;
        for(Diagnostic ds : diagnostics) {
          sp.goTo(ds.lineNumber, ds.columnNumber);
          int offset = -10000;
          int currChar = sp.currChar();
          String charType = (int)currChar + "";
          if(prevIndex > 0) {
            offset = sp.getIndex() - prevIndex;
            prevIndex = sp.getIndex();
          }
          if(Character.isLetter(currChar)) charType = "letter";
          if(Character.isDigit(currChar)) charType = "digit";
          FeatureSet.ErrorFeature ef = fs.new ErrorFeature(ds.errorClass, sp.currChar(), charType, offset, ds.strings);
          fs.addErrorFeature(ef);
        }
        return fs;
      }
      return null;
    }
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public class ErrorFeature {
    public final String error;
    public final char character;
    public final String characterType;
    public final int offset;
    public final Map<String, String> strings;

    ErrorFeature(String error, char character, String characterType, int offset, Map<String, String> strings) {
      this.error = error;
      this.character = character;
      this.characterType = characterType;
      this.offset = offset;
      this.strings = strings;
    }
  }

}