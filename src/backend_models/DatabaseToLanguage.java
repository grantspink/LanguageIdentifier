
package backend_models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * Reads through database of languages, and puts them into language form
 */

public class DatabaseToLanguage
{
    public static List<String> languageSamples = new ArrayList<>();
    
    // Reads through files containing graphemes and retrieves them
    public static Language readGraphFile(Charlang charlang) throws FileNotFoundException, IOException
    {
        File f = new File(charlang.abbr + "_graphs.txt");
        byte[] data;
        try (FileInputStream fis = new FileInputStream(f))
        {
            data = new byte[(int)f.length()];
            fis.read(data);
        }
        String s = new String(data, "UTF-8");
        Scanner sc = new Scanner(s).useDelimiter("\\s*[,\n]\\s*");
        List<List<Phoneme>> Graphs = new ArrayList<>();
        List<Integer> total_graphs = Arrays.asList(0,0,0);
        for(int i=0; i<Language.prefixList.size(); i++)
        {
            Graphs.add(new ArrayList<Phoneme>());
            while(true)
            {
                String phoneme = sc.next();
                if(phoneme.equals(Language.prefixList.get(i)+"graphs"))
                    break;
                int occurences = sc.nextInt();
                Graphs.get(i).add(new Phoneme(phoneme, occurences));
                total_graphs.set(i, i+1);
            }
        }
        Language lang = new Language(s, Graphs, charlang, total_graphs);
        return lang;
    }
    
    // Reads a file
    public static String readFile(File f) throws IOException
    {
        byte[] data;
        try (FileInputStream fis = new FileInputStream(f))
        {
            data = new byte[(int)f.length()];
            fis.read(data);
        }
        String s = new String(data, "UTF-8");
        return s;
    }
}
