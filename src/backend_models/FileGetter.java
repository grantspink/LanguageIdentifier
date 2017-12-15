package backend_models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * Handles reading of files
 */

public class FileGetter
{
    // Returns file content
    public static String readFile(String filePath) throws IOException
    {
        if(filePath!=null)
            return new String(Files.readAllBytes(Paths.get(filePath)));
        else
            return null;
    }
    
    // Returns file name without extension
    public static String fileName(String filePath)
    {
        File f = new File(filePath);
        return f.getName();
    }
}