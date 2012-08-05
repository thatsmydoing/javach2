
/**
 * This program simulates a mobile phone while applying conditional statements.
 * 
 * @author Nicole Guloy
 * @version July 13, 2007
 */

public class CPE_ACTUAL_0033
{
    double credit, callRate, passedLoad;
    int totalMinutes, textMessage;
    
    public CPE_ACTUAL_0033()
    {
        credit = 0;
        callRate = 6.50;
        totalMinutes = 0;
        textMessage = 0;
    }
    
    public boolean load (double pesos)
    {
        if (pesos >= 25)
        {
            credit = credit + pesos;
        }
        return (pesos >= 25);
    }
    
    public boolean call (int minutes)
    {
        if (minutes*callRate >= credit)
        {
        totalMinutes = totalMinutes + minutes;
        credit -= minutes*callRate;
        }
        return (minutes*callRate >= credit + minutes*callRate);
    }
    
    public boolean sendTextMessage ()
    {
        if (credit >= 1)
        {
        textMessage = textMessage + 1;
        credit = credit - 1;
        }
        return (credit+1 >= 1);
    }
    
    public double getLoadLeft()
    {
        return credit;
    }
    
    public int getTotalMinutesCalled()
    {
        return totalMinutes;
    }
    
    public int getNumTextMessages()
    {
        return textMessage;
    }
    
    public boolean changeRate (double newCallRate)
    { 
        if (newCallRate <= 10 && newCallRate > 0) 
        {
        callRate = newCallRate;
        }
        return (newCallRate <= 10 && newCallRate > 0);
    }
    
    public void passLoadTo (CPE_ACTUAL_0033 dest, double amount)
    {
        if ((amount + 1) > credit || amount < 1)
        {
            return;
        }
        else
        {
            credit  = credit - amount - 1;
            passedLoad = passedLoad + amount;
            dest.credit = dest.credit + amount;
        }
    }
        
    public void printSummary()
    {
        System.out.printf("Credits left: P%.2f \n", credit);
        System.out.printf("Total call duration: %d", totalMinutes);
        System.out.print(" mins. \n");
        System.out.printf("Rate per call: P%.2f \n", callRate);
        System.out.printf("Number of text messages sent: %d \n", textMessage);
        System.out.printf("Total amount of load passed: P%.2f", 
        System.out.print(" \n\n");
    }
}
