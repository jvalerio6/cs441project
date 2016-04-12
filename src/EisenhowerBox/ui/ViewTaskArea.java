/**
 * 
 */
package EisenhowerBox.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * @author erikkalan
 *
 */
public class ViewTaskArea extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 30L;
	protected static ViewTaskArea instance;
	
	protected final JTextPane jtxpn1 = new JTextPane();
	protected final JTextPane jtxpn2 = new JTextPane();
	protected final JTextPane jtxpn3 = new JTextPane();
	protected final JTextPane jtxpn4 = new JTextPane();
	
	protected final JScrollPane jscpn1 = new JScrollPane();
	protected final JScrollPane jscpn2 = new JScrollPane();
	protected final JScrollPane jscpn3 = new JScrollPane();
	protected final JScrollPane jscpn4 = new JScrollPane();
	
	
	
	public ViewTaskArea() {
		initComponents();
		
		
    }
	
	private void initComponents(){
		
		final JPanel viewPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        viewPanel.add(new JScrollPane(jtxpn1,
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER),gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        viewPanel.add(new JScrollPane(jtxpn2,
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        viewPanel.add(new JScrollPane(jtxpn3,
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        viewPanel.add(new JScrollPane(jtxpn4,
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), gbc);
        
        
        

       
	}
	
	private void setDefaultTextArea(JTextPane textArea){
		textArea.setEditable(false);
		StyledDocument doc = textArea.getStyledDocument();
		StyleContext sc = new StyleContext();
	    
		// Create and add Task style
	    Style taskStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
	    final Style mainStyle = sc.addStyle("TaskStyle", defaultStyle);
	    StyleConstants.setLeftIndent(taskStyle, 16);
	    StyleConstants.setRightIndent(taskStyle, 16);
	    StyleConstants.setFirstLineIndent(taskStyle, 16);
	    StyleConstants.setFontFamily(taskStyle, "serif");
	    StyleConstants.setFontSize(taskStyle, 12);
	    
	    // Create and add Default style
	    Style mainStyle = sc.getStyle(StyleContext.DEFAULT_STYLE);
	    final Style mainStyle = sc.addStyle("MainStyle", defaultStyle);
	    StyleConstants.setLeftIndent(mainStyle, 16);
	    StyleConstants.setRightIndent(mainStyle, 16);
	    StyleConstants.setFirstLineIndent(mainStyle, 16);
	    StyleConstants.setFontFamily(mainStyle, "serif");
	    StyleConstants.setFontSize(mainStyle, 12);

	    // Create and add Date style
	    final Style DateStyle = sc.addStyle("ConstantWidth", null);
	    StyleConstants.setFontFamily(DateStyle, "monospaced");
	    StyleConstants.setForeground(DateStyle, Color.green);

	    // Create and add the Discription style
	    final Style discriptionStyle = sc.addStyle("Heading2", null);
	    StyleConstants.setForeground(discriptionStyle, Color.red);
	    StyleConstants.setFontSize(discriptionStyle, 16);
	    StyleConstants.setFontFamily(discriptionStyle, "serif");
	    StyleConstants.setBold(discriptionStyle, true);
	    StyleConstants.setLeftIndent(discriptionStyle, 8);
	    StyleConstants.setFirstLineIndent(discriptionStyle, 0);
		
		
	
	

    
    
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          try {
            // Set the logical style
            doc.setLogicalStyle(0, mainStyle);

            // Add the text to the document
            doc.insertString(0, sampleTask, null);

            // Apply the character attributes
            doc.setCharacterAttributes(49, 13, cwStyle, false);
            doc.setCharacterAttributes(223, 14, cwStyle, false);
            doc.setCharacterAttributes(249, 14, cwStyle, false);
            doc.setCharacterAttributes(286, 8, cwStyle, false);
            doc.setCharacterAttributes(475, 14, cwStyle, false);
            doc.setCharacterAttributes(497, 21, cwStyle, false);
            doc.setCharacterAttributes(557, 9, cwStyle, false);
            doc.setCharacterAttributes(639, 12, cwStyle, false);
            doc.setCharacterAttributes(733, 21, cwStyle, false);
            doc.setCharacterAttributes(759, 9, cwStyle, false);

            // Finally, apply the style to the heading
            doc.setParagraphAttributes(0, 1, heading2Style, false);

            // Set the foreground color and change the font
            pane.setForeground(Color.pink);
            pane.setFont(new Font("Monospaced", Font.ITALIC, 24));
          } catch (BadLocationException e) {
          }
        }
      });
    } catch (Exception e) {
      System.out.println("Exception when constructing document: " + e);
      System.exit(1);
    }
  }

  private static final String sampleTask = "Task 1 start date End Date priority .........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." +
		  "this is a test for length nothing else just to test the length and ..........." ;
          

}
    
    
    

}
