import java.io.File;
import java.util.Scanner;

import edu.ateneo.javach.parser.IdentifierCollector;
import edu.ateneo.javach.parser.SourceParser;
import junit.framework.TestCase;



public class IdentifierCollectorTest extends TestCase {
  public void testProcess() {
    String testInput = "";
    try {
      Scanner fileScanner = new Scanner(new File("tests/res/ContextTest.java"));
      fileScanner.useDelimiter("\\Z");
      testInput = fileScanner.next();
    } catch (Exception e) {
    }
    SourceParser sp = new SourceParser(testInput);
    IdentifierCollector ic = new IdentifierCollector(sp);
    ic.process();
    System.out.println(ic);
  }
}
