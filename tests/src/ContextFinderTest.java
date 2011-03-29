import java.io.*;
import java.util.*;

import edu.ateneo.javach.parser.*;
import junit.framework.TestCase;

public class ContextFinderTest extends TestCase {
  public void testGetContext() {
    String testInput = "";
    try {
      Scanner fileScanner = new Scanner(new File("tests/res/ContextTest.java"));
      fileScanner.useDelimiter("\\Z");
      testInput = fileScanner.next();
    } catch (Exception e) {
    }
    SourceParser sp = new SourceParser(testInput);
    ContextFinder scf = new TopDownContextFinder(sp);
    assertEquals(Context.IMPORT_DECLARATION, scf.getContext(1, 18));
    assertEquals(Context.ROOT, scf.getPreviousContext());
    assertEquals(Context.CLASS_BODY, scf.getContext(4, 1));
    assertEquals(Context.CLASS_DECLARATION, scf.getPreviousContext());
    assertEquals(Context.VARIABLE_DECLARATION, scf.getContext(4, 18));
    assertEquals(Context.CLASS_BODY, scf.getPreviousContext());
    assertEquals(Context.VARIABLE_DECLARATION, scf.getContext(6, 15));
    assertEquals(Context.ASSIGNMENT, scf.getContext(6, 24));
    assertEquals(Context.METHOD_DECLARATION, scf.getContext(8, 21));
    assertEquals(Context.VARIABLE_DECLARATION, scf.getContext(8, 37));
    assertEquals(Context.METHOD_DECLARATION_PARAMETERS, scf.getPreviousContext() );
    assertEquals(Context.METHOD_BODY, scf.getContext(9, 6));
    assertEquals(Context.FOR_PARAMETERS, scf.getContext(9, 14));
    assertEquals(Context.WHILE_PARAMETERS, scf.getContext(10, 15));
    //assertEquals(Context.EXPRESSION, scf.getPreviousContext());
    assertEquals(Context.FOR_BLOCK, scf.getPreviousContext());
    assertEquals(Context.IF_PARAMETERS, scf.getContext(11, 16));
    //assertEquals(Context.EXPRESSION, scf.getPreviousContext());
    assertEquals(Context.WHILE_BLOCK, scf.getPreviousContext());
    assertEquals(Context.EXPRESSION, scf.getContext(12, 13));
    assertEquals(Context.IF_BLOCK, scf.getPreviousContext());
    assertEquals(Context.BLOCK, scf.getContext(18, 4));
    assertEquals(Context.CLASS_BODY, scf.getPreviousContext());
    assertEquals(Context.VARIABLE_DECLARATION, scf.getContext(19, 13));
    assertEquals(Context.ASSIGNMENT, scf.getContext(19, 32));
    assertEquals(Context.EXPRESSION, scf.getContext(20, 10));
    assertEquals(Context.EXPRESSION, scf.getContext(20, 26));
  }
}