package edu.ateneo.javach;

import java.io.*;
import java.util.*;

import edu.ateneo.javach.parser.*;

public class ParserTester {
  public static void main(String[] args) throws Exception {
    String[] params = new String[args.length + 1];
    params[0] = "javac";
    for (int i = 0; i < args.length; i++) {
      params[i + 1] = args[i];
    }
    try {
      DiagnosticParser parser = new DiagnosticParser();
      Runtime rt = Runtime.getRuntime();
      Process p = rt.exec(params);
      Scanner sc = new Scanner(p.getErrorStream());

      while (sc.hasNextLine()) {
        parser.parse(sc.nextLine());
      }
      for (Diagnostic d : parser.getDiagnostics()) {
        System.out.println(d);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }
}