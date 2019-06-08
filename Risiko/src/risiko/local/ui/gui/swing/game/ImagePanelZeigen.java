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
    private int imgPanelWidth = 1024;
    private  int imgPanelHeigth = 525;
    private int trueWidth;
    private int trueHeight;
    private float scaleFactorX;
    private float scaleFactorY;

    private KartenPanel kartenenPanel;



    public ImagePanelZeigen(KartenPanel kartenPanel) {
    	this.kartenenPanel = kartenPanel;

       	this.setSize(imgPanelWidth,imgPanelHeigth);
		this.setPreferredSize(new Dimension(imgPanelWidth,imgPanelHeigth));

       try {
          bi = ImageIO.read(new File("images/WeltkarteBlauKreise.png"));
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
        Dimension d = getSize(); //1000, 600,
        int parentWidth = this.getParent().getWidth();
        int parentHeight = this.getParent().getHeight();

        this.setSize(parentWidth,parentHeight);
		g.drawImage(bi, 0, 0, parentWidth, parentHeight, this); //Observer = null -> moeglicherweise

		scaleFactorX = trueWidth / parentWidth;
		scaleFactorY = trueHeight / parentHeight;

    }
}	