
/**
 * Write a description of class AppletExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PBE_ACTUAL_0076 extends JApplet 
{   private JButton button;
    private JTextField textField;
    private JLabel label;
    private JTextArea textArea;
    private Container c;
    public void init()
    {   
        c = getContentPane();
        
        label = new JLabel( "Hello, World!" );
        c.add( label );
        
        textField = new JTextField( "Enter your name here", 15 );
        c.add( textField );
                
        textArea = new JTextArea( "Type your message here", 20, 30 );
        c.add( textArea );
        
        button = new JButton( "Click Me" );
        c.add( button );
        class ButtonListener implements ActionListener
        { 
            public void actionPerformed( ActionEvent ae )
            {
                String text = textField.getText();
                label.setText( "Hello," +text );
            }
        }
        ActionListener listener = new ButtonListener;
        button.ActionListener( new ButtonListener() );
    }
}

