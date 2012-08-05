
/**
 * Write a description of class CPE_ACTUAL_0029 here.
 * 
 * @author francine gomendoza
 * @version june 27, 2007
 */
public class CPE_ACTUAL_0029
{
	private double creditsLeft = 0;
	private int minutesCalled = 0;
	private double ratePerCall = 6.50;
	private int sentTextMessage = 0;
	
	public void load( double pesos )
	{
	    if ( pesos >= 25 )
	    creditsLeft = creditsLeft + pesos;
	   }
	
	public void call( int minutes )
	{
	    if ( creditsLeft >= ratePerCall*minutes )
	    {
	    creditsLeft = creditsLeft - ratePerCall*minutes;
	    minutesCalled = minutes;
	   }
	   else ( minutesCalled = creditsLeft/ratePerCall; )
	   }
	
	public void changeRate( double newCallRate )
	{
	    if ( ratePerCall <= 10.00 )
	    ratePerCall = newCallRate;
	}
	
	public void textMessage( int text )
	{
	    sentTextMessage = text;
	    creditsLeft = creditsLeft - sentTextMessage;
	}
	
	public double getloadLeft()
	{
	    return creditsLeft;
	}
	
	public int getTotalMinutesCalled()
	{
	    return minutesCalled;
	}
	
	public int getNumTextMessages()
	{
	    return sentTextMessage;
	}
	
	public void printSummary()
	{
	    System.out.printf( "Credits left: Php%5.2f\n", creditsLeft );
	    System.out.print( "Total call duration: ");
	    System.out.println( minutesCalled );
	    System.out.printf( "Rate per call: Php%4.2f\n", ratePerCall );
	    System.out.print( "Number of text messages sent: ");
	    System.out.println( sentTextMessage );
	}    
    
	   
	   
	   
}
