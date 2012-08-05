
/**
 * Tests SodaTrader.
 * 
 * @author Mark Joshua U. Tan 
 * @version July 9, 2009
 */
public class PBE_ACTUAL_0110
{
    public static void main(String args[])
    {
        SodaTrader crispin = new SodaTrader;
        SodaTrader basilio = new SodaTrader;
        
        crispin.buyOneCase(200);
        basilio.buyOneCase(250);
        crispin.sellSoda(5,20);
        basilio.sellSoda(10,22.5);
        crispin.sellSoda(10,15.5);
        basilio.sellSoda(10,12.25);
        crispin.buyOneCase(150);
        basilio.buyOneCase(300);
        crispin.sellSoda(24,16.75);
        basilio.sellSoda(24,25.25);
        
        System.out.printf("SODA TRADER SUMMARY\nCrispin has sold %d cans. He has Php %5.2f and %d cans left.\nBasilio has sold %d cans. He has Php %5.2f and %d cans left.\nEND OF REPORT",crispin.getSoldCans(),crispin.getCashOnHand,crispin.getCansOnHand,basilio.getSoldCans(),basilio.getCashOnHand,basilio.getCansOnHand)
    }
}
