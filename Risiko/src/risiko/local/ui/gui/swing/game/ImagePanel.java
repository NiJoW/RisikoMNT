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

	KartenPanel kartenenPanel;

	
	
    public ImagePanel(KartenPanel kartenPanel) {
    	this.kartenenPanel = kartenPanel;

	//PhasenPanel phasenPanel;
	
	
   // public ImagePanel(PhasenPanel phasenPanel) {

       	this.setSize(imgPanelWidth,imgPanelHeigth);
		this.setPreferredSize(new Dimension(imgPanelWidth,imgPanelHeigth));
	//	this.phasenPanel = phasenPanel;

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
		
		
//		System.out.println(scaleFactorX +" y: " + scaleFactorY);
//		System.out.println(trueWidth + " " + trueHeight);
//		System.out.println("breite "+this.getWidth() + " hoehe" + this.getHeight());
//		System.out.println("breite "+this.getParent().getWidth() + " hoehe" + this.getParent().getHeight());
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
					System.out.println("Farbwert: " + myColor.hashCode());
					kartenenPanel.setClickedProvinz(getProvinzByColor(myColor)); 
					//getProvinzIDByColor(rgb);
//					System.out.println(getProvinzIDByColor(rgb));
					System.out.println(myColor.getRGB());
					rgb = myColor.getRGB();
//					if(getProvinzIDByColor(rgb)!=42) {
//						phasenPanel.setClickedProvinz(getProvinzIDByColor(rgb));
//					}
					
//				} catch (AWTException e1) {
//					e1.printStackTrace();
//				} 
            	
//            	System.out.println("clicked");
            }

			private int getProvinzByColor(Color myColor) {
				int farbwert = myColor.hashCode();
				int provinzID = 0;
				switch(farbwert) {
				
				
				//Afrika
				case -13998027: //Aegypten
					provinzID = 0;
					break;
				case -16742038: //Kongo 
					provinzID = 1;
					break;
				case -10515867: //Madagaska
					provinzID = 2;
					break;
				case -12482198: //Nordwestafrika
					provinzID = 3;
					break;
				case -10515916: //Ostafrika
					provinzID = 4;
					break;
				case -16747688: //Suedafrika
					provinzID = 5;
					break;
					
					
				//Asien
				case -12237370: //Afganistan
					provinzID = 6;
					break;
				case -16774797: //China
					provinzID = 7;
					break;
				case -14984762: //Indien
					provinzID = 8;
					break;
				case -16729716: //Irkutsk
					provinzID = 9;
					break;
				case -16729689: //Jakutien
					provinzID = 10;
					break;
				case -16743028: //Japan
					provinzID = 11;
					break;
				case -16737908: //Kamtschatka
					provinzID = 12;
					break;
				case -2740343://Mittlerer Osten
					provinzID = 13;
					break;
				case -16761485: //Mongolei
					provinzID = 14;
					break;
				case -14973498: //Siam
					provinzID = 15;
					break;
				case -14973581: //Sibirien
					provinzID = 16;
					break;
				case -12232250: //Ural
					provinzID = 17;
					break;	
				
					
				//Australien
				case -9221519: //Indonesien
					provinzID = 18;
					break;
				case -9225414: //Neu Guinera
					provinzID = 19;
					break;
				case -9240463: //Ost-Australien
					provinzID = 20;
					break;
				case -9240518: //West-Australien
					provinzID = 21;
					break;
					
				//Europa
				case -5549961: //Grossbritanien
					provinzID = 22;
					break;
				case -3377289: //Island
					provinzID = 23;
					break;
				case -2731863: //Mitteleuropa
					provinzID = 24;
					break;
				case -5549911: //Skandinavien
					provinzID = 25;
					break;
				case -2731920: //Sued-Europa
					provinzID = 26;
					break;
				case -2731895: //Ukraine
					provinzID = 27;
					break;
				case -5550024: //West-Europa
					provinzID = 28;
					break;
				
				//Nordamerika
				case -5979818: //Alaska
					provinzID = 29;
					break;
				case -3096234: //Alberta
					provinzID = 30;
					break;
				case -2510553: //Groenland
					provinzID = 31;
					break;
				case -5966809: //Mittel-Amerika
					provinzID = 32;
					break;
				case -3096320: //Nordwest-Territorium
					provinzID = 33;
					break;
				case -5988010: //Ontario
					provinzID = 34;
					break;
				case -2493401: //Oststaaten
					provinzID = 35;
					break;
				case -5987945: //Quebeck
					provinzID = 36;
					break;
				case -5975769: //Weststaaten
					provinzID = 37;
					break;
					
				//Australien
				case -6666648: //Argentienien
					provinzID = 38;
					break;
				case -8763899: //Brasilien
					provinzID = 39;
					break;
				case -8763820: //Peru
					provinzID = 40;
					break;
				case -8781819: //Venezuela
					provinzID = 41;
					break;
					
				default:
					provinzID = 42;
					System.out.println("keine valide Provinz");
					break;
				}
				return provinzID;
			}
        };
	}

//    private int getProvinzIDByColor(int rgb) {   	
//    	//zufälliges Fake-Ergebnis //südamerikanische Provinz oder Provinz "Weststaaten"
//    			Random rand = new Random();
//    			int provinzID = 37 + rand.nextInt(5);
//    			System.out.println("Zufällige Provinz-ID:"+provinzID);
//    			
//    	     	return provinzID;
//    }
    
}
