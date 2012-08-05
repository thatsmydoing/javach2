
/**
 * Write a description of class CPE_ACTUAL_0022 here.
 * 
 * @author Agaptio Feliciano
 * @version June 27, 2007
 */
public class CPE_ACTUAL_0022
{
    private double creditsLeft;
    private int callDuration;
    private double callRate;
    private int text;
    public CPE_ACTUAL_0022(){
        
        creditsLeft = 0;
        callDuration = 0;
        callRate = 6.50;
        text = 0;
    }
    public void load(double pesos){
     creditsLeft = pesos;
        
    }
    public void call(int minutes){
        
     callDuration = callDuration+minutes;
     creditsLeft = creditsLeft-(minutes*callRate);
    }   
    public double getLoadLeft(){
     return  creditsLeft;   
    }
    public int getTotalMinutesCalled(){
        
        return callDuration;
    }
    public void changeRate(double newCallRate){
        callRate=newCallRate;   
        
    }
    public void sendTextMessage(){
       if(
     creditsLeft -=  1;
     text = text+1;
    }
    public int getNumTextMessages()
    {
        
     return text;   
    }
    public void printSummary(){
     System.out.printf("Credits left: P%5.2f\n", creditsLeft);   
     System.out.printf("Total Call Duration: P %3d\n", callDuration);   
     System.out.printf("Rate per Call: P%5.2f\n", callRate);   
     System.out.printf("Number of text message(s) sent: %2d\n", text);   
     
    }
}
