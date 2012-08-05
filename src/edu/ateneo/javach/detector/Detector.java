package edu.ateneo.javach.detector;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.ateneo.javach.detector.handlers.BEHandler;
import edu.ateneo.javach.detector.handlers.CBEHandler;
import edu.ateneo.javach.detector.handlers.CFSHandler;
import edu.ateneo.javach.detector.handlers.CPEHandler;
import edu.ateneo.javach.detector.handlers.IEHandler;
import edu.ateneo.javach.detector.handlers.ISEHandler;
import edu.ateneo.javach.detector.handlers.ITHandler;
import edu.ateneo.javach.detector.handlers.MRSHandler;
import edu.ateneo.javach.detector.handlers.PBEHandler;
import edu.ateneo.javach.detector.handlers.SEHandler;
import edu.ateneo.javach.features.FeatureSet;

public class Detector {
  ResourceBundle messageBundle;
  List<Handler> handlers;

  public Detector() {
    this(Locale.getDefault());
  }

  public Detector(Locale locale) {
    messageBundle = ResourceBundle.getBundle("edu.ateneo.javach.ErrorMessages", locale);
    this.handlers = new LinkedList<Handler>();
  }

  public void loadHandlers() {
    handlers.add(new BEHandler());
    handlers.add(new PBEHandler());
    handlers.add(new CBEHandler());
    handlers.add(new CPEHandler());
    handlers.add(new IEHandler());
    handlers.add(new ISEHandler());
    handlers.add(new SEHandler());
    handlers.add(new ITHandler());
    handlers.add(new CFSHandler());
    handlers.add(new MRSHandler());
  }

  public List<ErrorMessage> getErrors(List<FeatureSet> d) {
    List<ErrorMessage> result = new LinkedList<ErrorMessage>();
    while(!d.isEmpty()) {
      ErrorMessage e = null;
      for(Handler h : handlers) {
        try {
          e = h.handle(d);
        }
        catch(IOException err) {
          e = null;
        }
        if(e != null) break;
      }
      if(e == null) {
        d.remove(0);
      }
      else {
        result.add(e);
      }  
    }
    return result;
  }

  public List<String> getMessages(List<FeatureSet> d) {
    List<String> messages = new LinkedList<String>();
    for(ErrorMessage m : getErrors(d)) {
      messages.add(m.getMessage(messageBundle));
    }
    return messages;
  }
}
