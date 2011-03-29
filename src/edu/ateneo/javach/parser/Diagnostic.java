package edu.ateneo.javach.parser;

public class Diagnostic {
  public final int lineNumber;
  public final int columnNumber;
  public final String fileName;
  public final String message;
  public final String errorClass;

  Diagnostic(int lineNumber, int columnNumber, String fileName, String message, String errorClass) {
    this.lineNumber = lineNumber;
    this.columnNumber = columnNumber;
    this.fileName = fileName;
    this.message = message;
    this.errorClass = errorClass;
  }

  @Override
  public String toString() {
    return String.format("%s:%d:%d\n%s", fileName, lineNumber, columnNumber, message);
  }
}