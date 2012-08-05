package edu.ateneo.javach.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopDownContextFinder extends ContextFinder {

  List<ContextRange> contextList;
  Stack<Context> contextStack;
  Context nextContext;

  int index;
  int lastContextIndex;

  public TopDownContextFinder(SourceParser sp) {
    super(sp);
    contextList = null;
  }

  public Context getContext(int lineNumber, int columnNumber) {
    parser.goTo(lineNumber, columnNumber);
    index = parser.getIndex();
    parser.goTo(1,1);
    if(contextList != null) {
      for(int i = 0; i < contextList.size(); i++) {
        ContextRange currContext = contextList.get(i);
        if(currContext.index > index) {
          lastContextIndex = i-1;
          return contextList.get(i-1).context;
        }
        else if(i == contextList.size() - 1) {
          lastContextIndex = i;
          return currContext.context;
        }
      }
      return Context.ROOT;
    }
    contextList = new ArrayList<ContextRange>();
    contextStack = new Stack<Context>();
    contextStack.push(Context.PREVIOUS);
    nextContext = Context.ROOT;
    while(!contextStack.isEmpty() && !parser.isEOF()) {
      Context currContext = contextStack.peek();
      if(nextContext == Context.PREVIOUS) {
        Context from = contextStack.pop();
        Context to = contextStack.peek();
        if(to == Context.PREVIOUS) break;
        contextList.add(new ContextRange(to, parser.getIndex()));
        getContextHandler(to).exitFrom(from);
      }
      else {
        contextStack.push(nextContext);
        contextList.add(new ContextRange(nextContext, parser.getIndex()));
        getContextHandler(nextContext).enterFrom(currContext);
      }
    }
    return getContext(lineNumber, columnNumber);
  }

  public Context getPreviousContext() {
    if(--lastContextIndex < 0) return Context.ROOT;
    return contextList.get(lastContextIndex).context;
  }

  ContextHandler getContextHandler(Context c) {
    switch(c) {
      case ROOT:
        return rootH;
      case IMPORT_DECLARATION:
        return importH;
      case CLASS_DECLARATION:
        return classDeclarationH;
      case CLASS_BODY:
        return classBodyH;
      case VARIABLE_DECLARATION:
        return variableDeclarationH;
      case METHOD_DECLARATION:
        return methodDeclarationH;
      case METHOD_DECLARATION_PARAMETERS:
        return methodDeclarationParametersH;
      case IF_PARAMETERS:
      case WHILE_PARAMETERS:
        return booleanH;
      case FOR_PARAMETERS:
        return booleanH;
      case SWITCH_PARAMETERS:
        return booleanH;
      case IF_BLOCK:
      case FOR_BLOCK:
      case WHILE_BLOCK:
      case METHOD_BODY:
      case ELSE_BLOCK:
      case BLOCK:
        return blockH;
      case SWITCH_BLOCK:
        return switchBlockH;
      case ASSIGNMENT:
      case EXPRESSION:
        return expressionH;
      case METHOD_CALL_PARAMETERS:
        return expressionH;
      default:
        return null;
    }
  }

  interface ContextHandler {
    public void enterFrom(Context c);
    public void exitFrom(Context c);
  }

  ContextHandler rootH = new ContextHandler() {
    public void enterFrom(Context c) {
      while(!parser.isEOF()) {
        String word = parser.nextGroup();
        if(word == null) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(word.equals("import")) {
          nextContext = Context.IMPORT_DECLARATION;
          return;
        }
        else if(word.equals("class") || word.equals("interface") || word.equals("enum")) {
          nextContext = Context.CLASS_DECLARATION;
          return;
        }
      }
      nextContext = Context.PREVIOUS;
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  ContextHandler importH = new ContextHandler() {
    public void enterFrom(Context c) {
      while(true) {
        char d = parser.nextChar();
        if(d == 0) break;
        if(d == ';') break;
        if(d == '\n') break;
      }
      nextContext = Context.PREVIOUS;
    }

    public void exitFrom(Context c) {
      nextContext = Context.PREVIOUS;
    }
  };

  ContextHandler classDeclarationH = new ContextHandler() {
    public void enterFrom(Context c) {
      parser.prevChar();
      String type = parser.nextGroup();
      String name = parser.nextGroup();
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup();
        if(nextWord.equals("extends")) {
          String parent = parser.nextGroup();
        }
        else if(nextWord.equals("implements")) {
          String parent = parser.nextGroup();
          while(!parser.isEOF()) {
            String nextWord2 = parser.nextGroup();
            if(nextWord2.equals(",")) {
              parent = parser.nextGroup();
            }
            else {
              break;
            }
          }
        }
        else {
          break;
        }
      }
      nextContext = Context.CLASS_BODY;
    }

    public void exitFrom(Context c) {
      nextContext = Context.PREVIOUS;
    }
  };

  ContextHandler classBodyH = new ContextHandler() {
    public void enterFrom(Context c) {
      char d = parser.prevChar();
      if(d == '(' || d == '{' || d == ';' || d == '}') parser.nextChar();
      parser.goToStartOfWord();
      parser.savePos();
      String nextWord = parser.nextGroup();
      if(nextWord == null) {
        nextContext = Context.PREVIOUS;
      }
      else if(nextWord.equals("}")) {
        nextContext = Context.PREVIOUS;
      }
      else if(nextWord.equals("{")) {
        nextContext = Context.BLOCK;
      }
      else if(nextWord.equals(")")) {
        nextContext = Context.PREVIOUS;
      }
      else if(nextWord.equals(",")) {
        nextContext = Context.PREVIOUS;
      }
      else {
        int lastLineNumber = parser.getLineNumber();
        while(!parser.isEOF()) {
          nextWord = parser.nextGroup(false);
          if(nextWord.equals("(")) {
            parser.loadPos();
            nextContext = Context.METHOD_DECLARATION;
            return;
          }
          else if(nextWord.equals(";") || nextWord.equals("=") || lastLineNumber < parser.getLineNumber()) {
            parser.loadPos();
            nextContext = Context.VARIABLE_DECLARATION;
            return;
          }
        }
      }
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  ContextHandler variableDeclarationH = new ContextHandler() {
    public void enterFrom(Context c) {
      int lastLineNumber = parser.getLineNumber();
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals(",") || nextWord.equals(";") || nextWord.equals(")") || lastLineNumber < parser.getLineNumber()) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals("=")) {
          nextContext = Context.ASSIGNMENT;
          return;
        }
      }
    }

    public void exitFrom(Context c) {
      nextContext = Context.PREVIOUS;
    }
  };

  ContextHandler methodDeclarationH = new ContextHandler() {
    public void enterFrom(Context c) {
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("(")) {
          nextContext = Context.METHOD_DECLARATION_PARAMETERS;
          return;
        }
      }
    }

    public void exitFrom(Context c) {
      if(c == Context.METHOD_DECLARATION_PARAMETERS) {
        parser.nextGroup(false);
        nextContext = Context.METHOD_BODY;
      }
      else {
        nextContext = Context.PREVIOUS;
      }
    }
  };

  ContextHandler methodDeclarationParametersH = new ContextHandler() {
    public void enterFrom(Context c) {
      char d = parser.prevChar();
      parser.nextChar();
      if(d == '(' || d == ',') {
        nextContext = Context.VARIABLE_DECLARATION;
      }
      else {
        nextContext = Context.PREVIOUS;
      }
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  ContextHandler blockH = new ContextHandler() {
    public void enterFrom(Context c) {
      parser.goToStartOfWord();
      parser.savePos();
      String nextWord = parser.nextGroup();
      if(nextWord.equals("for")) {
        nextContext = Context.FOR_PARAMETERS;
        parser.nextGroup(false);
      }
      else if(nextWord.equals("if")) {
        nextContext = Context.IF_PARAMETERS;
        parser.nextGroup(false);
      }
      else if(nextWord.equals("while")) {
        nextContext = Context.WHILE_PARAMETERS;
        parser.nextGroup(false);
      }
      else if(nextWord.equals("switch")) {
        nextContext = Context.SWITCH_PARAMETERS;
      }
      else if(nextWord.equals("else")) {
        nextWord = parser.nextGroup(false);
        if(nextWord.equals("{")) {
          nextContext = Context.ELSE_BLOCK;
        }
        else {
          enterFrom(c);
        }
      }
      else if(nextWord.equals("}")) {
        nextContext = Context.PREVIOUS;
      }
      else {
        int counter = 1;
        while(!parser.isEOF()) {
          nextWord = parser.nextGroup(false);
          if(nextWord.equals("=") && counter > 1) {
            parser.loadPos();
            nextContext = Context.VARIABLE_DECLARATION;
            return;
          }
          else if(nextWord.equals("=") || nextWord.equals(";") || nextWord.equals("}")) {
            parser.loadPos();
            nextContext = Context.EXPRESSION;
            return;
          }
          counter++;
        }
      }
    }

    public void exitFrom(Context c) {
      if(c == Context.FOR_PARAMETERS) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("{")) {
          nextContext = Context.FOR_BLOCK;
        }
        else {
          nextContext = Context.EXPRESSION;
        }
      }
      else if(c == Context.IF_PARAMETERS) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("{")) {
          nextContext = Context.IF_BLOCK;
        }
        else {
          nextContext = Context.EXPRESSION;
        }
      }
      else if(c == Context.WHILE_PARAMETERS) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("{")) {
          nextContext = Context.WHILE_BLOCK;
        }
        else if(nextWord.equals(";")) {
          nextContext = Context.PREVIOUS;
        }
        else {
          nextContext = Context.EXPRESSION;
        }
      }
      else if(c == Context.SWITCH_PARAMETERS) {
        parser.nextGroup(false);
        nextContext = Context.SWITCH_BLOCK;
      }
      else {
        enterFrom(c);
      }
    }
  };

  ContextHandler expressionH = new ContextHandler() {
    public void enterFrom(Context c) {
      int counter = 1;
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("=")) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals(";")) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals(")")) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals("}")) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals("(")) {
          if(counter % 2 == 0) {
            nextContext = Context.METHOD_CALL_PARAMETERS;
            return;
          }
          else {
            nextContext = Context.EXPRESSION;
            return;
          }
        }
        counter++;
      }
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  ContextHandler booleanH = new ContextHandler() {
    public void enterFrom(Context c) {
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup(false);
        if(nextWord == null) {
          return;
        }
        if(nextWord.equals(")")) {
          nextContext = Context.PREVIOUS;
          return;
        }
        else if(nextWord.equals("{")) {
          nextContext = Context.PREVIOUS;
          return;
        }
      }
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  ContextHandler switchBlockH = new ContextHandler() {
    public void enterFrom(Context c) {
      while(!parser.isEOF()) {
        String nextWord = parser.nextGroup(false);
        if(nextWord.equals("}")) {
          nextContext = Context.PREVIOUS;
          return;
        }
      }
    }

    public void exitFrom(Context c) {
      enterFrom(c);
    }
  };

  class ContextRange {
    Context context;
    int index;

    ContextRange(Context context, int index) {
      this.context = context;
      this.index = index;
    }

    public String toString() {
      return context.toString() + " " + index;
    }
  }
}
