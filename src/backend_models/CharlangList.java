
package backend_models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class creates a list of charlang objects from the database of languages I have
 */

public class CharlangList
{
    public static List<Charlang> charlangs = new ArrayList<>();
    
    public static void charlangList() throws IOException
    {
        String lang_path = "Languages.txt";
        String content = new String(Files.readAllBytes(Paths.get(lang_path)));
        try (Scanner sc = new Scanner(content))
        {
            while(sc.hasNext())
            {
                String name = sc.next();
                String abbr = sc.next();
                charlangs.add(new Charlang(abbr, name));
            }
        }
    }
}
