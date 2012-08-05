package edu.ateneo.javach.detector.handlers;

import java.util.ArrayList;
import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.IdentifierCollector;
import edu.ateneo.javach.parser.IdentifierCollector.Identifier;

public class CFSHandler extends Handler {
  IdentifierCollector ic;
  
  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.startsWith("cannot find symbol")) {
      ic = new IdentifierCollector(sp);
      ic.process();
      String type = f1.errors.get(0).strings.get("type");
      System.out.println(type);
      String identifier = f1.errors.get(0).strings.get("identifier");
      
      List<Identifier> similarIdentifiers = ic.sortSimilar(identifier);
      List<Identifier> possibleIdentifiers = new ArrayList<IdentifierCollector.Identifier>();
      Identifier closest = similarIdentifiers.get(0);
      if(closest.name.equals(identifier)) {
        String closestType = closest.getMostLikelyType();
        if(type.equals("variable")) {
          if(closestType.equals("method")) {
            return new ErrorMessage("UV_NPRM");
          }
        }
        else if(type.equals("method")) {
          if(closestType.equals("method")) {
            return new ErrorMessage("UM_WPRM");
          }        
        }
        else {
          
        }
      }
      String problem = "_TYPO";
      for(Identifier i : similarIdentifiers) {
        if(i.name.equals(identifier)) continue;
        if(i.name.indexOf(identifier) >= 0) {
          possibleIdentifiers.add(0, i);
          problem = "_OVER";
        }
        else if(identifier.indexOf(i.name) >= 0) {
          possibleIdentifiers.add(0, i);
          problem = "_INC";
        }
        else if(i.getDistanceFrom(identifier) < 3) {
          possibleIdentifiers.add(i);
        }
      }
      if(type.equals("variable")) {
        problem = "UV" + problem;
      }
      else if(type.equals("method")) {
        problem = "UM" + problem;        
      }
      else {
        problem = "UC" + problem;
      }
      ErrorMessage e = new ErrorMessage(problem);
      int counter = 1;
      for(Identifier possible : possibleIdentifiers) {
        e.setString("possible"+counter, possible.name);
        counter++;
      }
      return e;
      //f1.errors.remove(0);
      //if(f1.errors.isEmpty()) d.remove(0);
    }
    d.add(0, f1);
    return null;
  }

}
