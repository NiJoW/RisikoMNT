package risiko.local.ui.gui.swing;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class TestePanel extends JFrame {
//5281 × 2820 //3439 × 2193
	BufferedImage bi; //5281 ×  //(2820/4); //2820
	int biWidth = (3439); //(1259/2); //(5281/4); //
	int biHeigth =(2193); //(1160/2);
	
    public TestePanel() {

      
    	try
        {
  
    		
          bi = ImageIO.read(new File("images/weltPS.png")); 
               setSize(biWidth,biHeigth);
  		this.addMouseListener(getMouseAdapter());

        
        } 
        catch (IOException e)
        {
          // log the exception
          // re-throw if desired
        }
    	this.setVisible(true);

    }
    public void paint(Graphics g) {
        g.drawImage(bi, 0, 0, biWidth,biHeigth, this);
      }
    
    private MouseAdapter getMouseAdapter() {
		return new MouseAdapter() {
			Color c;
			int rgb;
            
            @Override
            public void mouseClicked(MouseEvent e) {
            	try {
					Robot r = new Robot();
					c = r.getPixelColor(e.getX(),e.getY()); 
					rgb = c.getRGB();
					System.out.println("Geklickter Farbwert: "+rgb);
					
				} catch (AWTException e1) {
					e1.printStackTrace();
				} 
//            	
//            	int x = e.getX();
//            	int y = e.getY();
//            	
//            	System.out.println("Koordinaten: " + x + ", " + y);
//            	
//            	Color myColor = new Color(bi.getRGB(x, y));
//            	
//            	System.out.println("Geklickter Farbwert: " + myColor);
            	
            	//System.out.println("clicked");
            }
        };
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new TestePanel();
            }
        });
    }
}