/**
 * An aggregate of at most 50 WordData objects in an array
 * 
 * @author (name)
 * @version (date)
 */
public class ISE_ACTUAL_0025
{
    private WordData[] words;
    private int wordCount;
    private int uWord;
    private int maxFreq;
    public static final int MAX = 50;// declare instance fields here, include an array

    public ISE_ACTUAL_0025()
    {
        words = new WordData[MAX];
        wordCount = 0;
        uWord = 0;
        maxFreq = 0;// initialize instance fields here
    }

    public void addWord( String w )
    {
        for (int i = 0; i < uWord; i++)
        {
            if (words[i].getWord().equals(w))
                words[i].addFrequency();
        }
        wordCount++;
        // place code here
    }
    
    public WordData findWord( String w )
    {
        for (int i = 0; i < uWord; i++)
        {
            if (words[i].getWord().equals(w))
                return words[i];
        }// place code here
    }
    
    public int getUniqueWords()
    {
        return uWord;// place code here
    }
    
    public int getTotalWords()
    {
         return wordCount;// place code here
    }
    
    public void printUpdate()
    {
        System.out.println("Total Word Count: " + getTotalWords() );
        System.out.println("Unique Word Count: " + getUniqueWords() );
    }
    
    public String getMostFrequentWord()
    {
        for(i = 0; i < uWord; // place code here
    }
    
    public int getMaxFrequency()
    {
        // place code here
    }
    
    public void printExit()
    {
        if ( getMostFrequentWord() != null )
            System.out.println("Most Frequent Word: " + getMostFrequentWord() + " with frequency of " + getMaxFrequency() );
    }
}
