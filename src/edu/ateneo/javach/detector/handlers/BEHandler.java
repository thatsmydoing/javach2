package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;

public class BEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("'[' expected")) {
      char c = f1.errors.get(0).character;
      if(c == 123) {
        return new ErrorMessage("BE_BPRM");
      }
      else if(c == 32) {
        return new ErrorMessage("BE_2TYP");
      }
      else if(c == 40) {
        return new ErrorMessage("BE_PIBFPA");
      }
      else {
        return new ErrorMessage("BE_UNKNOWN");
      }
    }
    d.add(0, f1);
    return null;
  }

}
