
/**
 * A program that simulates the ticket-based systems in a movie theater.
 * 
 * @author Joshua Rex T. Cheng   
 * @version July 27, 2007
 */
public class SE_ACTUAL_0319
{
    public String movieName;
    public double ticketPrice, totalSales;
    public int numTicketsSold;
    
    public SE_ACTUAL_0319(String movie, double premiumPrice)
    {
        movieName = movie;
        ticketPrice = premiumPrice;
    }
    
    public SE_ACTUAL_0319(String movie)
    {
        movieName = movie;
        ticketPrice = 130.0;
    }
    
    public Ticket printRegular(String seatNum)
    {
        Ticket t = new Ticket(movieName, seatNum);
        numTicketsSold++;
        totalSales += t.getPrice();
        return t;
    }
    
    public Ticket printPremium(String seatNum)
    {
        Ticket t = new Ticket(movieName,ticketPrice,seatNum)
        numTicketsSold++;
        totalSales += t.getPrice();
        return t;
    }
    
    public void printSummary()
    {
        System.out.println("Movie: "+t.getName);
    }
    
}
