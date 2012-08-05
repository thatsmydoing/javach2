package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class CPEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("')' expected")) {
      if(f1.outerContext == Context.BLOCK) {
        if(f1.context == Context.IF_PARAMETERS) {
          // TODO
          return new ErrorMessage("CPE_ARGSPC");
        }
        else if(f1.context == Context.WHILE_PARAMETERS) {
          return new ErrorMessage("CPE_NLT");
        }
      }
      else if(f1.outerContext == Context.EXPRESSION) {
        if(f1.errorCount >= 2) {
          String error2 = f1.errors.get(1).error;
          if(error2.equals("illegal start of expression")) {
            if(f1.errorCount >= 3) {
              String error3 = f1.errors.get(2).error;
              if(error3.equals("';' expected")) {
                // TODO kinda
                return new ErrorMessage("CPE_TYPARG");
              }
              else if(error3.equals("not a statement")) {
                return new ErrorMessage("CPE_ARGSPC");
              }
            }
            else {
              // TODO
            }
          }
          else if(error2.equals("illegal start of type")) {
            return new ErrorMessage("CPE_CLLBRC");
          }
          else if(error2.equals("not a statement")) {
            char charAtError1 = f1.errors.get(0).character;
            if(charAtError1 == 123) {
              return new ErrorMessage("CPE_CLLBRC");
            }
            else if(charAtError1 == 32) {
              if(f1.errorCount >= 3) {
                char charAtError3 = f1.errors.get(2).character;
                if(charAtError3 == 41) {
                  // TODO
                  return new ErrorMessage("CPE_NCMM");
                }
                else if(charAtError3 == 44) {
                  return new ErrorMessage("CPE_SPCDOT");
                }
              }
            }
            else if(charAtError1 == 33) {
              return new ErrorMessage("CPE_QUOT");
            }
          }
          else if(error2.equals("unclosed string literal")) {
            return new ErrorMessage("CPE_QUOT");
          }
        }
      }
      else if(f1.outerContext == Context.METHOD_CALL_PARAMETERS) {
        if(f1.context == Context.EXPRESSION) {
          return new ErrorMessage("CPE_PARBDY");
        }
        else if(f1.context == Context.METHOD_CALL_PARAMETERS) {
          return new ErrorMessage("CPE_NCMM");
        }
      }
      else if(f1.outerContext == Context.METHOD_DECLARATION_PARAMETERS) {
        if(f1.errorCount >= 3) {
          char charAtError3 = f1.errors.get(2).character;
          if(charAtError3 == 32) {
            char charAtError1 = f1.errors.get(0).character;
            if(charAtError1 == 123) {
              return new ErrorMessage("CPE_ARRBRC");
            }
            else if(charAtError1 == 32) {
              return new ErrorMessage("CPE_TYPSPC");
            }
          }
          else if(charAtError3 == 41) {
            return new ErrorMessage("CPE_ARRBRC");
          }
          else if(charAtError3 == 44) {
            return new ErrorMessage("CPE_ARRBRC");
          }
        }
        else {
          if(f1.errorCount >= 2) {
            String error2 = f1.errors.get(1).error;
            if(error2.equals("<identifier> expected")) {
              return new ErrorMessage("CPE_BCHR");
            }
          }
          else {
            return new ErrorMessage("CPE_CLSBRC");
          }
        }
      }
      return new ErrorMessage("CPE_UNKNOWN");
    }
    d.add(0, f1);
    return null;
  }
}
