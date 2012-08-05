public class CPE_ACTUAL_0013
{
    private double credits, rate;
    private int totalmin;
        
    public CPE_ACTUAL_0013()
    {
        rate=6.50;
        credits=0;
        totalmin=0;
    }
    public void load(double pesos)
    {
        credits+=pesos;       
    }
    public void call(int minutes)
    {
        totalmin+=minutes;
    }
    public double getLoadLeft()
    {
        return credits;
    }
    public int getTotalMinutescalled()
    {
        return totalmin;
    }
    public void printSummary()
    {
        System.out.printf("Credits left: P"+ " 1.2f",credits);
        System.out.println("Total call duration: "+total min+" mins.";
        System.out.println("Rate per call: P"+" 1.2f",rate);
        
    }
    public static void main (String args[])
    {
    }    
}
