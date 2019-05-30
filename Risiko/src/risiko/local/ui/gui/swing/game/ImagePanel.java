package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage bi;
    String pathToImage;
    int biWidth = 1024;
	int biHeigth = 768;

    public ImagePanel() {
    	//1024 × 768
    	this.setSize(1024,768);
		this.setPreferredSize(new Dimension(1024,768));

    	pathToImage = "images/weltPS.png";
       try {                
          //image = ImageIO.read(new File(pathToImage));
          bi = ImageIO.read(new File("images/weltPS.png"));
          setSize(biWidth,biHeigth);
          this.addMouseListener(getMouseAdapter());
       } catch (IOException ex) {
    	   System.out.println(ex.getMessage());
            // handle exception...
       }
       this.setVisible(true);
       
    }

    @Override
	public void paint(Graphics g) {
//        super.paintComponent(g);
        Dimension d = getSize(); //1000, 600,
		g.drawImage(bi, 0, 0, biWidth, biHeigth, this); //Observer = null -> moeglicherweise
		System.out.println("draw image!");
//        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    
    private MouseAdapter getMouseAdapter() {
		return new MouseAdapter() {
			Color c;
			int rgb;
            
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            		int xKoordinate = e.getX();
            		int yKoordinate = e.getY();
            		
            		System.out.println("x-Koordinate = " + xKoordinate);
            		System.out.println("y-Koordinate = " + yKoordinate);
            		
            		Color myColor = new Color(bi.getRGB(xKoordinate, yKoordinate));
//            		System.out.println(karte.getData().getPixel(xKoordinate, yKoordinate, new int[0]));
            		//					Robot r = new Robot();
//					c = r.getPixelColor(e.getX(),e.getY()); 
//					rgb = c.getRGB();
					//System.out.println(c);
					System.out.println("Farbwert: " + myColor);
					//getProvinzIDByColor(rgb);
//					System.out.println(getProvinzIDByColor(rgb));
				//	phasenPanel.setClickedProvinz(getProvinzIDByColor(rgb));
//				} catch (AWTException e1) {
//					e1.printStackTrace();
//				} 
            	
            	System.out.println("clicked");
            }
        };
	}

}
