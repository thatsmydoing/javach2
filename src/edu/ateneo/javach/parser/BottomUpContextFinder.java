package edu.ateneo.javach.parser;

public class BottomUpContextFinder extends ContextFinder {

  public BottomUpContextFinder(SourceParser sp) {
    super(sp);
  }

  public Context getContext(int lineNumber, int columnNumber) {
    parser.goTo(lineNumber, columnNumber);
    return getPreviousContext();
  }

  public Context getPreviousContext() {
    if (parser.getColumnNumber() == 0) {
      return Context.ROOT;
    }
    if (parser.currChar() == '{') {
      parser.prevChar();
      String prevWord = parser.prevGroup();
      if (prevWord.charAt(0) == '(') {
        String word = parser.prevGroup();
        if (word.equals("for")) {
          return Context.FOR_BLOCK;
        }
        else if (word.equals("if")) {
          return Context.IF_BLOCK;
        }
        else if (word.equals("while")) {
          return Context.WHILE_BLOCK;
        }
        else if (parser.prevGroup().equals("new")) {
          return Context.CLASS_BODY;
        }
        else {
          return Context.METHOD_BODY;
        }
      }
      else if (prevWord.equals("else")) {
        return Context.ELSE_BLOCK;
      }
      else if (prevWord.equals("}")) {
        parser.nextWord();
        parser.upOneLevel();
        parser.nextChar();
        return Context.BLOCK;
      }
      else if (prevWord.equals("{")) {
        return Context.BLOCK;
      }
      else {
        while (!parser.isEOF()) {
          String word = parser.prevGroup();
          if (word.equals("class")) {
            return Context.CLASS_BODY;
          }
          else if (word.equals("interface")) {
            return Context.INTERFACE_BODY;
          }
          else if (word.equals("enum")) {
            return Context.ENUM_BODY;
          }
          else if (word.equals("{")) {
            return Context.BLOCK;
          }
          else if (word.equals("}")) {
            return Context.BLOCK;
          }
        }
      }
      return Context.BLOCK;
    }
    parser.savePos();
    parser.upOneLevel();
    if (parser.currChar() == 0) {
      parser.loadPos();
      SourceParser lp = new SourceParser(parser.getCurrentLine());
      while (!lp.isEOF()) {
        String word = lp.nextGroup();
        Context c = null;
        if (word.equals("class")) {
          c = Context.CLASS_DECLARATION;
        }
        else if (word.equals("interface")) {
          c = Context.CLASS_DECLARATION;
        }
        else if (word.equals("enum")) {
          c = Context.CLASS_DECLARATION;
        }
        else if (word.equals("import")) {
          c = Context.IMPORT_DECLARATION;
        }
        else if (word.equals("package")) {
          c = Context.PACKAGE_DECLARATION;
        }
        if (c != null) {
          parser.loadPos();
          parser.upOneLevel();
          return c;
        }
      }
    }
    char currChar = parser.nextChar();
    parser.savePos("{");
    parser.prevChar();
    String prevWord = parser.prevWord();
    if (currChar == '(') {
      if (prevWord.equals("for")) {
        return Context.FOR_PARAMETERS;
      }
      else if (prevWord.equals("if")) {
        return Context.IF_PARAMETERS;
      }
      else if (prevWord.equals("while")) {
        return Context.WHILE_PARAMETERS;
      }
      else {
        switch(getPreviousContext()) {
          case CLASS_BODY:
            return Context.METHOD_DECLARATION_PARAMETERS;
          default:
            return Context.METHOD_CALL_PARAMETERS;
        }
      }
    }
    if (currChar == '{') {
      parser.loadPos();
      SourceParser lp = new SourceParser(parser.getCurrentLine());
      int counter = 0;
      int equalsPos = -1;
      int parenPos = -1;
      while (!lp.isEOF()) {
        String word = lp.nextGroup();
        if (word.charAt(0) == '(') {
          parenPos = lp.getColumnNumber();
        }
        if (word.equals("=")) {
          equalsPos = lp.getColumnNumber();
        }
        if (word.equals(";")) break;
        if (equalsPos == -1) counter++;
      }
      parser.loadPos("{");
      if (equalsPos == -1 && parenPos == -1) {
        if(counter == 1) {
          return Context.EXPRESSION;
        }
        return Context.VARIABLE_DECLARATION;
      }
      else if (equalsPos == -1 && parenPos >= 0) {
        Context c = getPreviousContext();
        parser.loadPos("{");
        switch (c) {
          case CLASS_BODY:
          case ENUM_BODY:
            return Context.METHOD_DECLARATION;
          default:
            return Context.EXPRESSION;
        }
      }
      else if (equalsPos >= 0) {
        parser.loadPos();
        if (parser.getColumnNumber() < equalsPos) {
          if (counter > 1) {
            return Context.VARIABLE_DECLARATION;
          }
          else {
            return Context.EXPRESSION;
          }
        }
        else {
          return Context.EXPRESSION;
        }
      }

    }
    return Context.EXPRESSION;
  }
}
