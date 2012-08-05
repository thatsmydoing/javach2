// if error2 = none then CSTMT  (0 / 7 / 1 / 1 / 0 / 0) if context = CLASS_DECLARATION then RES  (0 / 0 / 0 / 0 / 5 / 0) if charTypeAtError1 = 10 then CNPRM  (4 / 0 / 0 / 0 / 0 / 0) if charTypeAtError2 = letter and error2 = '(' expected then SPC  (0 / 0 / 0 / 0 / 0 / 2) if charTypeAtError1 = 32 and context = VARIABLE_DECLARATION then MTYPO  (0 / 0 / 1 / 0 / 0 / 0) if charTypeAtError1 = 32 and context = METHOD_DECLARATION then MTYPO  (0 / 0 / 2 / 0 / 0 / 2) if context = VARIABLE_DECLARATION then NTYP  (0 / 0 / 0 / 2 / 0 / 0)

package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class IEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("<identifier> expected")) {
      if(f1.errorCount < 2) {
        // TODO
        return new ErrorMessage("IE_CSTMT");
      }
      String error2 = f1.errors.get(1).error;
      String charTypeAtError2 = f1.errors.get(1).characterType;
      if(f1.context == Context.CLASS_DECLARATION) {
        return new ErrorMessage("IE_RES");
      }
      if(charTypeAtError2.equals("letter") && error2.equals("'(' expected")) {
        return new ErrorMessage("IE_SPC");
      }
      if(f1.errors.get(0).character == 32) {
        if(f1.context == Context.VARIABLE_DECLARATION) {
          return new ErrorMessage("IE_MTYPO");
        }
        else if(f1.context == Context.METHOD_DECLARATION) {
          // TODO
          return new ErrorMessage("IE_MTYPO");
        }
      }
      if(f1.context == Context.VARIABLE_DECLARATION) {
        return new ErrorMessage("IE_NTYP");
      }
      return new ErrorMessage("IE_UNKNOWN");
    }
    d.add(0, f1);
    return null;
  }

}
