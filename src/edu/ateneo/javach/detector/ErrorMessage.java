package edu.ateneo.javach.detector;

import java.util.HashMap;
import java.util.ResourceBundle;

public class ErrorMessage {
	final String type;
	final HashMap<String, String> strings;
	
	public ErrorMessage(String type) {
		this.type = type;
		this.strings = new HashMap<String, String>();
	}
	
	public void setString(String name, String value) {
		strings.put(name, value);
	}
	
	public String getMessage(ResourceBundle messageBundle) {
		String template = messageBundle.getString(type);
		for(String name : strings.keySet()) {
			template.replaceAll("{"+name+"}", strings.get(name));
		}
		return template;
	}
}
