
/**
 * Write a description of class BRE_ACTUAL_0026 here.
 * 
 * @author Rina Reinoso 
 * @version (a version number or a date)
 */
public class BRE_ACTUAL_0026

  public double cash;
  public int car;
  public int violators;

  public void letCarPass (boolean ePass, double ePassBalance, double tollFee)
 {
  if (ePass)
   {      
  if (ePassBalance >= tollFee)
        { 
        double newEPassBalance = ePassBalance - tollFee;
        ePassBalance = newEPassBalance;
        int countCar = car + 1;
        car = countCar;
        }
    else
        {
        double remainingTollFee = tollFee - ePassBalance; 
        double newCash = cash + remainingTollFee;
        cash = newCash;
        int countCar = car + 1;
        car = countCar;
         }
    }
    else
    {    
     violators = violators + 1; 
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
        
   
