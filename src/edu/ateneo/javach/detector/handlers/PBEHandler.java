package edu.ateneo.javach.detector.handlers;

import java.util.List;

import edu.ateneo.javach.detector.ErrorMessage;
import edu.ateneo.javach.detector.Handler;
import edu.ateneo.javach.parser.Diagnostic;

public class PBEHandler extends Handler {

	@Override
	public ErrorMessage process(List<Diagnostic> d) {
		if(d.get(0).errorClass.equals("'(' or '[' expected")) {
			
		}		
		return null;
	}
	
}
