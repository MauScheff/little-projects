/*
 * Author: James Branam-Lefkove, jbranaml@fit.edu
 * Author: Nick Baldwin, baldwinn@fit.edu
 * Author: Maurcio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: fall2008
 */

package trie;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AutoCompleteGUI extends JFrame implements KeyListener
{
   private static final String[] DICTIONARY_WORDS =
         { "Asmita", "Joshua", "Tyler", "Luis", "Ryan", "Michael", "John",
               "Thomas", "Ronald", "James", "Chris", "Miles", "Kevin", "Hamad",
               "James", "Nicholas", "Ronaldo", "Kevin", "Deborah", "Mauricio",
               "Sandra", "Eric", "Bradley", "Shriram", "Chocolate", "Vanilla" };
   
   private static final Font FONT = new Font("monospaced", Font.PLAIN, 20);
   
   private static final String TITLE = "Tries Application: Autocomplete";
   
   private final JTextField theInputField = newTextField(20);
   
   private final JTextArea theSuggestionsBox = readOnlyTextArea();
   
   private final Trie theDictionary = new Trie();
   
   public static void main (String[] args)
   {
      final Autocomplete autoGui = new Autocomplete();
      autoGui.setTitle(TITLE);
      autoGui.setVisible(true);
   }
   
   public Autocomplete ()
   {
      addWindowListener(new WindowDestroyer());
      populateDictionary();
      
      createContentPane(inputComponent(), outputComponent());
   }
   
   public void keyPressed (KeyEvent event)
   {
   }
   
   public void keyReleased (KeyEvent event)
   {
      final String input = getInput();
      
      if (input.length() > 0) {
         update(theSuggestionsBox, theDictionary.getChildren(input));
      } else {
         theSuggestionsBox.setText("");
      }
   }
   
   public void keyTyped (KeyEvent event)
   {
   }
   
   private void createContentPane (JComponent upper, JComponent lower)
   {
      final Container contentPane = getContentPane();
      
      contentPane.setLayout(new BorderLayout());
      contentPane.setSize(new Dimension(700, 530));
      
      contentPane.add(upper, BorderLayout.NORTH);
      contentPane.add(lower, BorderLayout.CENTER);
   }
   
   private String getInput ()
   {
      final String input = theInputField.getText();
      return input;
   }
   
   private JComponent inputComponent ()
   {
      final JPanel panel = new JPanel();
      
      panel.add(theInputField);
      
      return panel;
   }
   
   private JTextField newTextField (int size)
   {
      final JTextField field = new JTextField(size);
      field.addKeyListener(this);
      field.setFont(FONT);
      return field;
   }
   
   private JComponent outputComponent ()
   {
      final JPanel panel = new JPanel();
      panel.add(theSuggestionsBox);
      return panel;
   }
   
   private void populateDictionary ()
   {
      for (String current : DICTIONARY_WORDS) {
         theDictionary.insert(current);
      }
   }
   
   private JTextArea readOnlyTextArea ()
   {
      final JTextArea textArea = new JTextArea();
      textArea.setEditable(false);
      textArea.setFont(FONT);
      return textArea;
   }
   
   private void update (JTextArea textArea, String text)
   {
      textArea.setText(text);
   }
   
   private void update (JTextArea textArea, String[] stringList)
   {
      final StringBuilder builder = new StringBuilder();
      
      for (final String current : stringList) {
         builder.append(current + " ");
      }
      
      update(textArea, builder.toString());
   }
   
   class WindowDestroyer extends WindowAdapter
   {
      public void windowClosing (WindowEvent event)
      {
         System.exit(0);
      }
   }
}
