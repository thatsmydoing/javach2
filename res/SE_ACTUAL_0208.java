
/**
 * Write a description of class SE_ACTUAL_0208 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SE_ACTUAL_0208
{
   private double cash;
   private int car;
   private int violator;
public SE_ACTUAL_0208()
{
    cash = 0;
    car = 0;
    violator = 0;
}
/** Allows one car to enter the lane after paying
 * 
 * @param ePass if car is using an ePass to pay
 * @param ePassBalance shows the amount of money remaining in your ePass
 * @param TollFee shows how much money you need in order to pass
 */
public void letCarPass(boolean ePass, double ePassBalance, double tollFee)
{
  if (ePass)
    if (ePassBalance >= tollFee)
        { 
            cash = cash + tollFee;
            car = car + 1
        }
     else
        {
            cash = cash + ePassBalance;
            violator = violator + 1;
            car = car + 1;
        }
   else
       {
         cash = cash + tollFee;
        }            
}
/** Shows how much money one toll lane has collected.*/
public double getCash()
{
    return cash;
}
/** Shows the number of cars that have passed thru the toll lane.*/
public int getCars()
{
    return car;
}
/** Shows the numbet of cars that are violators or did not have enough credit.*/
public int getViolators()
{
    return violators;
}
}
