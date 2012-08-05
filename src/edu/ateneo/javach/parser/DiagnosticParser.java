package edu.ateneo.javach.parser;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DiagnosticParser {
  private ArrayList<Diagnostic> diagnostics;

  private int state;

  /* Temporary variables */
  private String line;
  private int lineNumber;
  private int columnNumber;
  private String fileName;
  private String message;
  private String errorClass;
  private HashMap<String, String> strings;

  public DiagnosticParser() {
    diagnostics = new ArrayList<Diagnostic>();
  }
  
  public void parseFile(File f) {
    try {
      String[] params = new String[2];
      params[0] = "javac";
      params[1] = f.getPath();
      Process p = Runtime.getRuntime().exec(params);
      Scanner sc = new Scanner(p.getErrorStream());
      
      while (sc.hasNextLine()) {
          this.parse(sc.nextLine());
      }
    }
    catch (IOException e) {
      
    }
  }

  public void parse(String line) {
    this.line = line;
    switch (state) {
      case 0:
        firstLine();
        break;
      case 1:
        secondLine();
        break;
      case 2:
        thirdLine();
        break;
      case 3:
        state = 4;
        break;
      case 4:
        lastLine();
        break;
    }
  }

  void firstLine() {
    strings = new HashMap<String, String>();
    if (line.matches("\\d+ errors?")) return;
    int pos = line.indexOf(":");
    if(pos == 1) {
      pos = line.indexOf(":", pos+1);
    }
    if(pos < 0) return;
    fileName = line.substring(0, pos);
    lineNumber = Integer.parseInt(line.substring(pos + 1, line.indexOf(":", pos + 1)));
    pos = line.indexOf(":", pos + 1) + 2;
    message = line.substring(pos);

    if (message.equals("incompatible types") || message.equals("cannot find symbol")) {
      state = 1;
    }
    else {
      state = 3;
    }
  }

  void secondLine() {
    message += "\n" + line;
    state = 2;
  }

  void thirdLine() {
    message += "\n" + line;
    state = 3;
  }

  void lastLine() {
    columnNumber = line.indexOf("^") + 1;
    setErrorClass(message);
    diagnostics.add(new Diagnostic(lineNumber, columnNumber, fileName, message, errorClass, strings));
    state = 0;
  }

  public List<Diagnostic> getDiagnostics() {
    return diagnostics;
  }

  void setErrorClass(String input) {
    if(input.indexOf("cannot be applied to") >= 0) {
      errorClass = "cannot be applied to";
      Scanner sc = new Scanner(input);
      strings.put("method", sc.next()); // method
      sc.next(); // in
      sc.next(); // classname
      sc.next(); // cannot
      sc.next(); // be
      sc.next(); // applied
      sc.next(); // to
      strings.put("parameters", sc.next()); // parameter list
    }
    else if(input.startsWith("incompatible types")) {
      errorClass = "incompatible types";
      Scanner sc = new Scanner(input);
      sc.next(); // incompatible
      sc.next(); // types
      sc.next(); // found
      sc.next(); // :
      strings.put("found", sc.next()); // type
      sc.next(); // required:
      strings.put("expected", sc.next()); // type
    }
    else if(input.startsWith("cannot find symbol")) {
      Scanner sc = new Scanner(input);
      sc.next(); // cannot
      sc.next(); // find
      sc.next(); // symbol
      sc.next(); // symbol
      sc.next(); // :
      String type = sc.next(); // variable, method or class
      strings.put("type", type);
      errorClass = "cannot find symbol - " + type;
      strings.put("identifier", sc.next()); // identifier
    }
    else {
      errorClass = input;
    }
  }
}

