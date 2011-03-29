package edu.ateneo.javach.parser;

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

  public DiagnosticParser() {
    diagnostics = new ArrayList<Diagnostic>();
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
    if (line.matches("\\d+ errors?")) return;
    int pos = line.indexOf(":");
    if(pos == 1) {
      pos = line.indexOf(":", pos+1);
    }
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
    diagnostics.add(new Diagnostic(lineNumber, columnNumber, fileName, message, errorClass));
    state = 0;
  }

  public List<Diagnostic> getDiagnostics() {
    return diagnostics;
  }

  void setErrorClass(String input) {
    if(input.indexOf("cannot be applied to") >= 0) {
      errorClass = "cannot be applied to";
    }
    else if(input.startsWith("incompatible types")) {
      errorClass = "incompatible types";
    }
    else if(input.startsWith("cannot find symbol")) {
      Scanner sc = new Scanner(input);
      sc.next(); // cannot
      sc.next(); // find
      sc.next(); // symbol
      sc.next(); // symbol
      sc.next(); // :
      String type = sc.next(); // variable, method or class
      errorClass = "cannot find symbol - " + type;
    }
    else {
      errorClass = input;
    }
  }
}

