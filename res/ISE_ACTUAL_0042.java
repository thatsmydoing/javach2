
/**
 * Write a description of class ISE_ACTUAL_0042 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ISE_ACTUAL_0042
{
    // instance variables - replace the example below with your own
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     * Constructor for objects of class ISE_ACTUAL_0042
     */
    public ISE_ACTUAL_0042(int a, int b, int c, int d)
    {
        // initialise instance variables
        x1 = a;
        y1 = b;
        x2 = c;
        y2 = d;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void drawFilled()
    {
        String a = "";
        String b = "";
        for (int g=0;g<y1;g++)
        {
            a+="\n";
        }
        System.out.print(a);
        a="";
        for ( int h=0; h<y2;h++)
        {
            for ( int i=0; i<x2; i++)
            {
                b+=" ";
                a+="*";
            }
            System.out.println(b+a);
            a = "";
            b = "";
        }
    }
    
    public void drawHollow()
    {
        String a = "";
        String b = "";
        for (int g=0;g<y1;g++)
        {
            a+="\n";
        }
        System.out.print(a);
        a="";
        for ( int h=0; h<y2;h++)
        {
            for ( int i=0; i<x2; i++)
            {
                for(int k=;i!=1 && i!=x2;k++)
                {
                b+=" ";
                a+="*";
                } 
            }
            System.out.println(b+a);
            a = "";
            b = "";
        }
    }
}
