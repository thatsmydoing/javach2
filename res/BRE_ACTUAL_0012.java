public class BRE_ACTUAL_0012

public static void main( String args[])
{
    EggTrader e = new EggTrader();
    e.buyOneDozen (50.00);
    e.SellEggs(3, 5.00);
    System.out.rintln(e.getEggCost());
    System.out.println(e.getCashOnHand());
    e.sellEggs(2, 7.50);
    System.out.println(e.getCashOnHand());
}
