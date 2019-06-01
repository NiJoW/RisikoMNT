package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage bi;
//    String pathToImage;
    int imgPanelWidth = 1024;
    int imgPanelHeigth = 525;
    int trueWidth;
	int trueHeight;
	float scaleFactorX;
	float scaleFactorY;
	PhasenPanel phasenPanel;
	
	
    public ImagePanel(PhasenPanel phasenPanel) {
       	this.setSize(imgPanelWidth,imgPanelHeigth);
		this.setPreferredSize(new Dimension(imgPanelWidth,imgPanelHeigth));
		this.phasenPanel = phasenPanel;

       try {
          bi = ImageIO.read(new File("images/WeltkarteFINAL.png"));
          trueWidth = bi.getWidth();
  		  trueHeight = bi.getHeight();
          setSize(imgPanelWidth,imgPanelHeigth);
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
        int parentWidth = this.getParent().getWidth();
        int parentHeight = this.getParent().getHeight();
        
        this.setSize(parentWidth,parentHeight);
		g.drawImage(bi, 0, 0, parentWidth, parentHeight, this); //Observer = null -> moeglicherweise
		
		scaleFactorX = trueWidth / parentWidth;
		scaleFactorY = trueHeight / parentHeight;
		
		
		System.out.println(scaleFactorX +" y: " + scaleFactorY);
		System.out.println(trueWidth + " " + trueHeight);
		System.out.println("breite "+this.getWidth() + " hoehe" + this.getHeight());
		System.out.println("breite "+this.getParent().getWidth() + " hoehe" + this.getParent().getHeight());
//        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    
    private MouseAdapter getMouseAdapter() {
		return new MouseAdapter() {
			Color c;
			int rgb;
            
            @Override
            public void mouseClicked(MouseEvent e) {
            		
            		float xKoordinate = (e.getX()*scaleFactorX);//*scaleFactorX
            		float yKoordinate = (e.getY()*scaleFactorY);
            		int xK = (int)xKoordinate;
            		int yK = (int)yKoordinate;
            		
//            		System.out.println("Skalierte x-Koordinate = " + xKoordinate);
//            		System.out.println("Skalierte y-Koordinate = " + yKoordinate);
            		
            		Color myColor = new Color(bi.getRGB(xK, yK));
//            		System.out.println(karte.getData().getPixel(xKoordinate, yKoordinate, new int[0]));
            		//					Robot r = new Robot();
//					c = r.getPixelColor(e.getX(),e.getY()); 
//					rgb = c.getRGB();
					//System.out.println(c);
					System.out.println("Farbwert: " + myColor);
					//getProvinzIDByColor(rgb);
//					System.out.println(getProvinzIDByColor(rgb));
					System.out.println(myColor.getRGB());
					rgb = myColor.getRGB();
					if(getProvinzIDByColor(rgb)!=42) {
						phasenPanel.setClickedProvinz(getProvinzIDByColor(rgb));
					}
					
//				} catch (AWTException e1) {
//					e1.printStackTrace();
//				} 
            	
//            	System.out.println("clicked");
            }
        };
	}

    private int getProvinzIDByColor(int rgb) {   	
    	//zufälliges Fake-Ergebnis //südamerikanische Provinz oder Provinz "Weststaaten"
    			Random rand = new Random();
    			int provinzID = 37 + rand.nextInt(5);
    			System.out.println("Zufällige Provinz-ID:"+provinzID);
    			
    	     	return provinzID;
    }
    
}
