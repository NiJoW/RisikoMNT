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
//    String pathToImage;
    int imgPanelWidth = 1024;
    int imgPanelHeigth = 700;
    int trueWidth;
	int trueHeight;
	float scaleFactorX;
	float scaleFactorY;
	

    public ImagePanel() {
    	//1024/768
    	this.setSize(imgPanelWidth,imgPanelHeigth);
		this.setPreferredSize(new Dimension(imgPanelWidth,imgPanelHeigth));

//    	pathToImage = "images/weltPS.png";
       try {                
          //image = ImageIO.read(new File(pathToImage));
          bi = ImageIO.read(new File("images/weltpic.png"));
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
            		
            		int xKoordinate = (int) (e.getX()*scaleFactorX);
            		int yKoordinate = (int) (e.getY()*scaleFactorY);
            		System.out.println("Skalierte x-Koordinate = " + xKoordinate);
            		System.out.println("Skalierte y-Koordinate = " + yKoordinate);
            		
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
