 package frontend_viewcontroller;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

 /*
  * Class with all the buttons and displays
  */
 
public class MainViewDisplay extends JFrame implements ListSelectionListener
{
    public JList inputLanguageList;
    public DefaultListModel inputLanguageListModel;
    public JScrollPane inputLanguageListScrollPane;
    public JPanel inputLanguageListPanel;
    
    public JList allLanguageList;
    public DefaultListModel allLanguageListModel;
    public JScrollPane allLanguageListScrollPane;
    public JPanel allLanguageListPanel;
    
    public JButton addLanguageButton;
    public JButton removeLanguageButton;
    public JButton wikiInputButton;
    public JButton detectLanguageButton;
    public JButton instructionsButton;
    public JButton clearAllButton;
    
    public JLabel inputLanguageLabel;
    public JLabel allLanguageLabel;
    public JLabel monographLabel;
    public JLabel digraphLabel;
    public JLabel trigraphLabel;
    public JLabel resultLabel;
    public JLabel languageSampleLabel;
    public JLabel descriptionLabel;
    
    public JTextArea languageSampleField;
    public JTextArea descriptionField;
    public JTextArea monographField;
    public JTextArea digraphField;
    public JTextArea trigraphField;
    public JTextArea resultField;
    public JTextArea wikiInputField;
    
    public JScrollPane languageSampleScroll;
    public JScrollPane descriptionScroll;
    public JScrollPane monographScroll;
    public JScrollPane digraphScroll;
    public JScrollPane trigraphScroll;
    public JScrollPane resultScroll;
    public JScrollPane wikiInputScroll;
    
    public JPanel languageSamplePanel;
    public JPanel descriptionPanel;
    public JPanel monographPanel;
    public JPanel digraphPanel;
    public JPanel trigraphPanel;
    public JPanel resultPanel;
    public JPanel wikiInputPanel;
    
    // Sets GUI settings
    public MainViewDisplay() throws IOException
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container mainDisplayPane = getContentPane();
        mainDisplayPane.setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints c;
        ////////////////////////////////////////////////////////
        this.inputLanguageListModel = new DefaultListModel();
        
        this.inputLanguageList = new JList(this.inputLanguageListModel);
        this.inputLanguageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.inputLanguageList.setFixedCellWidth(10);
        
        this.inputLanguageListScrollPane = new JScrollPane(this.inputLanguageList);
        this.inputLanguageListScrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        
        this.inputLanguageListPanel = new JPanel();
        this.inputLanguageListPanel.add(this.inputLanguageListScrollPane);
        this.inputLanguageListPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.inputLanguageListPanel, c);
        ////////////////////////////////////////////////////////
        this.allLanguageListModel = new DefaultListModel();
        this.allLanguageListModel.addElement("<html><b>All</b></html>");
        
        this.allLanguageList = new JList(this.allLanguageListModel);
        this.allLanguageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.allLanguageList.setSelectedIndex(1);
        
        this.allLanguageListScrollPane = new JScrollPane(this.allLanguageList);
        this.allLanguageListScrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
        
        this.allLanguageListPanel = new JPanel();
        this.allLanguageListPanel.add(this.allLanguageListScrollPane);
        this.allLanguageListPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.allLanguageListPanel, c);
        ////////////////////////////////////////////////////////
        this.detectLanguageButton = new JButton("Detect Language");
        this.detectLanguageButton.setEnabled(false);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        mainDisplayPane.add(this.detectLanguageButton, c);
        ////////////////////////////////////////////////////////
        this.addLanguageButton = new JButton("Add File");
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainDisplayPane.add(this.addLanguageButton, c);
        ////////////////////////////////////////////////////////
        this.removeLanguageButton = new JButton("Remove");
        this.removeLanguageButton.setEnabled(false);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        mainDisplayPane.add(this.removeLanguageButton, c);
        ////////////////////////////////////////////////////////
        this.wikiInputButton = new JButton("Add Wiki");
        this.wikiInputButton.setEnabled(false);
        
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 4;
        mainDisplayPane.add(this.wikiInputButton, c);
        /////////////////////////////////////////////////////////
        this.instructionsButton = new JButton("INSTRUCTIONS");
        
        c = new GridBagConstraints();
        c.gridx = 11;
        c.gridy = 1;
        mainDisplayPane.add(this.instructionsButton, c);
        ////////////////////////////////////////////////////////
        this.inputLanguageLabel = new JLabel("Input Languages", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        mainDisplayPane.add(this.inputLanguageLabel, c);
        ////////////////////////////////////////////////////////
        this.allLanguageLabel = new JLabel("All Languages", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 2;
        mainDisplayPane.add(this.allLanguageLabel, c);
        ////////////////////////////////////////////////////////
        this.monographLabel = new JLabel("---------------Monographs---------------", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 5;
        c.gridy = 0;
        c.gridwidth = 2;
        mainDisplayPane.add(this.monographLabel, c);
        ////////////////////////////////////////////////////////
        this.digraphLabel = new JLabel("-----------------Digraphs-----------------", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 7;
        c.gridy = 0;
        c.gridwidth = 2;
        mainDisplayPane.add(this.digraphLabel, c);
        ////////////////////////////////////////////////////////
        this.trigraphLabel = new JLabel("----------------Trigraphs----------------", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 9;
        c.gridy = 0;
        c.gridwidth = 2;
        mainDisplayPane.add(this.trigraphLabel, c);
        ////////////////////////////////////////////////////////
        this.resultLabel = new JLabel("-----------------Result-----------------", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 11;
        c.gridy = 2;
        mainDisplayPane.add(this.resultLabel, c);
        ////////////////////////////////////////////////////////
        
        this.clearAllButton = new JButton("Clear All");
        
        c = new GridBagConstraints();
        c.gridx = 11;
        c.gridy = 4;
        mainDisplayPane.add(this.clearAllButton, c);
        ////////////////////////////////////////////////////////
        this.languageSampleLabel = new JLabel("Language Sample", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        mainDisplayPane.add(this.languageSampleLabel, c);
        ////////////////////////////////////////////////////////
        this.descriptionLabel = new JLabel("Description", JLabel.CENTER);
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        mainDisplayPane.add(this.descriptionLabel, c);
        ////////////////////////////////////////////////////////
        this.languageSampleField = new JTextArea(10,10);
        this.languageSampleField.setLineWrap(true);
        
        this.languageSampleScroll = new JScrollPane(languageSampleField);
        this.languageSampleScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        this.languageSamplePanel = new JPanel();
        this.languageSamplePanel.add(this.languageSampleScroll);
        this.languageSamplePanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        mainDisplayPane.add(this.languageSamplePanel, c);
        ////////////////////////////////////////////////////////
        this.descriptionField = new JTextArea(10,10);
        this.languageSampleField.setWrapStyleWord(true);
        this.descriptionField.setLineWrap(true);
        /////--WINDOWS--/////this.descriptionField.setText("--Choose one--\nA: Select 1 input \nlanguage, and at \nleast 1 database \nlanguage.\n\nB: Select 1 \ndatabase language,\nand at least 1 input \nlanguage.");
        this.descriptionField.setText("--Choose one--\nA: Select 1 input \nlanguage, and atleast 1 database\nlanguage.\n\nB: Select 1 \ndatabase \nlanguage,\nand at least 1 \ninput language.");
        this.descriptionField.setEditable(false);
        
        this.descriptionScroll = new JScrollPane(descriptionField);
        this.descriptionScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        this.descriptionPanel = new JPanel();
        this.descriptionPanel.add(this.descriptionScroll);
        this.descriptionPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        mainDisplayPane.add(this.descriptionPanel, c);
        ////////////////////////////////////////////////////////
        this.monographField = new JTextArea();
        this.monographField.setLineWrap(true);
        this.monographField.setEditable(false);
        
        this.monographScroll = new JScrollPane(monographField);
        this.monographScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        this.monographPanel = new JPanel();
        this.monographPanel.add(this.monographScroll);
        this.monographPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.monographPanel, c);
        ////////////////////////////////////////////////////////
        this.digraphField = new JTextArea();
        this.digraphField.setLineWrap(true);
        this.digraphField.setEditable(false);
        
        this.digraphScroll = new JScrollPane(digraphField);
        this.digraphScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        this.digraphPanel = new JPanel();
        this.digraphPanel.add(this.digraphScroll);
        this.digraphPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 7;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.digraphPanel, c);
        ////////////////////////////////////////////////////////
        this.trigraphField = new JTextArea();
        this.trigraphField.setLineWrap(true);
        this.trigraphField.setEditable(false);
        
        this.trigraphScroll = new JScrollPane(trigraphField);
        this.trigraphScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        this.trigraphPanel = new JPanel();
        this.trigraphPanel.add(this.trigraphScroll);
        this.trigraphPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 9;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.trigraphPanel, c);
        ////////////////////////////////////////////////////////
        this.resultField = new JTextArea();
        this.resultField.setLineWrap(true);
        this.resultField.setEditable(false);
        
        this.resultScroll = new JScrollPane(resultField);
        
        this.resultPanel = new JPanel();
        this.resultPanel.add(this.resultScroll);
        this.resultPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 11;
        c.gridy = 3;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.resultPanel, c);
        ////////////////////////////////////////////////////////
        this.wikiInputField = new JTextArea(1,5);
        AbstractDocument pDoc=(AbstractDocument)wikiInputField.getDocument();
        pDoc.setDocumentFilter(new SizeAndNumberFilter(3));
        
        this.wikiInputScroll = new JScrollPane(wikiInputField);
        
        this.wikiInputPanel = new JPanel();
        this.wikiInputPanel.add(this.wikiInputScroll);
        this.wikiInputScroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
        this.wikiInputScroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        this.wikiInputPanel.setLayout(new GridLayout(0, 1));
        
        c = new GridBagConstraints();
        c.gridx = 4;
        c.gridy = 4;
        c.fill = GridBagConstraints.BOTH;
        mainDisplayPane.add(this.wikiInputPanel, c);
        ////////////////////////////////////////////////////////
        
        /////--WINDOWS--/////
        //this.pack();
        //this.setSize(1250, 400);
        this.setSize(1400, 500);
    }

    // Displays open file window
    public String showOpenDialog()
    {
        JFileChooser jfc = new JFileChooser();
        int status = jfc.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION)
        {
            File theFile = jfc.getSelectedFile();
            String thePath = theFile.getAbsolutePath();
            return thePath;
        }

        return null;
    }

    @Override
    public void valueChanged(ListSelectionEvent lse)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Parses textfield input, for size, and whether it is a number
    static class SizeAndNumberFilter extends DocumentFilter
    {
        private final int max;
        
        public SizeAndNumberFilter(int max)
        {
            this.max = max;
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException
        {
            if (fb.getDocument().getLength() + text.length() < max)
            {
                if(numberTest(text))
                    super.insertString(fb, offset, text, attr);
                else
                    showNumberError();
            }
            else
                showSizeError();
        }
        
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
        {
            int documentLength = fb.getDocument().getLength();
            if (documentLength - length + text.length() < max)
            {
                if(numberTest(text))
                    super.replace(fb, offset, length, text, attrs);
                else
                    showNumberError();
            }
            else 
                showSizeError();
        }
        
        private boolean numberTest(String text)
        {
            try
            {
                Integer.parseInt(text);
                return true;
            }
            catch (NumberFormatException e)
            {
                return false;
            }
        }
        
        private void showSizeError()
        {
            JOptionPane.showMessageDialog(null, "Number is too large");
        }
        
        private void showNumberError()
        {
            JOptionPane.showMessageDialog(null, "You must enter a number");
        }
    }
}



