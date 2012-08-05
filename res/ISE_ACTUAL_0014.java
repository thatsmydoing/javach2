
/**
 * Write a description of class ISE_ACTUAL_0014 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ISE_ACTUAL_0014
{

    private Product apples;
    private Product oranges;
    private Product pomelos;
    private double totalSales;
    
    public ISE_ACTUAL_0014()
    {
        apples = new Product( "apple", 5.00, 100);
        oranges = new Product( "orange", 6.00, 100);
        pomelos= new Product( "pomelo", 7.00, 20);
        totalSales= 0;
    }

    public void buy (String productName, int qty)
    {
        if ( productName.equals("apple"))
        {
            totalSales += qty *apples.getPrice();
            apples.removeStock(qty);
        }
        else if ( productName.equals("orange"))
        {
            totalSales += qty *oranges.getPrice();
            oranges.removeStock(qty);
        }
        else if ( productName.equals("pomelo"))
        {
            totalSales += qty *pomelos.getPrice();
            pomelos.removeStock(qty);
        }
    } 
    
    public double getPrice ( String name)
    {
        if(name.equals("apple"))
            return apples.getPrice();
        else if (name.equals("orange"))
            return oranges.getPrice();
        else if (name.equals("pomelo"))
    }
    
    public void deliver( String productName, int qty )
    {
        
    }
}
