
/**
 * Write a description of class PBE_ACTUAL_0107 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PBE_ACTUAL_0107
{
    public static void main( String args [] )
    {
        SodaTrader crispin = new SodaTrader;
        SodaTrader basilio = new SodaTrader;
        crispin.buyOneCase( 200.00 );
        basilio.buyOneCase( 250.00 );
        crispin.sellSoda( 5, 20.00 );
        basilio.sellSoda( 10, 22.50 );
        crispin.sellSoda( 10, 15.50 );
        basilio.sellSoda( 10, 12.25 );
        crispin.buyOneCase( 150.00 );
        basilio.buyOneCase( 300.00 );
        crispin.sellSoda( 24, 16.75 );
        basilio.sellSoda( 24, 25.25 );
        System.out.println("SODA TRADER SUMMARY");
        System.out.println("Crispin has sold" + soldCans + "cans. He has Php" + balance + "and" + CansOnHand + "cans left.");
        System.out.println("Basilio has sold" + soldCans + "cans. He has Php" + balance + "and" + CansOnHand + "cans left.");
        System.out.println("END OF REPORT");
    }
}
