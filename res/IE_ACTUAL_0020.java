
/**
 * Write a description of class IE_ACTUAL_0020 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IE_ACTUAL_0020
{
    private double cash;
    private int cars;
    private int violators;
    
    public void letCarPass( boolean ePass, double ePassBalance, double tollFee)
    {
        if (ePass = false)
            {
                double newCash = cash + tollFee;
                cash = newCash;
                cars++;
            }
        
        else if (ePassBalance > tollFee)
            {
                double newCash = cash + tollFee;
                cash = newCash;
                cars++;
            }
        
        else if (ePassBalance < tollFee)
            {
                double newCash = cash + ePassBalance;
                cash = newCash;
                double newCars = cars + 1;
                cars = new;
                double newViolators = violators + 1;
                violators = newViolators;
            }
    }
    
    public double getCash()
    {
        return cash;
    }
    
    public int getCars()
    {
        return cars;
    }
    
    public int getViolators()
    {
        return violators;
    }
}
