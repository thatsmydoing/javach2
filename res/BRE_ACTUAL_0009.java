public class D

public void applyYearlyInterest( int numYears )
{
   double balance;
   double interest;
   int year;
   int i;
   for( i = 1; i <= numYears; i++)
   {
     interest = balance*intRate/100;
     balance += interest;
     System.out.print( “Year ” + i + “- interest: “+ interest );
     System.out.println( “, balance:” + balance );
	 years++;
   }
}
