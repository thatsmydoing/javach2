
/**
 * Class RemoteFrame
 * 
 * @author Erwin Miranda
 * @version September 23, 2008
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoteFrame extends JFrame
{
    private JTextField display;
    private JButton[] keys;
    private Container c;
    private JPanel keyPane;
    
    public RemoteFrame()
    {
        c = getContentPane();
        c.setLayout( new BorderLayout() );
        display = new JTextArea;
        display.setBackground(Color red);
        c.add( display, "North" );
    }
}
