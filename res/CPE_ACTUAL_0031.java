public class CPE_ACTUAL_0031
{
private double credits;
private int TotalMinutesCalled;
private double ratepercall;
private int TotalTextMessages;

public CPE_ACTUAL_0031()
{
credits = 0;
TotalMinutesCalled = 0;
ratepercall = 6.50;
TotalTextMessages = 0;
}


public void load ( double pesos  )
{
credits = credits + pesos;
}
public void call( int minutes  )
{
TotalMinutesCalled = TotalMinutesCalled + minutes;
}
public void sendTextMessage ( int messages )
{
TotalTextMessages = TotalTextMessages + messages; 
}
public double getLoadLeft() 
{
return credits; 
}
public int getTotalMinutesCalled()
{
return TotalMinutesCalled;
}
public int getTotalTextMessages()
{
return TotalTextMessages;
}
public void printSummary()
{
System.out.printf ("Credits Left:P %5.2f \n",getLoadLeft());
System.out.printf ("Total Call Duration: %2.0f minutes \n", getTotalMinutesCalled() );
System.out.println( "Rate Per Call: P 6.50");
System.out.println(" Number of Text Messages Sent: %2.0f messages \n", getTotalTextMessages() ); 
}
public void credits(double num)
{
        if (call<=load)
            load=load - call;
        else if (cal
        else if (
     
            
    }

}
