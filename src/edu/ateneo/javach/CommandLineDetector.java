package edu.ateneo.javach;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.ateneo.javach.detector.Detector;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.DiagnosticParser;

public class CommandLineDetector {
  public static void main(String[] args) {
    System.out.print(args[0]);
    DiagnosticParser dp = new DiagnosticParser();
    dp.parseFile(new File(args[0]));
    ArrayList<FeatureSet> fs = new ArrayList<FeatureSet>(); 
    fs.add(FeatureSet.extractFeatures(dp.getDiagnostics()));
    Detector d = new Detector();
    d.loadHandlers();
    List<String> errors = d.getMessages(fs);
    if(errors.size() > 0) {
      System.out.printf(" : %s%n", errors.get(0));
    }
    else {
      System.out.println(" error");
    }
  }
}
