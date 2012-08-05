public class SE_ACTUAL_0010
{
	String movieName;
	double premiumPrice;
	int ticketsSold;
	int totalSales
    
    public SE_ACTUAL_0010( String movieName, double premiumPrice )
    {
    	this.movieName=movieName;
    	this.premiumPrice=premiemPrice;
    	// method body
    }
    
    public SE_ACTUAL_0010( String movieName )
    {
    	this.movieName=movieName;
    	premiumPrice=130.00;
    	// method body
    }
    
    public Ticket printRegular( String seatNum )
    {
        Ticket t = new Ticket( this.movie, seatNum );
        this.totalSales += t.getPrice();
        this.ticketsSold++;
        return t;
    }
    
    public Ticket printPremium( String seatNum )
    {
    	// method body
    }
    
    public void printSummary()
    {
    	// method body
    }
}
