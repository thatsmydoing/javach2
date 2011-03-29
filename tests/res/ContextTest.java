import java.util.*;

public class ContextTest {
  public int testVariable;

  int testVariable2 = 5;

  public int testMethod(int testParameters) {
    for (int test = 0; test < 10; test++) {
      while (true) {
        if (test == 0) {
          break;
        }
      }
    }
  }

  {
    String testString = "This is in a block";
    testString = "Just testing"
  }
}