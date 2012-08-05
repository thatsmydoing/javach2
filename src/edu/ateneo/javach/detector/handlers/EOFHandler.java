package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;

public class EOFHandler extends Handler{

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("reached end of file while parsing")) {
      return new ErrorMessage("EOF_ACTUAL");
    }
    d.add(0, f1);
    return null;
  }

}
