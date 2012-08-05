
/**
 * Write a description of class BRE_ACTUAL_0025 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TicketPrice
public double TicketPrice( int customerAge, int daysShowing )
{
	double cost;

if (customerAge >= 60)
	if ( daysShowing < 8 )
	    cost = 100.0 * 0.80;
  else
	    cost = 120.0 * 0.80;
else
	if ( daysShowing < 8 )
	    cost = 100.0;
  else
	    cost = 120.0;
return cost; 
}
