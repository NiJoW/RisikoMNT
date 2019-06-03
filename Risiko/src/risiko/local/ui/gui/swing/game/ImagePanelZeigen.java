package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanelZeigen extends JPanel{



    private BufferedImage bi;
//    String pathToImage;
    int imgPanelWidth = 1024;
    int imgPanelHeigth = 525;
    int trueWidth;
	int trueHeight;
	float scaleFactorX;
	float scaleFactorY;

	KartenPanel kartenenPanel;

	
	
    public ImagePanelZeigen(KartenPanel kartenPanel) {
    	this.kartenenPanel = kartenPanel;

	//PhasenPanel phasenPanel;
	
	
   // public ImagePanel(PhasenPanel phasenPanel) {

       	this.setSize(imgPanelWidth,imgPanelHeigth);
		this.setPreferredSize(new Dimension(imgPanelWidth,imgPanelHeigth));
	//	this.phasenPanel = phasenPanel;

       try {
          bi = ImageIO.read(new File("images/WeltkarteFINALschwarz.png"));
          trueWidth = bi.getWidth();
  		  trueHeight = bi.getHeight();
          setSize(imgPanelWidth,imgPanelHeigth);
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
		
//		
//		System.out.println(scaleFactorX +" y: " + scaleFactorY);
//		System.out.println(trueWidth + " " + trueHeight);
//		System.out.println("breite "+this.getWidth() + " hoehe" + this.getHeight());
//		System.out.println("breite "+this.getParent().getWidth() + " hoehe" + this.getParent().getHeight());
//        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}	