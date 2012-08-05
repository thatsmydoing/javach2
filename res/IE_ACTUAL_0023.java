
/**
 * Write a description of class IE_ACTUAL_0023 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IE_ACTUAL_0023
{
	Bank b = new Bank();
b.deposit( “john”, 200 );
b.withdraw( “marsha”, 100 );
System.out.println( b.getBalance( “john” ) );
System.out.println( b.getBalance( “marsha” ) );
}
