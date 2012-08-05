
/**
 * Classes for bus objects that loads and unloads passengers.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class IE_ACTUAL_0021
{
   private int numPassengers;
   public int capacity;
   public double fare;
   public IE_ACTUAL_0021()
   {
       capacity = 50;
       numPassengers = 0;
       fare = 22.50;
   }
   public IE_ACTUAL_0021( int maxPassengers )
   {
       capacity = maxPassengers;
       numPassengers = 0;
   }
   public void ( int seatsLeft )
   {
       seatsLeft = capacity - numPassengers;
    }
   public void loadPassengers( int count )
   {
       if ( count <= seatsLeft )
       {
           numPassengers = numPassengers + count;
           seatsLeft = capacity - numPassengers;
        }
        else
        {
            numPassengers = capacity;
            seatsLeft = 0;
            System.out.println( "Some passengers were not loaded." )
        }
   }
   
   public void unloadAll()
   {
       numPassengers = 0;
   }
   
   public int getPassengerCount()
   {
       return numPassengers;
   }
}
