package edu.ateneo.javach.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ateneo.other.Tools;

public class IdentifierCollector {
  List<String> AssignmentOperators;
  List<String> InfixOperators;
  List<String> PrefixOperators;
  List<String> PostfixOperators;
  List<String> BasicTypes;
  List<String> CommonTypes;
  List<String> Modifiers;
  List<String> ReservedWords;
    
  SourceParser sp;
  Map<String, Identifier> identifiers;
  
  public IdentifierCollector(SourceParser sp) {
    this.sp = sp;
    identifiers = new HashMap<String, IdentifierCollector.Identifier>();
    String[] assignmentOperators = {
      "=",
      "+=",
      "-=",
      "*=",
      "/=",
      "&=",
      "|=",
      "^=",
      "%=",
      "<<=",
      ">>=",
      ">>>="
    };
    AssignmentOperators = Arrays.asList(assignmentOperators);
    
    String[] infixOperators = {
      "||",
      "&&",
      "|",
      "^",
      "&",
      "==",
      "!=",
      "<",
      ">",
      "<=",
      ">=",
      "<<",
      ">>",
      ">>>",
      "+",
      "-",
      "*",
      "/",
      "%"
    };
    InfixOperators = Arrays.asList(infixOperators);
    
    String[] prefixOperators = {
      "++",
      "--",
      "!",
      "~",
      "+",
      "-"
    };
    PrefixOperators = Arrays.asList(prefixOperators);
    
    String[] postfixOperators = {
      "++",
      "--"
    };
    PostfixOperators = Arrays.asList(postfixOperators);
    
    String[] basicTypes = {
      "byte",
      "short",
      "char",
      "int",
      "long",
      "float",
      "double",
      "boolean"
    };
    BasicTypes = Arrays.asList(basicTypes);
    
    String[] commonTypes = {
      "Object",
      "String",
      "File",
      "ArrayList",
      "LinkedList",
      "Stack"
    };
    CommonTypes = Arrays.asList(commonTypes);
    
    String[] modifiers = {
      "public",
      "protected",
      "private",
      "static",
      "abstract",
      "final",
      "native",
      "synchronized",
      "transient",
      "volatile",
      "strictfp",
    };
    Modifiers = Arrays.asList(modifiers);
    
    String[] reservedWords = {
      "if",
      "for",
      "while",
      "switch",
      "case",
      "default",
      "try",
      "catch",
      "finally",
      "implements",
      "extends",
      "throws",
      "continue",
      "break",
      "true",
      "false",
      "null",
      "import"
    };
    ReservedWords = new ArrayList<String>(Arrays.asList(reservedWords));
    ReservedWords.addAll(Modifiers);
  }
  
  public boolean isNear(String word, List<String> list, int distance) {
    boolean found = false;
    for(String word2 : list) {
      if(distance == 0) {
        found = word.equals(word2);
      }
      else {
        found = (Tools.levenshteinDistance(word, word2) <= distance);
      }
      if(found) {
        return true;
      }
    }
    return false;
  }
  
  public void process() {
    sp.reset();
    boolean seenType = false;
    String tempWord = null;
    while(!sp.isEOF()) {      
      String word = sp.nextGroup(false);
      if(word.equals("(") && tempWord != null) {
        Identifier method = getIdentifier(tempWord);
        method.methodScore += 1;
        tempWord = null;
      }
      if(word.length() < 3 || 
          word.startsWith("\"") || 
          Character.isDigit(word.charAt(0)) ||
          isNear(word, ReservedWords, 0)) {
        if(tempWord != null && !tempWord.endsWith(".")) {
          Identifier var = getIdentifier(tempWord);
          var.variableScore += 1;
          tempWord = null;
        }
        continue;
      }
      if(isNear(word, BasicTypes, 0)) {
        seenType = true;
      }
      else if(isNear(word, CommonTypes, 0)) {
        seenType = true;
      }
      else if(seenType) {
        tempWord = word;
      }
      else {
        if(tempWord == null) {
          tempWord = word;
        }
        else {
          if(tempWord.equals("class") ||
              tempWord.equals("interface") ||
              tempWord.equals("enum")) {
            Identifier type = getIdentifier(word);
            type.classScore += 1;
            tempWord = null;
          }
          else {
            Identifier type = getIdentifier(tempWord);
            type.classScore += 1;
            tempWord = word;
          }
        }
      }
    }
    
    for(Identifier i : identifiers.values()) {
      i.similar.addAll(sortSimilar(i.name));
    }
  }
  
  public List<Identifier> sortSimilar(final String name) {
    List<Identifier> idents = new ArrayList<Identifier>(identifiers.values());
    Collections.sort(idents, new Comparator<Identifier>() {
      @Override
      public int compare(Identifier o1, Identifier o2) {
        return o1.getDistanceFrom(name) - o2.getDistanceFrom(name);
      }
    });
    return idents;
  }
  
  public Identifier getIdentifier(String name) {
    if(!identifiers.containsKey(name)) {
      identifiers.put(name, new Identifier(name));
    }
    return identifiers.get(name);
  }
  
  public class Identifier {
    public final String name;
    double variableScore;
    double methodScore;
    double classScore;
    List<Identifier> similar;
    Map<String, Integer> distances;
    
    public Identifier(String name) {
      this.name = name;
      variableScore = 0;
      methodScore = 0;
      classScore = 0;
      similar = new ArrayList<Identifier>();
      distances = new HashMap<String, Integer>(); 
    }
    
    public int getDistanceFrom(String name) {
      if(!distances.containsKey(name)) {
        int distance = Tools.levenshteinDistance(this.name, name);
        distances.put(name, distance);
        return distance;
      }
      return distances.get(name);
    }
    
    public String getMostLikelyType() {
      if(variableScore >= methodScore && variableScore >= classScore) {
        return "variable";
      }
      else if(methodScore >= variableScore && methodScore >= classScore) {
        return "method";
      }
      else {
        return "class";
      }
    }
  }
}

