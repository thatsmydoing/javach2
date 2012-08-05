
/**
 * Write a description of class SE_ACTUAL_0120 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SE_ACTUAL_0120
{
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int count = 1;
    private int count1 = 1;
    private int count2 = 1;
    private int count3 = 1;
    private int count4 = 1;
    private int row=0;
    private int col=0
       
    public SE_ACTUAL_0120(int a, int b, int c, int d)
    {
        x1=a;
        y1=b;
        x2=c;
        y2=d;
     }
     
     public void drawFilled()
     {
       row = (x2 - x1) + 1;
       col = (y2 - y1) + 1;
       
      while(count3 < y1)
      {
           System.out.println(" ");
      }
      while(count4 <= col)
      {
       while(count < x1)
       {
           System.out.print(" ");
           count++;
        }
        while(count2 <= row)
        {
            System.out.print("*");
            count2++;
        }
        count4++;
       }
    }

}
