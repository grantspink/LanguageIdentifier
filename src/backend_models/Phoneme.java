
package backend_models;

/*
 * Representative of a grapheme, contains information relevant for each
 */

public class Phoneme
{
    public String phoneme="";
    public int occurences=0;
    public double frequency=0.0;
    
    public Phoneme(String s)
    {
        phoneme = s;
        occurences=1;
    }
    
    public Phoneme(String s, int n)
    {
        phoneme = s;
        occurences = n;
    }
}
