package Classes;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Raza Lilani
 */
public class centercorner {
    public void getscreenCenteredCorner(Component c)
        {
            int x,y;
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        x=(int) ((size.getWidth()- c.getWidth())/2);
        y=(int) ((size.getHeight()- c.getHeight())/2);
        c.setLocation(x, y);
        }
    public void getCenteredCorner(Component c,Component p)
        {
            int a,b;
        Dimension size =p.getSize();
        a=(int) ((size.getWidth()- c.getWidth())/2);
        b=(int) ((size.getHeight()- c.getHeight())/2);
        c.setLocation(a, b);
        }
}
