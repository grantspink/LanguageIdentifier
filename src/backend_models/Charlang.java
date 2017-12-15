
package backend_models;

/*
 * This class contains two fields, a name of a language, and an abbreviation
 * Ex. Bulgarian, bg
 */

public class Charlang
{
    public String abbr;
    public String name;
    
    Charlang(String abbr_, String name_)
    {
        abbr = abbr_;
        name = name_;
    }
}