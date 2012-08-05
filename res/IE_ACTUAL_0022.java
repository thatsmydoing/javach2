
/**
 * Write a description of class IE_ACTUAL_0022 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IE_ACTUAL_0022
{
	private double Balance;
	
	public Balance=0.0;
	
	public void deposit(double amount)
	{
	    double newBalance=Balance + amount;
	    Balance=newBalance;
	}
	
	public void withdraw(double amount)
	{
	    double newBalance=Balance-amount;
	    Balance=newBalance;
	}
	
	public double getBalance()
	{
	    return Balance;
	}

}
