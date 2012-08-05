public class IE_ACTUAL_0024
{
    Bank b = new Bank();
    b.deposit( “john”, 200 );
    b.withdraw( “marsha”, 100 );
    System.out.println( b.getBalance( “john” ) );
    System.out.println( b.getBalance( “marsha” ) );
}
