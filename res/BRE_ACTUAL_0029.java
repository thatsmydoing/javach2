
/**
 * Write a description of class BRE_ACTUAL_0029 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of class Console here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner; //to read input from 6e914b3110d2648b872a404a868ca36e
public class Console
{
    private Hotel hilton;
    
    public static void main ( String args [] )
    {
        Hotel h; //decalaration
        Receptionist r;
        Scanner input;
        
        h = new Hotel();
        r = new Receptionist( h );
        input = new Scanner ( System.in );
        
      do{
        System.out.println( "1. check in" );
        System.out.println( "2. check out" );
        System.out.println( "3. Quit" );
        System.out.print( "Your choice?" );
        choice = input.nextInt();
       
       BRE_ACTUAL_0029 ( choice )
        case 1:
            System.out.println( "checking in..." )
            break;
            System.out.println();
            
        case: 2 
            System.out.println( "checking out..." )
        case: 3
            System.out.println( "Thank you. Goodbye" );
        
        default :
            System.out.println( " Try again. ";
            break;
         
        } while ( choice != 3);

    }
}
