package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class ISEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("illegal start of expression")) {
      if(f1.context == Context.METHOD_BODY && f1.errors.get(0).character == 41) {
        return new ErrorMessage("ISE_PARBDY");
      }
      if(f1.context == Context.BLOCK) {
        return new ErrorMessage("ISE_EXPAR");
      }
      if(f1.context == Context.IF_PARAMETERS && f1.errors.get(0).character == 62) {
        return new ErrorMessage("ISE_BCMP");
      }
      if(f1.errors.get(0).character == 41 && f1.errorCount < 2) {
        return new ErrorMessage("ISE_IFNPRM");
      }
      if(f1.context == Context.METHOD_BODY && f1.errors.get(0).characterType.equals("letter")) {
        return new ErrorMessage("ISE_NTCLSD");
      }
      if(f1.errors.get(0).characterType.equals("letter") && f1.errorCount < 2) {
        return new ErrorMessage("ISE_PPP");
      }
      if(f1.context == Context.IF_PARAMETERS && f1.outerContext == Context.BLOCK) {
        // TODO
        return new ErrorMessage("ISE_BCMP");
      }
      if(f1.errors.get(0).characterType.equals("letter")) {
        return new ErrorMessage("ISE_EXBRC");
      }
      if(f1.errors.get(0).character == 38) {
        return new ErrorMessage("ISE_CSTYL");
      }
      if(f1.context == Context.FOR_PARAMETERS) {
        return new ErrorMessage("ISE_IFNPRM");
      }
      if(f1.outerContext == Context.BLOCK) {
        return new ErrorMessage("ISE_PARBDY");
      }
      if(f1.context == Context.METHOD_CALL_PARAMETERS) {
        return new ErrorMessage("ISE_QUOT");
      }
      return new ErrorMessage("ISE_UNKNOWN");
    }
    d.add(0, f1);
    return null;
  }

}
