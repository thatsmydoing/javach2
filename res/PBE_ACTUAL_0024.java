
/**
 * Write a description of class PBE_ACTUAL_0024 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PBE_ACTUAL_0024
{
    public static void main (String args[])
    {
        TollLane t = new TollLane;
        EPass tag1 = new EPass();
        EPass tag2 = new EPass();
        tag1.getload(100.00);
        
        t.letCarPass(tag1, 20.00);
        t.letCarPass(tag1, 30.00);
        t.letCarPass(tag2, 20.00);
        t.letCarPass(null, 5.55);
    }
}
