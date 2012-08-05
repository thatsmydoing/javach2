import javax.swing.*;
import java.awt.*;
public class SE_ACTUAL_0260 extends JApplet 
{   private JButton button;
    private JTextField textField;

    public void init()
    {   Container c = getContentPane();
        c.setLayout = (new BorderLayout());
        c.add("North", new Buton(""))
        c.setLayout( new GridLayout( 4, 6) );
        
        c.add(new Button("MC"));
        c.add(new Button("7"));
        c.add(new Button("8"));
        c.add(new Button("9"));
        c.add(new Button("/"));
        c.add(new Button("sqrt"));
        c.add(new Button("MR"));
        c.add(new Button("4"));
        c.add(new Button("5"));
        c.add(new Button("6"));
        c.add(new Button("*"));
        c.add(new Button("%"));
        c.add(new Button("MS"));
        c.add(new Button("1"));
        c.add(new Button("2"));
        c.add(new Button("3"));
        c.add(new Button("-"));
        c.add(new Button("1/x"));
        c.add(new Button("M+"));
        c.add(new Button("0"));
        c.add(new Button("+/-"));
        c.add(new Button("."));
        c.add(new Button("+"));
        c.add(new Button("="));
    }
}
