
/**
 * Write a description of class CPE_ACTUAL_0019 here.
 * 
 * @author John Paul Brown 
 * @version 27 June 2007
 */

public class CPE_ACTUAL_0019

{
    private double loadLeftInPesos;
    private int totalMinutesCalled;
    private double ratePerMinute;
    private int numTextMessages;
    
    public CPE_ACTUAL_0019()
    {
        loadLeftInPesos = 0;
        totalMinutesCalled = 0;
        ratePerMinute = 6.50;
        numTextMessages = 0;
    }
    
    public void load( double pesos )
    {
        if (
        loadLeftInPesos = loadLeftInPesos + pesos;
    }
    
    public void call( int minutes )
    {
        if ( loadLeftInPesos >= loadUsed )
        {
            totalMinutesCalled = totalMinutesCalled + minutes;
            double loadUsed = minutes*ratePerMinute;
            loadLeftInPesos = loadLeftInPesos - loadUsed;
        }
    }
    
    public double getLoadLeft()
    {
        return loadLeftInPesos;
    }
    
    public int getTotalMinutesCalled()
    {
        return totalMinutesCalled;
    }
    
    public void changeRate ( double newCallRate )
    {
        if
        ratePerMinute = newCallRate;
    }
    
    public void sendTextMessage()
    {
        if (
        numTextMessages = numTextMessages + 1;
        loadLeftInPesos = loadLeftInPesos - 1;
    }
    
    public int getNumTextMessages()
    {
        return numTextMessages;
    }
    
    public void passLoadTo( CPE_ACTUAL_0019 dest, double amount )
    {
        
    }
        
    public void printSummary()
    {
        System.out.printf( "Credits left: P%5.2f\n", loadLeftInPesos );
        System.out.printf( "Total call duration: %2d mins.\n", totalMinutesCalled );
        System.out.printf( "Rate per call: P%4.2f\n", ratePerMinute );
        System.out.printf( "Number of text messages sent: %d", numTextMessages );
    }

}
