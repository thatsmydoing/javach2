
/**
 * Write a description of class ISE_ACTUAL_0048 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ISE_ACTUAL_0048
{
    private int height;
    private int width;

    /**
     * Constructor for objects of class ISE_ACTUAL_0048
     */
    public ISE_ACTUAL_0048( int h, int w)
    {
        height = h;
        width = w;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        for (int i = 1; i <= height; i++)
        {
            if (i == 1 || i == height)
            {
                for (int b = 1; b < width; b++)
                    s += "*";
                s += "*\n";
            }
            else
            {
                s += "*";
                for (int b = 1; b < width; b++)
            }
            
            return s;
        }
    }
  
}
