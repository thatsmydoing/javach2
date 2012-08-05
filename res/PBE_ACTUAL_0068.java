
/**
 * PBE_ACTUAL_0068 [Class] (1)
 * 
 * @author  Truthie Holgado
 * @version September 12, 2008
 */

public class PBE_ACTUAL_0068
{
    // instance variables - replace the example below with your own
    String event;
    String holder;
    double time;

    /**
     * Initializes the instance fields to the values of the corresponding parameters
     * @param   newEvent
     * @param   newHolder
     * @param   newTime
     */
    
    public PBE_ACTUAL_0068( String newEvent, String newHolder, double newTime )
    {
        // initialise instance variables
        event = newEvent;
        holder = newHolder;
        time = newTime;
    }

    /**
     * Updates the values of the record holder and the record time of an existing world record
     * @param   newHolder
     * @param   newTime
     */
    
    public void updateRecord( String newHolder, double newTime )
    {
        // put your code here
        newHolder = new event;
        newTime = new time;
    }
    
    /**
     * Returns the description of the event
     * @return  event
     */
    
    public String getEvent()
    {
        return event;
    }
    
    /**
     * Returns the name of the record holder
     * @return  holder
     */

    public String getHolder()
    {
        return holder;
    }
    
    /**
     * Returns the record time
     * @return time;
     */
    
    public double getTime()
    {
        return time;
    }
    
}
