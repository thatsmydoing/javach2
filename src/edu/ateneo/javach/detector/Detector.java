package edu.ateneo.javach.detector;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.ateneo.javach.detector.handlers.PBEHandler;
import edu.ateneo.javach.parser.Diagnostic;

public class Detector {
	ResourceBundle messageBundle;
	List<Handler> handlers;
	
	public Detector() {
		this(Locale.getDefault());
	}
	
	public Detector(Locale locale) {
		messageBundle = ResourceBundle.getBundle("ErrorMessages", locale);
		this.handlers = new LinkedList<Handler>();
	}
	
	public void loadHandlers() {
		handlers.add(new PBEHandler());
	}
	
	public List<ErrorMessage> getErrors(List<Diagnostic> d) {
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
			result.add(e);
		}
		return result;
	}
	
	public List<String> getMessages(List<Diagnostic> d) {
		List<String> messages = new LinkedList<String>();
		for(ErrorMessage m : getErrors(d)) {
			messages.add(m.getMessage(messageBundle));
		}
		return messages;
	}
}
