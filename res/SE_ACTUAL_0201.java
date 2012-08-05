
/**
 * Write a description of class SE_ACTUAL_0201 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner
public class SE_ACTUAL_0201
{
	public static void main( String args[] )
	{
	    Scanner in = newScanner( System.in );
	    System.out.print( "Type initial cash: " );
	    double initCash = in.nextDouble();
	    System.out.print( "Type price per dozen: " );
	    double dozenPrice = in.nextDouble();
	    System.out.print( "Type number of eggs: " );
	    int numEggs = in.nextInt();
	    System.out.print( "Type price per egg: " );
	    double unitPrice = in.nextDouble();
	    EggTrader e = new EggTrader( initCash );
	    e.buyOneDozen( dozenPrice );
	    e.sellEggs( numEggs, unitPrice );
	    System.out.printf( "eggs: %d\n", e.getEggCount() );
	    System.out.printf( "cash: %8.2f\n", e.getCashOnHand() );
	 }
	   
}
