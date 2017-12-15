package frontend_viewcontroller;

import backend_models.FileGetter;
import backend_models.LanguageIdentifier;
import backend_models.Printer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Has all the functions that make the buttons work
 */

public class ModelsAndViewsController
{
    public MainViewDisplay mainViewDisplay;

    // Adds an input language
    private class AddLanguageAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            String pathToFile = mainViewDisplay.showOpenDialog();
            if (pathToFile == null)
                return;
            LanguageIdentifier.setTestLanguagesPath(pathToFile);
            try
            {
                LanguageIdentifier.addTestLanguage();
            }
            catch (IOException ex)
            {
                Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            mainViewDisplay.inputLanguageListModel.insertElementAt(FileGetter.fileName(pathToFile), mainViewDisplay.inputLanguageListModel.getSize());
            mainViewDisplay.inputLanguageList.setSelectedIndex(mainViewDisplay.inputLanguageListModel.getSize()-1);
            mainViewDisplay.removeLanguageButton.setEnabled(true);
        }
    }
    
    // Removes an input language
    private class RemoveLanguageAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (!mainViewDisplay.inputLanguageList.isSelectionEmpty())
            {
                int indices[] = mainViewDisplay.inputLanguageList.getSelectedIndices();
                for(int i=indices.length-1; i>=0; i--)
                {
                    LanguageIdentifier.testLanguagesPath.remove(i);
                    LanguageIdentifier.testLanguages.remove(i);
                    mainViewDisplay.inputLanguageListModel.remove(indices[i]);
                    mainViewDisplay.languageSampleField.setText("");
                }
                int size = mainViewDisplay.inputLanguageListModel.getSize();
                if (size == 0)
                    mainViewDisplay.removeLanguageButton.setEnabled(false);
            }
            else
                mainViewDisplay.removeLanguageButton.setEnabled(false);
        }
    }
    
    // Gets selected languages, calculates answer, and displays it
    private class DetectLanguageAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            int inputIndices[] = mainViewDisplay.inputLanguageList.getSelectedIndices();
            int allIndices[] = mainViewDisplay.allLanguageList.getSelectedIndices();
            LanguageIdentifier.languageIdentifier(inputIndices, allIndices);
            mainViewDisplay.monographField.setText(Printer.outputter(0));
            mainViewDisplay.digraphField.setText(Printer.outputter(1));
            mainViewDisplay.trigraphField.setText(Printer.outputter(2));
            mainViewDisplay.resultField.setText(Printer.results());
            mainViewDisplay.monographField.setCaretPosition(0);
            mainViewDisplay.digraphField.setCaretPosition(0);
            mainViewDisplay.trigraphField.setCaretPosition(0);
            mainViewDisplay.resultField.setCaretPosition(0);
        }
    }
    
    // Displays instructions
    private class InstructionsAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            JOptionPane.showMessageDialog(null, "This program works by doing the following:\n\n1. Constructs a database of languages from wikipedia articles.\n2. Accepts input file(s) into the program.\n3. Based on how you select the input files and database files, it returns the most probable match to that given language or input.\n4. This program compares two languages based off of the monographs, digraphs, and trigraphs that appear in them.\n\nThere are two ways you can run this program:\n1. Selecting one input language and one or more languages from the database languages.\nIt will compare the input to each of the database languages, and return the most probable match to the input language.\n2. Selecting one database language and two or more languages from the input.\nIt will compare the database language to each of the input languages, and return the most probable match to the database.\n\nThe numbers printed out represent the relative probabilities calculated using either a monograph, digraph, or trigraph analysis.\nThe final answer is then calculated by using a 10-20-70 weighting of these probabilities.\n\nA monograph is a single letter that may appear in a word.\nA digraph is a two letter consecutive string inside a word.\nA trigraph is a three letter consectutive string inside a word.\nEx. The word \"probability\" has:\nMonographs: {p,r,o,b,a,b,i,l,i,t,y}\nDigraphs: {pr,ro,ob,ba,ab,bi,il,li,it,ty}\nTrigraphs: {pro,rob,oba,bab,abi,bil,ili,lit,ity}\n\nThe analysis consists of finding the frequency of the graphemes in the language(s), and the frequency of the graphemes in the input text(s).\nUsing these, we calculate the probability of a random assortment of graphemes from the language giving exactly the assortment of graphemes in the input text.\n(Think monkeys on a typewriter writing Hamlet.)\nFinally, using these absolute probabilities, we calculate the relative probabilities of the users selections by dividing by an appropriate factor.\nMost of the time, the relative probabilities work out such that one language is essentially 100% likely to be a match, compared to any other language.");
        }
    }
    
    // Gets articles from wikipedia
    private class WikiGetterAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            int selectedIndices[] = mainViewDisplay.allLanguageList.getSelectedIndices();
            int n = Integer.parseInt(mainViewDisplay.wikiInputField.getText());
            try
            {
                LanguageIdentifier.addToDatabase(selectedIndices, n);
            }
            catch (IOException ex)
            {
                Logger.getLogger(ModelsAndViewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Clears all displays
    private class ClearDisplayAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            mainViewDisplay.inputLanguageList.clearSelection();
            mainViewDisplay.allLanguageList.clearSelection();
            mainViewDisplay.languageSampleField.setText("");
            mainViewDisplay.monographField.setText("");
            mainViewDisplay.digraphField.setText("");
            mainViewDisplay.trigraphField.setText("");
            mainViewDisplay.resultField.setText("");
        }
    }
    
    // Checks for key input in the article amount field
    KeyListener WikiInputFieldKeyListener = new KeyListener()
    {
        @Override
        public void keyTyped(KeyEvent ke)
        {
            String txt=mainViewDisplay.wikiInputField.getText().trim();
            if(txt.equals(""))
                mainViewDisplay.wikiInputButton.setEnabled(false);
            else if(!mainViewDisplay.allLanguageList.isSelectionEmpty())
                mainViewDisplay.wikiInputButton.setEnabled(true);
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
            String txt=mainViewDisplay.wikiInputField.getText().trim();
            if(txt.equals(""))
                mainViewDisplay.wikiInputButton.setEnabled(false);
            else if(!mainViewDisplay.allLanguageList.isSelectionEmpty())
                mainViewDisplay.wikiInputButton.setEnabled(true);
        }

        @Override
        public void keyReleased(KeyEvent ke)
        {
            String txt=mainViewDisplay.wikiInputField.getText().trim();
            if(txt.equals(""))
                mainViewDisplay.wikiInputButton.setEnabled(false);
            else if(!mainViewDisplay.allLanguageList.isSelectionEmpty())
                mainViewDisplay.wikiInputButton.setEnabled(true);
        }
    };
    
    // Checks for selections in the input list
    ListSelectionListener InputListSelectionListener = new ListSelectionListener()
    {
        @Override
        public void valueChanged(ListSelectionEvent arg0)
        {
            if (mainViewDisplay.inputLanguageList.isSelectionEmpty())
            {
                mainViewDisplay.removeLanguageButton.setEnabled(false);
                mainViewDisplay.detectLanguageButton.setEnabled(false);
            }
            else
            {
                int inputIndices[] = mainViewDisplay.inputLanguageList.getSelectedIndices();
                int allIndices[] = mainViewDisplay.allLanguageList.getSelectedIndices();
                mainViewDisplay.languageSampleField.setText(LanguageIdentifier.testLanguages.get(inputIndices[0]).sampleText);
                mainViewDisplay.removeLanguageButton.setEnabled(true);
                if(allIndices.length>=1 && inputIndices.length==1)
                    mainViewDisplay.detectLanguageButton.setEnabled(true);
                else if(inputIndices.length>=1 && allIndices.length==1)
                    mainViewDisplay.detectLanguageButton.setEnabled(true);
                else if(inputIndices.length>1 && allIndices.length>1)
                    mainViewDisplay.detectLanguageButton.setEnabled(false);
                else
                    mainViewDisplay.detectLanguageButton.setEnabled(false);
            }
            if (!arg0.getValueIsAdjusting())
            {
                if(!mainViewDisplay.inputLanguageList.isSelectionEmpty())
                {
                    String sub;
                    if(LanguageIdentifier.testLanguages.get(mainViewDisplay.inputLanguageList.getSelectedIndex()).sampleText.length()<=200)
                        sub = LanguageIdentifier.testLanguages.get(mainViewDisplay.inputLanguageList.getSelectedIndex()).sampleText;
                    else
                        sub = LanguageIdentifier.testLanguages.get(mainViewDisplay.inputLanguageList.getSelectedIndex()).sampleText.substring(0, 200);
                    mainViewDisplay.languageSampleField.setText(sub);
                }
            }
        }
    };
    
    // Checks for selections in the database list
    ListSelectionListener allListSelectionListener = new ListSelectionListener()
    {
        @Override
        public void valueChanged(ListSelectionEvent arg0)
        {
            String txt=mainViewDisplay.wikiInputField.getText().trim();
            if(txt.equals(""))
                mainViewDisplay.wikiInputButton.setEnabled(false);
            else if(mainViewDisplay.allLanguageList.isSelectionEmpty())
                mainViewDisplay.wikiInputButton.setEnabled(false);
            else
                mainViewDisplay.wikiInputButton.setEnabled(true);
            
            if (mainViewDisplay.allLanguageList.isSelectionEmpty())
                mainViewDisplay.detectLanguageButton.setEnabled(false);
            else
            {
                int inputIndices[] = mainViewDisplay.inputLanguageList.getSelectedIndices();
                int allIndices[] = mainViewDisplay.allLanguageList.getSelectedIndices();
                if(allIndices[0]==0)
                {
                    int selectedIndices[] = new int[LanguageIdentifier.allLanguages.size()];
                    for(int i=0; i<selectedIndices.length; i++)
                        selectedIndices[i]=i+1;
                    mainViewDisplay.allLanguageList.setSelectedIndices(selectedIndices);
                }
                if(allIndices.length>=1 && inputIndices.length==1)
                    mainViewDisplay.detectLanguageButton.setEnabled(true);
                else if(inputIndices.length>=1 && allIndices.length==1)
                    mainViewDisplay.detectLanguageButton.setEnabled(true);
                else if(inputIndices.length>1 && allIndices.length>1)
                    mainViewDisplay.detectLanguageButton.setEnabled(false);
                else
                    mainViewDisplay.detectLanguageButton.setEnabled(false);
            }
        }
    };
    
    // Initializes the functions
    public ModelsAndViewsController(MainViewDisplay aMainViewDisplay) throws IOException
    {
        this.mainViewDisplay = aMainViewDisplay;
        
        LanguageIdentifier.constructDatabase();
        for(int i=0; i<LanguageIdentifier.allLanguages.size(); i++)
            this.mainViewDisplay.allLanguageListModel.insertElementAt(LanguageIdentifier.allLanguages.get(i).name, this.mainViewDisplay.allLanguageListModel.getSize());
        
        this.mainViewDisplay.addLanguageButton.addActionListener(new AddLanguageAction());
        this.mainViewDisplay.removeLanguageButton.addActionListener(new RemoveLanguageAction());
        this.mainViewDisplay.wikiInputField.addKeyListener(WikiInputFieldKeyListener);
        this.mainViewDisplay.inputLanguageList.addListSelectionListener(InputListSelectionListener);
        this.mainViewDisplay.allLanguageList.addListSelectionListener(allListSelectionListener);
        this.mainViewDisplay.detectLanguageButton.addActionListener(new DetectLanguageAction());
        this.mainViewDisplay.clearAllButton.addActionListener(new ClearDisplayAction());
        this.mainViewDisplay.wikiInputButton.addActionListener(new WikiGetterAction());
        this.mainViewDisplay.instructionsButton.addActionListener(new InstructionsAction());
    }
}
