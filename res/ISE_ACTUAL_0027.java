/**
 * An aggregate of at most 50 WordData objects in an array
 * 
 * @author (name)
 * @version (date)
 */
public class ISE_ACTUAL_0027
{
    WordData[] words;
    public static int MAX = 50;
    private int totNumWordsEnt;
    private int totNumUniqWords;
    private int count;

    public ISE_ACTUAL_0027()
    { 
        totNumWordsEnt = 0;
        totNumUniqWords = 0;
        count = 0;
    }

    public void addWord( String w )
    {
        WordData temp = findWord(w);
        
        
        if ( temp == null )
        {
            words[count++] = new WordData(w);
        }
        else if ( temp != null)
        {
             temp.addFrequency();
        }
            
            
    }
    
    public WordData findWord( String w )
    {
        WordData temp = null;
        
        for ( int i = 0; i < count; i++ )
        {
            if ( words[i].WordData().equals(w) )
            {
                return words[i];
            }
            
        }
        
        return null;
        
        
    }
    
    public int getUniqueWords()
    {
        
    }
    
    public int getTotalWords()
    {
        count = 
        return  
    }
    
    public void printUpdate()
    {
        System.out.println("Total Word Count: " + getTotalWords() );
        System.out.println("Unique Word Count: " + getUniqueWords() );
    }
    
    public String getMostFrequentWord()
    {
        
    }
    
    public int getMaxFrequency()
    {
        
    }
    
    public void printExit()
    {
        if ( getMostFrequentWord() != null )
            System.out.println("Most Frequent Word: " + getMostFrequentWord() + " with frequency of " + getMaxFrequency() );
    }
}
