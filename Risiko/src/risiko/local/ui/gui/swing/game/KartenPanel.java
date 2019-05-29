package risiko.local.ui.gui.swing.game;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risiko.local.domain.Risiko;

public class KartenPanel extends JPanel {
	
	Risiko risiko;
	int aktuellerSpielerID;
	private PhasenPanel phasenPanel;
	JLabel aktuellerSpieler;
	BufferedImage karte;
	
	public KartenPanel(Risiko risiko, int aktuellerSpielerID, int screenWidth, int screenHeight) {
		this.risiko = risiko;
		
		this.aktuellerSpielerID = aktuellerSpielerID;
		System.out.println(aktuellerSpielerID);
		String spielername  = risiko.getSpielerName(aktuellerSpielerID);
		initialize(spielername, screenWidth, screenHeight);
	}

	private void initialize(String spielername, int screenWidth, int screenHeight) {
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension centerSize = new Dimension(4*(screenWidth/5), 4*(screenHeight/5));
		this.setMinimumSize(centerSize);
		this.setPreferredSize(centerSize);
		JTextField provinz = new JTextField("Provinz: ");
//		this.add(provinz);
		
		JButton bestaetigung = new JButton("bestaetigung");
		bestaetigung.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				phasenPanel.setClickedProvinz(Integer.parseInt(provinz.getText()));
			}
			
		});
//		this.add(bestaetigung);
//		ImagePanel myImage = new ImagePanel("images/Weltkarte.jpeg");
		try {
			karte = ImageIO.read(new File("images/test2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.addMouseListener(getMouseAdapter());
		
////		JLabel myImage = new JLabel(karte);
//		myImage.setPreferredSize(centerSize);
//		myImage.addMouseListener(getMouseAdapter());
//		this.add(myImage); //layeredPane.add(myImage);
		
		aktuellerSpieler = new JLabel(spielername);
		this.add(aktuellerSpieler);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Dimension d = getSize();
		
		g.drawImage(karte, 0, 0, (int) d.getWidth(), (int) d.getHeight(), this); //Observer = null -> moeglicherweise
	}
	
	public void addMap(String spielername) {
		//JLayeredPane layeredPane = new JLayeredPane();
		//layeredPane.setPreferredSize(this.getSize());
		// System.out.println("Breite "+this.getWidth() + " und " + this.getHeight());
		 
		
		
//		JLabel aktSpieler = new JLabel("Spieler "+ spielername +" ist an der Reihe");
//		aktSpieler.setHorizontalTextPosition(JLabel.RIGHT);
//		aktSpieler.setVerticalTextPosition(JLabel.TOP);
//		this.add(aktSpieler);
		//this.add(layeredPane);
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
            		
            		Color myColor = new Color(karte.getRGB(xKoordinate, yKoordinate));
//            		System.out.println(karte.getData().getPixel(xKoordinate, yKoordinate, new int[0]));
            		//					Robot r = new Robot();
//					c = r.getPixelColor(e.getX(),e.getY()); 
//					rgb = c.getRGB();
					//System.out.println(c);
					System.out.println("Geklickter Farbwert: " + myColor);
					//getProvinzIDByColor(rgb);
//					System.out.println(getProvinzIDByColor(rgb));
					phasenPanel.setClickedProvinz(getProvinzIDByColor(rgb));
//				} catch (AWTException e1) {
//					e1.printStackTrace();
//				} 
            	
            	System.out.println("clicked");
            }
        };
	}
	
	 private int getProvinzIDByColor(int rgb) {
		// if((c.getAlpha()==255) && (c.getRGB()()==255) && (c.getAlpha()==255) )
		int provinzID; 
		switch(rgb) {
			case -1: //zum test, haben alle
			case	-257:
			case	-8913664:
			case	-6735544:
			case	-9371648:
			case	-10420220:
			case	-5139:
				provinzID = 41; //Venezuela
				break;
			case -9751296: 
			case	-9817600:
//				-9240576 //hat Brasilien auch
//				-9816832 //hat Brasilien auch
//				-9686272 //hat Brasilien auch
			case	-9686528:
//				-1
			case	-65537:
//				-9306112 //hat Brasilien auch
			case	-9293312:
				provinzID = 39; //Brasilien
				break;
			case -9555647: 
			case	-9437184:
			case	-9294271:
			case	-10800640:
//				-9686272 //B
			case	-9751040:
			case	-9489920:
			case	-9555711:
			case	-9961462:
			case	-9359806:
//				-9816832 //B
//				-1 //beide
			case	-7213056:
			case	-5316823:
//				-9306112 //B
//				-9240576 //B
			case	-9488077:
			case	-10158070:
				provinzID = 40; //Peru
				break;
			case -9555900: 
				provinzID = 38; //Argentinien
				break;
			case -1381654: 
				provinzID = 31; //Groenland
				break;	
			default:
				provinzID = 42; 
				//TODO Fehlerfall bzw. keine Farbe/Weißer Kartenhintergrund
				//soll nichts passieren
		}
		System.out.println("Provinz ID laut Farbe:" +provinzID + " (42 ist 'ungueltig')");
		
		//git spinnt total rum//////
		
		//zufälliges Fake-Ergebnis //südamerikanische Provinz oder Provinz "Weststaaten"
		Random rand = new Random();
		provinzID = 37 + rand.nextInt(5);
		System.out.println("Zufällige Provinz-ID:");
		
     	return provinzID;
     }

	public void addPhasenPanel(PhasenPanel phasenPanel) {
		this.phasenPanel = phasenPanel;
	}

	public void setAktuellerSpieler(int aktuellerSpielerID) {
		this.aktuellerSpielerID = aktuellerSpielerID;
		aktuellerSpieler.setText(risiko.getSpielerName(aktuellerSpielerID));
		System.out.println("naechste Spieler: " + risiko.getSpielerName(aktuellerSpielerID));
	}

}
