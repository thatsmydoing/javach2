package edu.ateneo.javach.parser;

public abstract class ContextFinder {
  SourceParser parser;

  public ContextFinder(SourceParser sp) {
    this.parser = sp;
  }

  public abstract Context getContext(int lineNumber, int columnNumber);

  public abstract Context getPreviousContext();
}
