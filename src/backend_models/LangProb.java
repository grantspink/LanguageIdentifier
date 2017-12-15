
package backend_models;

import java.util.ArrayList;
import java.util.List;

/*
 * Calculates probability that two languages are alike
 */

public class LangProb
{
    public static int anomalies;
    
    // Return the probabilities
    public static List<Double> probReturn(Language anyLang, Language testLang)
    {
        List<Double> probList = new ArrayList<>();
        List<Double> statList = new ArrayList<>();
        
        for(int i=0; i<anyLang.Graphs.size(); i++)
        {
            statList.add(probSet(anyLang.Graphs.get(i), testLang.Graphs.get(i)));
            probList.add(statList.get(i)*testLang.totalGraphs.get(i));
        }
        return probList;
    }
    
    // Checks if a phoneme is shared between two languages
    public static int hasString(String testGraph_phoneme, List<Phoneme> anyGraph)
    {
        int temp = -1;
        for(int i=0; i<anyGraph.size(); i++)
        {
            if(anyGraph.get(i).phoneme.equals(testGraph_phoneme))
            {
                temp = i;
                return temp;
            }
        }
        return temp;
    }
    
    // Calculates probability
    public static double probSet(List<Phoneme> anyGraph, List<Phoneme> testGraph)
    {
        double sum=0;
        anomalies=0;
        
        for(int i=0; i<testGraph.size(); i++)
        {
            int casetest = hasString(testGraph.get(i).phoneme, anyGraph);
            if(casetest == -1)
            {
                anomalies+=testGraph.get(i).occurences;
                sum+=testGraph.get(i).frequency*(Math.log10(0.0000001));
            }
            else
                sum+=testGraph.get(i).frequency*(Math.log10(anyGraph.get(casetest).frequency));
        }
        return sum;
    }
}