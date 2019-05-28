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

	BufferedImage bi; //5281 ×  //(2820/4); //2820
	int biWidth = (1259); //(1259/2); //(5281/4); //
	int biHeigth =(1160); //(1160/2);
	
    public TestePanel() {

       /* ImageIcon face = new ImageIcon("images/IllustratorWelt.png"); //new ImageIcon(getClass().getResource("/images/Weltkarte.jpeg"));
        
        JLabel fLab = new JLabel(face);
        fLab.setBounds(0, 0, 1400, 1400);
    
        JLayeredPane layers = new JLayeredPane();

        layers.add(fLab, 1);
   
        JPanel rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1400, 1400);
            }
        };
        rightPanel.setLayout(null);
        layers.setBounds(0, 0, 1400, 1400);
        rightPanel.add(layers);

       
   

        add(rightPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);*/
    	try
        {
          // the line that reads the image file
    		
          bi = ImageIO.read(new File("images/karte.svg"));
        //  setSize(bi.getWidth(), bi.getHeight());
          
          setSize(biWidth,biHeigth);
       
  		this.addMouseListener(getMouseAdapter());

          // work with the image here ...
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
					//System.out.println(c);
					System.out.println("Geklickter Farbwert: "+rgb);
					//getProvinzIDByColor(rgb);
					//System.out.println(getProvinzIDByColor(rgb));
					//phasenPanel.setClickedProvinz(getProvinzIDByColor(rgb));
				} catch (AWTException e1) {
					e1.printStackTrace();
				} 
            	
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