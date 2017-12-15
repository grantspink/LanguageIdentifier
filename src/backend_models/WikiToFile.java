
package backend_models;

import java.io.*;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * This class gets articles from wikipedia, and adds them to my databse files
 */

public final class WikiToFile
{
    // Temporary holding string
    public static String languageSample;
    
    // Takes in languages I want to get more articles for, and number of articles
    public static void wikiToFile(int[] selectedIndices, int n) throws IOException
    { 
        for(int i=0; i<selectedIndices.length; i++)
        {
            languageSample="";
            buildDatabase(CharlangList.charlangs.get(selectedIndices[i]-1), n);
        }
    }
    
    // Gets specific name for each langauge, and number of articles
    // Will try at least 5 times to get a decent article, after which any will do
    public static void buildDatabase(Charlang charlang, int n) throws IOException
    {
        System.out.println(charlang.name);
        for(int i=0; i<n; i++)
        {
            int count = 0;
            while(count<=5)
            {
                if(contentGetter(charlang, true))
                    break;
                else
                    count++;
                if(count==5)
                    contentGetter(charlang,false);
            }
            System.out.println(i);
        }
        writeFile(charlang.abbr);
    }
    
    // Appends the new article to the existing file
    public static void writeFile(String charlang) throws IOException
    {
        PrintWriter output;
        File nf = new File(charlang + ".txt");
        output = new PrintWriter(new FileOutputStream(nf, true));
        output.append(languageSample);
        output.close();
    }
    
    // Gets article from wikipedia, either good or bad depending on code above
    public static boolean contentGetter(Charlang charlang, boolean checkifbody) throws MalformedURLException, IOException, ConnectException
    {
        URL url = new URL("http://" + charlang.abbr + ".wikipedia.org/wiki/Special:Random");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String text = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            text += inputLine;
        }
        if(checkifbody && !text.contains("<ol class=\"references\">"))
            return false;
        text = text.replaceAll("<.*?>", "");
        text = text.replaceAll("\\{.*?\\}", "");
        text = text.replaceAll("\\(.*?\\)", "");
        text = text.replaceAll("\\[.*?\\]", "");
        Language lang = new Language(text, new Charlang(charlang.abbr, charlang.name));
        languageSample += lang.sampleText;
        languageSample.replaceAll("null", "");
        return true;
    }
}
