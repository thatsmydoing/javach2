//Lab Exercise 1
public class CPE_ACTUAL_0014
{
    public double Credit;
    public int Call;
    public double Rate;
    
    { Credit = 0; }
    { Call = 0; }
    { Rate = 6.50; }
    
    public void load ( double pesos )
    {
        Credit = Credit + pesos;
    }
    
    public void call ( int minutes )
    {
        Call = Call + minutes;
    }
    
    public double getLoadLeft ()
    {
        return Credit;
    }
    
    public int getTotalMinutesCalled()
    {
        return Call;
    }
    
    public void printSummary()
    {
        System.out.print( "Credit's left: " + "P" );
        System.out.printf( Credit
    }
}
