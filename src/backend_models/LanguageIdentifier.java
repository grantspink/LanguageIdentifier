
package backend_models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Handles creation and implementation of all backend models
 */

public class LanguageIdentifier
{
    public static List<String> testLanguagesPath = new ArrayList<>();
    public static List<Language> testLanguages = new ArrayList<>();
    public static List<Language> allLanguages = new ArrayList<>();
    
    // Retrieves information from files, puts into language form
    public static void constructDatabase() throws IOException
    {
        CharlangList.charlangList();
        for(int i=0; i<CharlangList.charlangs.size(); i++)
            allLanguages.add(DatabaseToLanguage.readGraphFile(CharlangList.charlangs.get(i)));
    }
    
    // Retrieves and adds articles from wikipedia, appends to languages list
    public static void addToDatabase(int selectedIndices[], int n) throws IOException
    {
        WikiToFile.wikiToFile(selectedIndices, n);
        for(int i=0; i<selectedIndices.length; i++)
        {
            int s = selectedIndices[i]-1;
            allLanguages.set(s, new Language(DatabaseToLanguage.readFile(new File(CharlangList.charlangs.get(s).abbr + ".txt")), CharlangList.charlangs.get(s)));
            allLanguages.get(s).graphsToFile();
        }
    }
    
    // Adds and stores a file path for each input you add
    public static void setTestLanguagesPath(String filePath)
    {
        testLanguagesPath.add(filePath);
    }
    
    // Adds your input file into a language
    public static void addTestLanguage() throws IOException
    {
        int size = testLanguagesPath.size()-1;
        testLanguages.add(new Language(FileGetter.readFile(testLanguagesPath.get(size)), new Charlang("Testcase: " + size, FileGetter.fileName(testLanguagesPath.get(size)))));
    }
    
    // Takes in list selection input, handles calling of required functions
    public static void languageIdentifier(int inputIndices[], int allIndices[])
    {
        List<Language> testLangs = new ArrayList<>();
        List<Language> allLangs = new ArrayList<>();
        for(int i=0; i<inputIndices.length; i++)
            testLangs.add(testLanguages.get(inputIndices[i]));
        for(int i=0; i<allIndices.length; i++)
            allLangs.add(allLanguages.get(allIndices[i]-1));
        
        if(testLangs.size()>allLangs.size())
            languageCalculator(allLangs, testLangs);
        else
            languageCalculator(testLangs, allLangs);
    }
    
    // Handles the bulk of the calculation work
    public static void languageCalculator(List<Language> testLangs, List<Language> allLangs)
    {
        List<List<Double>> graphProbs = new ArrayList<>();
        for (int i = 0; i < testLangs.get(0).Graphs.size(); i++)
            graphProbs.add(new ArrayList<Double>());

        for (int i = 0; i < allLangs.size(); i++)
        {
            List<Double> temp = LangProb.probReturn(allLangs.get(i), testLangs.get(0));
            for (int j = 0; j < 3; j++)
                graphProbs.get(j).add(temp.get(j));
        }

        List<Double> largestProbs = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            largestProbs.add(graphProbs.get(i).get(0));
            for (int j = 0; j < graphProbs.get(i).size(); j++)
            {
                if (graphProbs.get(i).get(j) > largestProbs.get(i))
                    largestProbs.set(i, graphProbs.get(i).get(j));
            }
        }
        
        List<List<Double>> statProbs = new ArrayList<>();
        for(int i=0; i<graphProbs.size(); i++)
        {
            statProbs.add(new ArrayList<Double>());
            for(int j=0; j<graphProbs.get(i).size(); j++)
                statProbs.get(i).add(graphProbs.get(i).get(j));
        }
        
        List<Double> probSums = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            probSums.add(0.0);
            for (int j = 0; j < graphProbs.get(i).size(); j++)
            {
                graphProbs.get(i).set(j, Math.pow(10, (graphProbs.get(i).get(j) - largestProbs.get(i))));
                probSums.set(i, probSums.get(i) + graphProbs.get(i).get(j));
            }
        }

        for (int i = 0; i < allLangs.size(); i++)
        {
            for (int j = 0; j < graphProbs.size(); j++)
            {
                allLangs.get(i).graphProbs.set(j, graphProbs.get(j).get(i) / probSums.get(j));
                allLangs.get(i).statProbs.set(j, statProbs.get(j).get(i));
            }
        }

        Printer.allLangs = allLangs;
        Printer.testLangs = testLangs;
        Printer.statProbs = statProbs;
    }
}
