package edu.ateneo.javach.parser;

import java.util.*;

public class SourceParser {
  private String code;
  private int currLineNumber;
  private int currColumnNumber;
  private int index;
  private char lastChar;
  private List<Integer> lineLengths;
  private Map<String, Integer> positions;

  private static Set<Character> specialChars;

  static {
    specialChars = new HashSet<Character>();
    specialChars.add('{');
    specialChars.add('}');
    specialChars.add('(');
    specialChars.add(')');
    specialChars.add('[');
    specialChars.add(']');
    specialChars.add('?');
    specialChars.add(':');
    specialChars.add(',');
    specialChars.add(';');
    specialChars.add('"');
    specialChars.add('\'');
    specialChars.add('@');
  }

  static boolean isSpecialChar(char c) {
    return specialChars.contains(c);
  }

  private static Set<Character> specialWordChars;

  static {
    specialWordChars = new HashSet<Character>();
    specialWordChars.add('=');
    specialWordChars.add('+');
    specialWordChars.add('-');
    specialWordChars.add('*');
    specialWordChars.add('/');
    specialWordChars.add('%');
    specialWordChars.add('|');
    specialWordChars.add('&');
    specialWordChars.add('^');
    specialWordChars.add('!');
    specialWordChars.add('<');
    specialWordChars.add('>');
    specialWordChars.add('\\');
  }

  static boolean isSpecialWordChar(char c) {
    return specialWordChars.contains(c);
  }

  static boolean isWordChar(char c) {
    return Character.isJavaIdentifierPart(c) || c == '.';
  }

  public SourceParser(String code) {
    this.code = code;
    reset();
    positions = new HashMap<String, Integer>();
    lineLengths = new ArrayList<Integer>();
    lineLengths.add(0);
  }

  void reset() {
    currLineNumber = 1;
    currColumnNumber = 1;
    index = 0;
    lastChar = 0;
  }

  public int getIndex() {
    return index;
  }

  public int getLineNumber() {
    return currLineNumber;
  }

  public int getColumnNumber() {
    return currColumnNumber;
  }

  public char goTo(int lineNumber, int columnNumber) {
    reset();
    int min = Math.min(lineNumber, lineLengths.size());
    while (currLineNumber < min) {
      index += lineLengths.get(currLineNumber++);
    }
    while (currLineNumber < lineNumber && !isEOF()) {
      nextChar();
    }
    for (int i = 1; i < columnNumber && !isEOF(); i++) {
      nextChar();
    }
    if (lineNumber != currLineNumber) {
      return 0;
    }
    return 1;
  }

  public boolean isStart() {
    return index < 0;
  }

  public boolean isEOF() {
    return index >= code.length();
  }

  public char nextChar() {
    if(index < 0) {
      lastChar = 0;
    }
    else if (isEOF()) return 0;
    else {
      lastChar = code.charAt(index);
    }
    if (lastChar == '\n') {
      if (lineLengths.size() == currLineNumber) {
        lineLengths.add(currColumnNumber);
      }
      currColumnNumber = 1;
      currLineNumber++;
    }
    else {
      ++currColumnNumber;
    }
    ++index;
    if (isEOF()) return 0;
    lastChar = code.charAt(index);
    return lastChar;
  }

  public char currChar() {
    if (isStart() || isEOF()) return 0;
    return code.charAt(index);
  }

  public char prevChar() {
    --index;
    --currColumnNumber;
    if (isStart()) return 0;
    char curr = code.charAt(index);
    if (curr == '\n') {
      currLineNumber--;
      currColumnNumber = lineLengths.get(currLineNumber);
    }
    return curr;
  }

  void goToStartOfWord() {
    char c = currChar();
    if (c == 0) {
      prevChar();
    }
    else if (Character.isWhitespace(c)) {
      while (!isEOF() && Character.isWhitespace(nextChar())) ;
    }
    else if (isSpecialWordChar(c)) {
      while (!isStart() && isSpecialWordChar(prevChar())) ;
      nextChar();
    }
    else if (isWordChar(c)) {
      while (!isStart() && isWordChar(prevChar())) ;
      nextChar();
    }
  }

  void goToEndOfWord() {
    char c = currChar();
    if (Character.isWhitespace(c)) {
      while (!isStart() && Character.isWhitespace(prevChar())) ;
      nextChar();
    }
    else if (isSpecialWordChar(c)) {
      while (!isEOF() && isSpecialWordChar(nextChar())) ;
    }
    else if (isWordChar(c)) {
      while (!isEOF() && isWordChar(nextChar())) ;
    }
    else if (!isEOF()) {
      nextChar();
    }
  }

  public String nextWord() {
    goToStartOfWord();
    if (isEOF()) return null;
    int start = index;
    goToEndOfWord();
    if(index < start) return null;
    if(index >= code.length()) return null;
    return code.substring(start, index);
  }

  String prevWord() {
    goToEndOfWord();
    if (isStart()) return null;
    int end = index;
    prevChar();
    goToStartOfWord();
    int start = index;
    prevChar();
    return code.substring(start, end);
  }

  String nextGroup() {
    return nextGroup(true);
  }

  String nextGroup(boolean alsoParenthesis) {
    String nextWord = nextWord();
    if (nextWord == null) return " ";
    if (nextWord.equals("(") && alsoParenthesis) {
      int counter = 0;
      while (counter >= 0 && !isEOF()) {
        String word = nextWord();
        if (word == null) return " ";
        if (word.equals("(")) counter++;
        else if (word.equals(")")) counter--;
        nextWord += word;
      }
    }
    if (nextWord.equals("\"")) {
      StringBuilder sb = new StringBuilder();
      boolean slash = false;
      while (!isEOF()) {
        char c = currChar();
        if (c == 0) break;
        sb.append(c);
        if (c == '\n') {
          sb.deleteCharAt(sb.length() - 1);
          break;
        }
        else if (slash) {
          slash = false;
        }
        else if (c == '\\') {
          slash = true;
        }
        else if (c == '"') {
          break;
        }
        nextChar();
      }
      nextChar();
      nextWord += sb.toString();
    }
    while (nextWord.startsWith("/*")) {
      char lastChar = 0;
      while(!isEOF()) {
        char c = nextChar();
        if(c == 0) break;
        if(lastChar == '*' && c == '/') {
          break;
        }
        lastChar = c;
      }
      nextWord = nextWord();
    }
    while (nextWord.startsWith("//")) {
      while(true) {
        char c = nextChar();
        if(c == 0) break;
        if(c == '\n') break;
      }
      nextWord = nextWord();
    }
    return nextWord;
  }

  String prevGroup() {
    String prevWord = prevWord();
    if (prevWord.equals(")")) {
      int counter = 0;
      while (counter >= 0 && !isStart()) {
        String word = prevWord();
        if (word.equals(")")) counter++;
        else if (word.equals("(")) counter--;
        prevWord = word + prevWord;
      }
    }
    if (prevWord.equals("\"")) {
      StringBuilder sb = new StringBuilder();
      while (true) {
        char c = currChar();
        if (c == 0) break;
        sb.insert(0, c);
        if (c == '\n') {
          sb.deleteCharAt(0);
          break;
        }
        else if (c == '"') {
          if (prevChar() != '\\')
            break;
          else {
            nextChar();
          }
        }
        else
          prevChar();
      }
      prevChar();
      prevWord = sb.toString() + prevWord;
    }
    return prevWord;
  }

  String getCurrentLine() {
    savePos("$");
    while (!isStart() && prevChar() != '\n') ;
    int start = index + 1;
    while (!isEOF() && nextChar() != '\n') ;
    return code.substring(start, index - 1);
  }

  void upOneLevelBrace() {
    int braceCount = 0;
    while (braceCount >= 0 && !isStart()) {
      String prevWord = prevWord();
      if (prevWord.equals("}")) braceCount++;
      else if (prevWord.equals("{")) braceCount--;
    }
  }

  void upOneLevel() {
    int parenCount = 0;
    int braceCount = 0;
    while (braceCount >= 0 && parenCount >= 0 && !isStart()) {
      String prevWord = prevWord();
      if (prevWord.equals(")")) parenCount++;
      else if (prevWord.equals("(")) parenCount--;
      else if (prevWord.equals("}")) braceCount++;
      else if (prevWord.equals("{")) braceCount--;
    }
  }

  void savePos() {
    savePos("default");
  }

  void loadPos() {
    loadPos("default");
  }

  void savePos(String name) {
    positions.put(name, index);
  }

  void loadPos(String name) {
    if (positions.containsKey(name)) {
      reset();
      index = positions.get(name);
      int sum = 0;
      currLineNumber = 1;
      while (currLineNumber < lineLengths.size() && index > sum + lineLengths.get(currLineNumber)) {
        sum += lineLengths.get(currLineNumber++);
      }
      currColumnNumber = index - sum + 1;
    }
  }

  void printLocation() {
    System.out.printf("%d (%d:%d)\n", index, currLineNumber, currColumnNumber);
  }
}
