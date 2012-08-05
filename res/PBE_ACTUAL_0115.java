
/**
 * Write a description of class PBE_ACTUAL_0115 here.
 * 
 * @James Vincent E. Cruz
 * @13 August 2009
 */
public class PBE_ACTUAL_0115
{
    public static void main (String args [])
    {
       Cashier c1 = new Cashier;
       Manager man = new Manager();
       
       c1.connect(man);
       Cashier c2 = new Cashier(man);
       
       c1.buyProduct("apples", 10);
    }
}
