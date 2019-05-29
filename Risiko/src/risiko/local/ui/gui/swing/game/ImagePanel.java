package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    String pathToImage;

    public ImagePanel() {
    	pathToImage = "images/test2.png";
       try {                
          image = ImageIO.read(new File(pathToImage));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
	public void paint(Graphics g) {
//        super.paintComponent(g);
        Dimension d = getSize();
		g.drawImage(image, 0, 0, 1000, 600, this); //Observer = null -> moeglicherweise
		System.out.println("draw image!");
//        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}
