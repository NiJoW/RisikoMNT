package risiko.local.ui.gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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

import risiko.local.ui.gui.swing.game.ImagePanel;

public class FaceLayout extends JFrame {

	private BufferedImage bi;
    int PanelWidth = 1024;
    int PanelHeigth = 525;
    int imgPanelWidth = 205;
    int imgPanelHeigth = 105;
    int trueWidth;
	int trueHeight;
	
	public FaceLayout() {

        ImageIcon face = new ImageIcon("images/Gewonnen.png"); //new ImageIcon(getClass().getResource("/images/Weltkarte.jpeg"));
        ImageIcon eyes = new ImageIcon("images/WeltkarteFINAL.png"); //new ImageIcon(getClass().getResource("/images/Weltkarte.jpeg"));
        ImageIcon nose = new ImageIcon("images/WeltkarteFINAL.png"); //new ImageIcon(getClass().getResource("/images/Weltkarte.jpeg"));
        ImageIcon mouth = new ImageIcon("images/WeltkarteFINAL.png"); //new ImageIcon(getClass().getResource("/images/Weltkarte.jpeg"));

        JLabel fLab = new JLabel(face);
        fLab.setBounds(25, 25, 100, 100);
      //  JLabel eLab = new JLabel(eyes);
       // eLab.setBounds(100, 100, 100, 100);
        
        JPanel eLab = new JPanel();
        eLab.setBackground(Color.PINK);
        eLab.setBounds(100, 100, 100, 100);
        
        JPanel nLab = new JPanel();
        nLab.setBackground(Color.BLUE);
        nLab.setBounds(150, 150, 100, 100);
        
//        JLabel nLab = new JLabel(nose);
//        nLab.setBounds(175, 175, 100, 100);
        JLabel mLab = new JLabel(mouth);
        mLab.setBounds(250, 250, 100, 100);
        
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
       //  this.setVisible(true);
         

   
        JLayeredPane layers = new JLayeredPane();
        
        ImagePanel myImage = new ImagePanel();
       // myImage.setVisible(false);
		//this.add(myImage);
		
		layers.add(myImage, 6);
        layers.add(fLab, 2);
        layers.add(eLab, 3);
        layers.add(nLab, 4);
        layers.add(mLab, 5);
        
        JPanel rightPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PanelWidth, PanelHeigth);
            }
        };
        rightPanel.setLayout(null);
        layers.setBounds(0, 0, 400, 400);
        rightPanel.add(layers);

        Dimension rSize = new Dimension(100, 400);
        rightPanel.setMinimumSize(rSize);

        add(rightPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
//    @Override
//	public void paint(Graphics g) {
////        super.paintComponent(g);
//        Dimension d = getSize(); //1000, 600,
////        int parentWidth = this.getParent().getWidth();
////        int parentHeight = this.getParent().getHeight();
////        
////        this.setSize(parentWidth,parentHeight);
//		g.drawImage(bi, 0, 0, imgPanelWidth, imgPanelHeigth, this); //Observer = null -> moeglicherweise
//		
//
////	
////        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
//    }
    private MouseAdapter getMouseAdapter() {
		return new MouseAdapter() {
			Color c;
			int rgb;
            
            @Override
            public void mouseClicked(MouseEvent e) {
            		
            		float xKoordinate = (e.getX());//*scaleFactorX
            		float yKoordinate = (e.getY());
            		int xK = (int)xKoordinate;
            		int yK = (int)yKoordinate;
            		

            		
            		Color myColor = new Color(bi.getRGB(xK, yK));

					System.out.println(myColor.getRGB());
					rgb = myColor.getRGB();

            }
          }; 
    
		}
    		
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new FaceLayout();
            }
        });
    }
}