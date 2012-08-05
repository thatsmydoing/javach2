public class IdentifiersTest {
  private int a;
  IdentifiersTest t;
  
  public static void main(String[] args) {
    a = 5;
    t = null;
    int b = a;
    a = b + a - 1;
    b = doSomething(b, a);
  }
  
  public int doSomething(int a, int b) {
    return a + b;
  }
}
