package edu.ateneo.javach.parser;

import java.util.Map;

public class Diagnostic {
  public final int lineNumber;
  public final int columnNumber;
  public final String fileName;
  public final String message;
  public final String errorClass;
  public final Map<String, String> strings;   
  
  Diagnostic(int lineNumber, int columnNumber, String fileName, String message, String errorClass, Map<String, String> strings) {
    this.lineNumber = lineNumber;
    this.columnNumber = columnNumber;
    this.fileName = fileName;
    this.message = message;
    this.errorClass = errorClass;
    this.strings = strings;
  }
  
  public void setString(String name, String value) {
    strings.put(name, value);
  }
  
  public String getString(String name) {
    return strings.get(name);
  }

  @Override
  public String toString() {
    return String.format("%s:%d:%d\n%s", fileName, lineNumber, columnNumber, message);
  }
}