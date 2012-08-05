import java.util.ArrayList
public class SE_ACTUAL_0161
{
    private ArrayList<BankAccount> accounts;
    
    public SE_ACTUAL_0161()
    {
        accounts = new ArrayList<BankAccount>();
        for(int i = 0; i < 5; i++)
        {
            accounts.add(new BankAccount("Acct" + i, 1000));
        }
    }
    
    public BankAccount findAccount(String name)
    {
        for(BankAccount b : accounts)
        {
            if(name.equals(b.getAcctName()))
            {
                return b;
            }
        }
    }
   
    public void deposit( String name, double amt)
    {
        BankAccount b = findAccount(name);
        b.deposit(amt);
    }     
    public void withdraw( String name, double amt)
    {
        BankAccount b = findAccount(name);
        b.withdraw(amt);
    }    
    public double getBalance(String name)
    {
        BankAccount b = findAccount(name);
        return b.getBalance();
    }
}
