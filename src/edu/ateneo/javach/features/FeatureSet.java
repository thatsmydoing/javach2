package edu.ateneo.javach.features;

import java.util.LinkedList;
import java.util.List;

import edu.ateneo.javach.parser.Context;

public class FeatureSet {
	public final String fileName;
	public final int errorCount;
	public final Context context;
	public final Context outerContext;
	public final List<ErrorFeature> errors;
	
	FeatureSet(String fileName, int errorCount, Context context, Context outerContext) {
		this.fileName = fileName;
		this.errorCount = errorCount;
		this.context = context;
		this.outerContext = outerContext;
		this.errors = new LinkedList<ErrorFeature>();
	}
	
	void addErrorFeature(ErrorFeature ef) {
		errors.add(ef);
	}
	
	public class ErrorFeature {
		public final String error;
		public final char character;
		public final String characterType;
		public final int offset;
		
		ErrorFeature(String error, char character, String characterType, int offset) {
			this.error = error;
			this.character = character;
			this.characterType = characterType;
			this.offset = offset;
		}
	}
	
}