
/**
 * Write a description of class SE_ACTUAL_0021 here.
 * 
 * @author Nathan Earl Uy 
 * @version July 17, 2008
 */
public class SE_ACTUAL_0021
{
    // instance variables - replace the example below with your own
    private int Cars;
    private double Cash;
    private int Violators;

    /**
     * Constructor for objects of class SE_ACTUAL_0021
     */
    public SE_ACTUAL_0021()
    {
        // initialise instance variables
        Cars = 0;
        Cash = 0;
        Violators = 0;
    }

    /**
     * letCarPass - lets a car pass the toll gate and collects the toll fee from them whether its in the form of Cash or ePass 
     * 
     * @param  ePass tells whether the car will pay in the form of cash or ePass
     * @param  ePassBalance tells the balance of the car's ePass
     * @param  tollFee is the amount of money needed to be paid inorder for a car to pass the toll gate
     */
    public void letCarPass(boolean ePass, double ePassBalance, double tollFee)
    {
        // put your code here
        if (ePass)
        {
            if (ePassBalance >= tollFee)
            {
                Cash = Cash + tollFee
                Cars = Cars + 1
            }
            if (ePassBalance < tollFee)
            {
                Cash = Cash + ePassBalance
                Violators = Violators + 1
            }
        }
        else
        {
            Cash = Cash + tollFee
            Cars = Cars + 1
        }
    }
    /**
     * getCash - returns the amount of Cash collected in the toll gate
     * 
     * @return Cash is the amount of cash collected
     */
    public double getCash()
    {
        return Cash
    }
    /**
     * getCars - returns the number of cars that passed the toll gate
     * 
     * @return Cars is the number of cars that passed the toll gate
     */
    public int getCars()
    {
        return Cars
    }
    /**
     * getViolators - returns the number of violators that passed the toll gate
     * 
     * @return Violators is the number of violators that passed the toll gate
     */
    public int getViolators()
    {
        return Violators
    }
}
