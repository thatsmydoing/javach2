
/**
 * Write a description of class IE_ACTUAL_0013 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IE_ACTUAL_0013
{
	private String fuelType;
	private double fuelLevel; //level of fuel in the truck
	private GasStation;
	
	public IE_ACTUAL_0013( String initGasType )
    {
        this.fuelType = fuelType;
    }
    
    public void goTo ( GasStation station )
    {
        this.GasStation = GasStation;
    }
    
    public void loadGas ( double liters ); //in the gasStation
    {
        fuelLevel += liters;
    }    
    
    public void unloadGas ( double liters );
    {
        fuelLevel -= liters;
    }
    
    public String gatGasType()
    {
        return fuelType;
    }
    
    public double getGasLevel()
    {
        return fuelLevel;
    }
    
    public GasStation getCurrStation()
    {
        return GasStation;
    }    
}
