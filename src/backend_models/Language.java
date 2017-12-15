
package backend_models;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * Class is representative of a language
 */

public class Language
{
    final int NUMBER_OF_GRAPHS=3;
    public String name;
    public String abbr;
    public String sampleText;
    public List<List<Phoneme>> Graphs = new ArrayList<>();
    public List<Integer> totalGraphs = new ArrayList<>();
    public static List<String> prefixList = Arrays.asList("Mono", "Di", "Tri");
    public List<Double> graphProbs = Arrays.asList(0.0, 0.0, 0.0);
    public List<Double> statProbs = Arrays.asList(0.0, 0.0, 0.0);
    public Double finalProb = 0.0;
    
    // Initializer class
    public Language(String sample_text, Charlang charlang) throws FileNotFoundException
    {
        for(int i=0; i<NUMBER_OF_GRAPHS; i++)
            Graphs.add(new ArrayList<Phoneme>());
        name = charlang.name;
        abbr = charlang.abbr;
        characterSorter(sample_text.toLowerCase());
        wordSorter(sampleText);
        freqSet();
        //graphsToFile();
    }
    
    // Initializer class
    public Language(String sample_text, List<List<Phoneme>> graphs, Charlang charlang, List<Integer> total_graphs) throws FileNotFoundException
    {
        Graphs  = new ArrayList<>(graphs);
        totalGraphs = new ArrayList<>(total_graphs);
        name = charlang.name;
        abbr = charlang.abbr;
        characterSorter(sample_text.toLowerCase());
        freqSet();
    }
    
    // Updates file containing graphemes
    public void graphsToFile() throws FileNotFoundException
    {
        FileWriter out = null;
        try
        {
            File nf = new File(abbr + "_graphs.txt");
            PrintWriter pw = new PrintWriter(new FileOutputStream(nf, true));
            out = new FileWriter(nf);
            String s = "";
            for (int i = 0; i < Graphs.size(); i++)
            {
                for (int j = 0; j < Graphs.get(i).size(); j++)
                {
                    s += (Graphs.get(i).get(j).phoneme + ", " + Graphs.get(i).get(j).occurences + ", ");
                }
                s += ("\n" + prefixList.get(i) + "graphs");
                s += ("\n");
            }
            out.write(s);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Language.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Runs through input text, and seperates graphemes
    private void wordSorter(String sample_text)
    {
        Scanner sc = new Scanner(sample_text);
        
        while(sc.hasNext())
        {
            String input = sc.next();
            for(int i=0; i<Graphs.size(); i++)
            {
                if(input.length()>i)
                {
                    for(int j=0; j<input.length()-i; j++)
                        graphAdder(Graphs.get(i), input.substring(j, j+i+1));
                }
            }
        }
        
        for(int i=0; i<Graphs.size(); i++)
            totalGraphs.add(totalAdder(Graphs.get(i)));
    }
    
    // Handles adding of grapheme to list
    private void graphAdder(List<Phoneme> graphList, String phoneme)
    {
        boolean has_word=false;
        
        for(int i=0; i<graphList.size(); i++)
        {
            if(graphList.get(i).phoneme.equals(phoneme))
            {
                has_word = true;
                graphList.get(i).occurences++;
                break;
            }
        }
        if(!has_word)
            graphList.add(new Phoneme(phoneme));
    }
    
    // Sums phoneme occurences
    private int totalAdder(List<Phoneme> graphList)
    {
        int sum=0;
        for(int i=0; i<graphList.size(); i++)
            sum+=graphList.get(i).occurences;
        return sum;
    }
    
    // Parses text for bad characters
    private void characterSorter(String sample_text)
    {
        String temp = sample_text;
        
        temp = temp.replaceAll("0", "");
        temp = temp.replaceAll("1", "");
        temp = temp.replaceAll("2", "");
        temp = temp.replaceAll("3", "");
        temp = temp.replaceAll("4", "");
        temp = temp.replaceAll("5", "");
        temp = temp.replaceAll("6", "");
        temp = temp.replaceAll("7", "");
        temp = temp.replaceAll("8", "");
        temp = temp.replaceAll("9", "");
        temp = temp.replaceAll("▼", "");
        temp = temp.replaceAll("▲", "");
        temp = temp.replaceAll("]", "");
        temp = temp.replaceAll("±", "");
        temp = temp.replaceAll("→", "");
        temp = temp.replaceAll("←", "");
        temp = temp.replaceAll("@", "");
        temp = temp.replaceAll(",", "");
        temp = temp.replaceAll("#", "");
        temp = temp.replaceAll("&", "");
        temp = temp.replaceAll("%", "");
        temp = temp.replaceAll("^", "");
        temp = temp.replaceAll("″", "");
        temp = temp.replaceAll("′", "");
        temp = temp.replaceAll("/", "");
        temp = temp.replaceAll("|", "");
        temp = temp.replaceAll("·", "");
        temp = temp.replaceAll(":", "");
        temp = temp.replaceAll("!", "");
        temp = temp.replaceAll("'", "");
        temp = temp.replaceAll(">", "");
        temp = temp.replaceAll("<", "");
        temp = temp.replaceAll(";", "");
        temp = temp.replaceAll(",", "");
        temp = temp.replaceAll("-", "");
        temp = temp.replaceAll("_", "");
        temp = temp.replaceAll("“", "");
        temp = temp.replaceAll("°", "");
        temp = temp.replaceAll("„", "");
        temp = temp.replaceAll("ˈ", "");
        temp = temp.replaceAll("”", "");
        temp = temp.replaceAll("‘", "");
        temp = temp.replaceAll("–", "");
        temp = temp.replaceAll("—", "");
        temp = temp.replaceAll("’", "");
        temp = temp.replaceAll("²", "");
        temp = temp.replaceAll("³", "");
        temp = temp.replaceAll("•", "");
        temp = temp.replaceAll("»", "");
        temp = temp.replaceAll("«", "");
        temp = temp.replaceAll("↑", "");
        temp = temp.replaceAll("ʃ", "");
        temp = temp.replaceAll("\"", "");
        temp = temp.replaceAll("\\+", "");
        temp = temp.replaceAll("\\?", "");
        temp = temp.replaceAll("null", "");
        temp = temp.replaceAll("write", "");
        temp = temp.replaceAll("document", "");
        temp = temp.replaceAll(Pattern.quote("《"), "");
        temp = temp.replaceAll(Pattern.quote("》"), "");
        temp = temp.replaceAll(Pattern.quote("*"), "");
        temp = temp.replaceAll(Pattern.quote("("), "");
        temp = temp.replaceAll(Pattern.quote(")"), "");
        temp = temp.replaceAll(Pattern.quote("."), "");
        temp = temp.replaceAll(Pattern.quote("►"), "");
        temp = temp.replaceAll(Pattern.quote("◄"), "");
        temp = temp.replaceAll(Pattern.quote("$"), "");
        temp = temp.replaceAll(Pattern.quote("="), "");
        temp = temp.replaceAll(Pattern.quote("^"), "");
        temp = temp.replaceAll(Pattern.quote("…"), "");
        temp = temp.replaceAll(Pattern.quote("⁉"), "");
        temp = temp.replaceAll(Pattern.quote("†"), "");
        temp = temp.replaceAll(Pattern.quote("½"), "");
        temp = temp.replaceAll(Pattern.quote("ª"), "");
        temp = temp.replaceAll(Pattern.quote("º"), "");
        temp = temp.replaceAll(Pattern.quote("№"), "");
        temp = temp.replaceAll(Pattern.quote("↘"), "");
        temp = temp.replaceAll(Pattern.quote("↙"), "");
        temp = temp.replaceAll(Pattern.quote("´"), "");
        temp = temp.replaceAll(Pattern.quote("{"), "");
        temp = temp.replaceAll(Pattern.quote("}"), "");
        temp = temp.replaceAll(Pattern.quote("£"), "");
        temp = temp.replaceAll(Pattern.quote("]"), "");
        temp = temp.replaceAll(Pattern.quote("["), "");
        temp = temp.replaceAll(Pattern.quote("℞"), "");
        temp = temp.replaceAll(Pattern.quote("ⅱ"), "");
        temp = temp.replaceAll(Pattern.quote("\\"), "");
        List<String> noEnglish = asList("kn", "ar", "bn", "fa", "gu", "he", "hi", "ja", "kn", "ko", "ml", "mr", "ne", "pa", "ta", "te", "th", "ur", "zh");
        if(noEnglish.contains(name))
        {
            temp = temp.replaceAll("a", "");
            temp = temp.replaceAll("b", "");
            temp = temp.replaceAll("c", "");
            temp = temp.replaceAll("d", "");
            temp = temp.replaceAll("e", "");
            temp = temp.replaceAll("f", "");
            temp = temp.replaceAll("g", "");
            temp = temp.replaceAll("h", "");
            temp = temp.replaceAll("i", "");
            temp = temp.replaceAll("j", "");
            temp = temp.replaceAll("k", "");
            temp = temp.replaceAll("l", "");
            temp = temp.replaceAll("m", "");
            temp = temp.replaceAll("n", "");
            temp = temp.replaceAll("o", "");
            temp = temp.replaceAll("p", "");
            temp = temp.replaceAll("q", "");
            temp = temp.replaceAll("r", "");
            temp = temp.replaceAll("s", "");
            temp = temp.replaceAll("t", "");
            temp = temp.replaceAll("u", "");
            temp = temp.replaceAll("v", "");
            temp = temp.replaceAll("w", "");
            temp = temp.replaceAll("x", "");
            temp = temp.replaceAll("y", "");
            temp = temp.replaceAll("z", "");
        }
        temp = temp.replaceAll("	", " ");
        temp = temp.trim().replaceAll(" +", " ");
        sampleText = temp;
    }
    
    // Calculates phoneme frequency
    public final void freqSet()
    {
        for(int i=0; i<Graphs.size(); i++)
        {
            for(int j=0; j<Graphs.get(i).size(); j++)
                Graphs.get(i).get(j).frequency = (double)Graphs.get(i).get(j).occurences/(double)totalGraphs.get(i);
        }
    }
}
