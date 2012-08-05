package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.Context;

public class SEHandler extends Handler {

  @Override
  protected ErrorMessage process(List<FeatureSet> d) {
    FeatureSet f1 = d.remove(0);
    if(f1.errors.get(0).error.equals("';' expected")) {
      if(f1.context == Context.ASSIGNMENT) {
        // TODO
        return new ErrorMessage("SE_NPLS");
      }
      else if(f1.context == Context.EXPRESSION) {
        if(f1.outerContext == Context.BLOCK) {
          // TODO
          return new ErrorMessage("SE_NEQL");
        }
        else if(f1.outerContext == Context.METHOD_BODY) {
          // TODO
          return new ErrorMessage("SE_SPC");
        }
        else if(f1.outerContext == Context.METHOD_DECLARATION_PARAMETERS) {
          return new ErrorMessage("SE_TYPO");
        }
      }
      else if(f1.context == Context.FOR_PARAMETERS) {
        return new ErrorMessage("SE_FOR");
      }
      else if(f1.context == Context.IMPORT_DECLARATION) {
        return new ErrorMessage("SE_SPCDOT");
      }
      else if(f1.context == Context.METHOD_DECLARATION) {
        if(f1.outerContext == Context.CLASS_BODY) {
          // TODO
          return new ErrorMessage("SE_NEQL");
        }
        else if(f1.outerContext == Context.METHOD_DECLARATION_PARAMETERS) {
          // TODO
          return new ErrorMessage("SE_PARBDY");
        }
      }
      else if(f1.context == Context.VARIABLE_DECLARATION) {
        if(f1.errorCount >= 2) {
          if(f1.errors.get(1).error.equals("<identifier> expected")) {
            if(f1.errors.get(1).character == 32) {
              // TODO
              return new ErrorMessage("SE_2TYP");
            }
            else if(f1.errors.get(1).character == 59) {
              // TODO
              return new ErrorMessage("SE_SPC");
            }
          }
        }
        else {
          // TODO
          return new ErrorMessage("SE_NEQL");
        }
      }
      return new ErrorMessage("SE_UNKNOWN");

    }
    d.add(0, f1);
    return null;
  }

}
