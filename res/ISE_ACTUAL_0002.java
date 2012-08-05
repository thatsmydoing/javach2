
/**
 * Creates a class ISE_ACTUAL_0002.
 * 
 * Author : Ken Lee
 * Version 1.0 -- 07/27/07
 */
public class ISE_ACTUAL_0002
{
    private String nameOfMovie, numberOfSeat;
    private double priceOfTicket;
    private final double defaultPrice=120;
    public ISE_ACTUAL_0002(String movieName,double ticketPrice, String seatNumber)
    {
        nameOfMovie=movieName;
        priceOfTicket=ticketPrice;
        numberOfSeat=seatNumber;
    }
    public ISE_ACTUAL_0002(String movieName, String seatNumber)
    {
        nameOfMovie=movieName;
        priceOfTicket=defaultPrice;
        numberOfSeat=seatNumber;
    }
    public String getName()
    {
        return nameOfMovie;
    }
    public double getPrice()
    {
        return priceOfTicket;
    }
    public String getSeat()
    {
        return numberOfSeat;
    }
    public String details()
    {
        String a=String.format("%2.2f",priceOfTicket);
        String statement="'"  +nameOfMovie+  "' ("  +a+  ") - seat " + numberOfSeat;
        return 
    }
}
