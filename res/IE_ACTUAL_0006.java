
/**
 * Write a description of class IE_ACTUAL_0006 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class IE_ACTUAL_0006
{

    private String title;
    private String author;
    private String borrower;
    private int timesBorrowed;

    public IE_ACTUAL_0006 ( String initTitle, String initAuthor )
    {
        title = initTitle;
        author = initAuthor;
    }
    
    public void setBorrower( String borrowerName )
    {
        borrower = borrowerName;
    }
    
    public void unmark()
    {
        borrower = new.String( "-" );
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getAuthor()
    {
        return author;
    }
    
    public String getBorrower()
    {
        return borrower;
    }
    
    public int getTimesBorrowed()
    {
        return timesBorrowed;
    }
}
