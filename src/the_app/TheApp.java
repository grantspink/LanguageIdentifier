
package the_app;

import frontend_viewcontroller.MainViewDisplay;
import frontend_viewcontroller.ModelsAndViewsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheApp implements Runnable
{
    @Override
    public void run()
    {
        MainViewDisplay theMainViewDisplay = null;
        try
        {
            theMainViewDisplay = new MainViewDisplay();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TheApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theMainViewDisplay);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TheApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        theMainViewDisplay.setVisible(true);
    }
}