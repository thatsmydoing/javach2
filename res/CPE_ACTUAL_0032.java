public class CPE_ACTUAL_0032
{
    public double credits;
    public int minutes_called;
    public double rate;
    public int messages_sent;
    
    
    public CPE_ACTUAL_0032()
    {
    
    credits = 0;
    minutes_called = 0;
    rate = 6.50;
    }
    
    public void load (double pesos)
    {
        credits = credits + pesos;
    }
    
    
    public void call (int minutes)
    {
        minutes_called = minutes_called + minutes;
    }
    
    public double getLoadLeft ()
    {
        return credits;
    }
    
    public int getTotalMinutesCalled()
    {
        return minutes_called;
    }
    
    public void changeRate(double newCallRate)
    {
        rate = newCallRate;
    }
    
    public void sendTextMessage()
    {
        messages_sent = messages_sent + 1;
        credits = credits - 1;
    }
    
    public int getNumTextMessages()
    {
        return messages_sent;
    }
    
    public void printSummary()
    { 
       System.out.print("Credits Left: P ");
       System.out.printf("%7.2f \n", getLoadLeft());
       System.out.print("Total call duration : ");
       System.out.print(getTotalMinutesCalled());
       System.out.println(" mins.");
       System.out.print("Rate per call: P ");
       System.out.printf("%6.2f \n", rate);
       System.out.print("Number of text messages sent: ");
       System.out.println(getNumTextMessages());
    }
    
    public boolean isSatisfiedByCredits()
    {
        double cost = ( int minutes ) * ( rate );
        return ( credits > cost);
    }
    
    /*
     * By: Juergen Kenneth Roth
     * Id#: 074067
     * June 29, 2007
     */
}
