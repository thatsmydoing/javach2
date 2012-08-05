
/**
 * Write a description of class IE_ACTUAL_0028 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IE_ACTUAL_0028
{
    
    public double intRate;
    public double interest;
    public int years;
    public double balance;
    balance = 100.00

    public void applyYearlyInterest( int numYears )
    {
        double interest;
        int i;
        for( i = 1; i <= numYears; i++)
        {
            interest = balance*intRate/100;
            balance += interest;
            System.out.print( "Year " + i + "- interest: "+ interest );
            System.out.println( ", balance:" + balance );
            years++;
        }
}

}
