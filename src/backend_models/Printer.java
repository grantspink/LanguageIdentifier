
package backend_models;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This class creates the output for the text boxes
 */

public class Printer
{
    public static List<List<Double>> statProbs = new ArrayList<>();
    public static List<Language> testLangs = new ArrayList<>();
    public static List<Language> allLangs = new ArrayList<>();
    
    // Mono, Di, and Tri textboxes
    public static String outputter(int n)
    {
        String s = "";
        final int temp = n;
        Collections.sort(allLangs, new Comparator<Language>()
        {
            @Override
            public int compare(Language l1, Language l2)
            {
                return l2.statProbs.get(temp).compareTo(l1.statProbs.get(temp));
            }
        });
        
        DecimalFormat df = new DecimalFormat("0.00E0");  
        for (int j = 0; j < allLangs.size(); j++)
            s+=(df.format(allLangs.get(j).graphProbs.get(n)) + " - " + allLangs.get(j).name + "\n");
        return s;
    }
    
    // Results textbox
    public static String results()
    {
        String s = "";
        for(int i=0; i<allLangs.size(); i++)
            allLangs.get(i).finalProb=allLangs.get(i).graphProbs.get(0)*0.1+allLangs.get(i).graphProbs.get(1)*0.2+allLangs.get(i).graphProbs.get(2)*0.7;
        Collections.sort(allLangs, new Comparator<Language>()
        {
            @Override
            public int compare(Language l1, Language l2)
            {
                return l2.finalProb.compareTo(l1.finalProb);
            }
        });
        
        DecimalFormat df = new DecimalFormat("0.00E0");  
        for (int j = 0; j < allLangs.size(); j++)
            s+=(df.format(allLangs.get(j).finalProb) + " - " + allLangs.get(j).name + "\n");
        return s;
    }
}
