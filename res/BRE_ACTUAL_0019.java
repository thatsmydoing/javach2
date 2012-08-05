
/**
 * Write a description of class BRE_ACTUAL_0019 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BRE_ACTUAL_0019

public static void main( String args[] )
{
   Scanner in = new Scanner(System.in);
   System.out.println( “Numbers:" );
   double[] nums;
   nums = new double[3];
   for( int i = 0; i < 3; i++ )
      nums[i] = in.nextDouble();

   System.out.println( “Reverse:" );
   for( int i = 2; i >= 0; i-- )
      System.out.println( nums[i] );
}

