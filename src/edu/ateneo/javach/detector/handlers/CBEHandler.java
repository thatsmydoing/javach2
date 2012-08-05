package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class CBEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("']' expected")) {
      if(f1.errorCount >= 3) {
        String error3 = f1.errors.get(2).error; 
        if(error3.equals("<identifier> expected")) {
          return new ErrorMessage("CBE_SIB");
        }
        else if(error3.equals("illegal start of type")) {
          return new ErrorMessage("CBE_CBDY");
        }
      }
      if(f1.outerContext == Context.BLOCK) {
        return new ErrorMessage("CBE_SIB");
      }
      else if(f1.outerContext == Context.VARIABLE_DECLARATION) {
        return new ErrorMessage("CBE_NNEW");
      }
      else {
        return new ErrorMessage("CBE_UNKNOWN");
      }
    }
    d.add(0, f1);
    return null;
  }

}
