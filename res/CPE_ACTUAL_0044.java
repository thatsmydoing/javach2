

public class CPE_ACTUAL_0044
{
    Book book1, book2;
    
    public CPE_ACTUAL_0044()
    {
        // The following lines create 2 books by default
        this.book1 = new Book( "Things Fall Apart", "Chinua Achebe" );
        this.book2 = new Book( "Harry Potter and the Deathly Hallows", "J.K. Rowling" );
    }
    
    public void borrowBook( String studentName, String bookTitle )
    {
        if( this.book1.getTitle().equals( bookTitle ) )
        {
            this.book1.setBorrower( studentName );
            System.out.println( "'" + bookTitle + "' was borrowed by " + studentName );
        }
        else if( this.book2.getTitle().equals( bookTitle ) )
        {
            this.book2.setBorrower( studentName );
            System.out.println( "'" + bookTitle + "' was borrowed by " + studentName );
        }
        else
        {
            System.out.println( "'" + bookTitle + "' not found!" );
        }
    }
    
    public void returnBook( String bookTitle )
    {
        if (bookTitle.equalsIgnoreCase (Book.tBook)
        {
            return Book.unmark()
        }
        
        else
        {
            return "warning";
        }
    }
    
    public void printSummary()
    {
        System.out.println( "LIBRARY SUMMARY" );
        System.out.println( "Book: '" + this.book1.getTitle() + "' by " + this.book1.getAuthor() );
        System.out.println( "Borrowed by: " + this.book1.getBorrower() );
        System.out.println( "This book has been borrowed " + this.book1.getTimesBorrowed() + " time(s)" );
        System.out.println();
        System.out.println( "Book: '" + this.book2.getTitle() + "' by " + this.book2.getAuthor() );
        System.out.println( "Borrowed by: " + this.book2.getBorrower() );
        System.out.println( "This book has been borrowed " + this.book2.getTimesBorrowed() + " time(s)" );
    }

 
