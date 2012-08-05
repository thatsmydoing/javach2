package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class ITHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("incompatible types")) {
      if(f1.errors.get(0).strings.get("expected").equals("boolean")) {
        if(f1.context == Context.IF_PARAMETERS) {
          return new ErrorMessage("IT_EQLS");
        }
        if(f1.context == Context.WHILE_PARAMETERS) {
          return new ErrorMessage("IT_EQLS");
        }
        if(f1.context == Context.FOR_PARAMETERS) {
          return new ErrorMessage("IT_EQLS");
        }
      }
      if(f1.errors.get(0).strings.get("found").equals("<nulltype>")) {
        return new ErrorMessage("IT_NULINT");
      }
      if(f1.errors.get(0).strings.get("expected").equals("int") && f1.context == Context.SWITCH_PARAMETERS) {
        return new ErrorMessage("IT_SWITCH");
      }
      return new ErrorMessage("IT_UNKNOWN");
    }
    d.add(0, f1);
    return null;
  }

}
