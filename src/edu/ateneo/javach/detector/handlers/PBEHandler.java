// if charTypeAtError1 = 123 then BPRM  (0 / 0 / 0 / 7 / 0 / 0 / 0) if charTypeAtError1 = letter and outerContext = EXPRESSION then 2TYP  (2 / 0 / 0 / 0 / 0 / 0 / 0) if charTypeAtError1 = 58 then BCHR  (0 / 0 / 2 / 0 / 0 / 0 / 0) if charTypeAtError1 = letter and outerContext = BLOCK then SPC  (1 / 0 / 0 / 0 / 0 / 4 / 0) if charTypeAtError1 = letter then 2TYP  (2 / 0 / 0 / 0 / 0 / 0 / 0) if charTypeAtError1 = 41 then 90  (0 / 1 / 0 / 0 / 0 / 0 / 0) if context = EXPRESSION and charTypeAtError1 = 62 then BCHR  (0 / 0 / 1 / 0 / 0 / 0 / 0) if context = ASSIGNMENT then SPLT  (0 / 0 / 0 / 0 / 0 / 0 / 1) if context = EXPRESSION and outerContext = BLOCK then 90  (0 / 1 / 1 / 0 / 1 / 0 / 0) else SPLT  (0 / 0 / 0 / 0 / 0 / 0 / 0)

package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class PBEHandler extends Handler {

  @Override
  public ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("'(' or '[' expected")) {
      if(f1.errors.get(0).character == 123) {
        return new ErrorMessage("PBE_BPRM");
      }
      else if(f1.errors.get(0).characterType.equals("letter") && f1.outerContext == Context.EXPRESSION) {
        return new ErrorMessage("PBE_2TYP");
      }
      else if(f1.errors.get(0).character == 58) {
        return new ErrorMessage("PBE_BCHR");
      }
      else if(f1.errors.get(0).characterType.equals("letter") && f1.outerContext == Context.BLOCK) {
        return new ErrorMessage("PBE_SPC");
      }
      else if(f1.errors.get(0).character == 41) {
        return new ErrorMessage("PBE_90");
      }
      else if(f1.context == Context.EXPRESSION && f1.errors.get(0).character == 62) {
        return new ErrorMessage("PBE_BCHR");
      }
      else if(f1.context == Context.ASSIGNMENT) {
        return new ErrorMessage("PBE_SPLT");
      }
      else {
        return new ErrorMessage("PBE_UNKNOWN");
      }
    }
    d.add(0, f1);
    return null;
  }

}
