package edu.ateneo.javach.detector;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import edu.ateneo.javach.features.FeatureSet;
import edu.ateneo.javach.parser.ContextFinder;
import edu.ateneo.javach.parser.Diagnostic;
import edu.ateneo.javach.parser.SourceParser;
import edu.ateneo.javach.parser.TopDownContextFinder;

public abstract class Handler {
	protected SourceParser sp;
	protected ContextFinder cf;
	
	public ErrorMessage handle(List<FeatureSet> d) throws IOException {
	    if(d.size() <= 0) return null;
		Scanner fileScanner = new Scanner(new File(d.get(0).fileName));
		fileScanner.useDelimiter("\\Z");
		sp = new SourceParser(fileScanner.next());
		cf = new TopDownContextFinder(sp);
		return process(d);
	}
	
	protected abstract ErrorMessage process(List<FeatureSet> d);
}
