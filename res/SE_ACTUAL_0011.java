public class SE_ACTUAL_0011
{
    String movie;
    double price;
    String seatNumber;
    
    public SE_ACTUAL_0011( String movieName, double ticketPrice, String seatNum )
    {
        movie = movieName;
        price = ticketPrice;
        seatNumber = seatNum;
    }
    
    public SE_ACTUAL_0011( String movieName, String seatNumber )
    {
        movie = movieName;
        price = 120;
        seatNum = seatNumber;
    }
    
    public String getName()
    {
        return movie;
    }
    
    public double getPrice()
    {
        return price
    }    
    public String getSeat()
    {
        return seatNumber;
    }
    
    public String details()
    {
        return "'Transformers'"+ movieName + "(P" + ticketPrice")" + " - seat " + seatNum;
    }
}
    
    
