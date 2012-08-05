
/**
 * Classes for bus objects that loads and unloads passengers.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class ISE_ACTUAL_0013
{

   private int capacity;
   private int numPassengers;
   private double fareCollected;
   private double fare = 22.50;
   
   public ISE_ACTUAL_0013( int dCapacity )
   {
       if ( dCapacity == 0 )
       {
           capacity = 100;
       }
       else 
       {
           capacity  = dCapacity;
       }
       numPassengers = 0;
   }
   
   public void loadPassengers( int count )
   {
       if ( count <= capacity )
       {
           capacity = capacity + count;
           fareCollected = 
       }
       else
       {
           capacity = dCapacity;
           System.out.println( "Some passengers were not loaded.");
       }
       numPassengers = numPassengers + count;
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
