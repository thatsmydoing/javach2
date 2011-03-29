package edu.ateneo.javach.parser;

import java.util.*;

public abstract class ContextHandler {
  ContextHandler parent;
  List<ContextHandler> children;
  SourceParser parser;

  ContextHandler() {
    children = new ArrayList<ContextHandler>();
  }

  ContextHandler addChild(String type) {
    try {
      Class<? extends ContextHandler> c = (Class<? extends ContextHandler>) Class.forName(type);
      ContextHandler h = c.newInstance();
      h.parser = parser;
      h.parent = this;
      children.add(h);
      return h;
    } catch (Exception e) {

    }
    return null;
  }

  public abstract void process();

  class ContextRange {
    final Context context;
    final int startIndex;
    final int endIndex;

    public ContextRange(Context context, int startIndex, int endIndex) {
      this.context = context;
      this.startIndex = startIndex;
      this.endIndex = endIndex;
    }
  }
}

class RootContextHandler extends ContextHandler {
  RootContextHandler(SourceParser sp) {
    parser = sp;
  }

  public void process() {
    while (!parser.isEOF()) {
      String nextWord = parser.nextWord();
      if (nextWord.equals("import")) {
        addChild("ImportContextHandler").process();
      }
      else if (nextWord.equals("class")) {
        addChild("ClassContextHandler").process();
      }
    }
  }
}

class ClassContextHandler extends ContextHandler {
  public void process() {
    boolean processing = true;
    while (processing) {
      char c = parser.nextChar();
      switch (c) {
        case '{':
          processing = false;

          break;
      }
    }
  }
}