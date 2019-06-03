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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risiko.local.domain.Risiko;

public class KartenPanel extends JPanel {
	
	Risiko risiko;
	InformationsPanel informationsPanel;
	
	int aktuellerSpielerID;
	private PhasenPanel phasenPanel;
	JLabel aktuellerSpieler;
	BufferedImage karte;
	int width = 1024;
	int height = 525;
	
	public KartenPanel(Risiko risiko, InformationsPanel informationsPanel, int aktuellerSpielerID, int screenWidth, int screenHeight,  JLayeredPane lp) {
		this.risiko = risiko;
		this.informationsPanel = informationsPanel;
		
		this.aktuellerSpielerID = aktuellerSpielerID;
		System.out.println(aktuellerSpielerID);
		String spielername  = risiko.getSpielerName(aktuellerSpielerID);
		initialize(spielername, screenWidth, screenHeight, lp);
	}
	
	private void initialize(String spielername, int screenWidth, int screenHeight,  JLayeredPane orginaleLayerPane) {
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		JLayeredPane orginalLayerPane = orginaleLayerPane;
		JLayeredPane kartenLayer = new JLayeredPane();
		kartenLayer.setBounds(0, 0, 1024, 525);
		kartenLayer.setLayout(null);
		
		ImagePanel myImage = new ImagePanel(this);
		ImagePanelZeigen myImageZeigen = new ImagePanelZeigen(this);
		
		ImageIcon face = new ImageIcon("images/Gewonnen.png"); 
//		JLabel alaska = new JLabel(face); //new JLabel(face);
//		alaska.setBounds(85, 65, 10, 10);
//		alaska.setPreferredSize(new Dimension(100,100));
//		alaskaText.setPreferredSize(new Dimension(100,100));
		JLabel alaskaText = new JLabel("3"); //new JLabel(face);
		alaskaText.setLocation(86, 62);
		alaskaText.setSize(new Dimension(10,10));
		alaskaText.setBackground(Color.WHITE);
		alaskaText.setOpaque(true);
		kartenLayer.add(alaskaText);
		
		JLabel nwTerriteriumText  = new JLabel("3");
		nwTerriteriumText.setLocation(172, 62);
		nwTerriteriumText.setSize(new Dimension(10,10));
		nwTerriteriumText.setBackground(Color.WHITE);
		nwTerriteriumText.setOpaque(true);
		kartenLayer.add(nwTerriteriumText);
		
		JLabel ontarioText  = new JLabel("4");
		ontarioText.setLocation(150, 100);
		ontarioText.setSize(new Dimension(10,10));
		ontarioText.setBackground(Color.WHITE);
		ontarioText.setForeground(Color.RED);
		ontarioText.setOpaque(true);
		kartenLayer.add(ontarioText);
		
		JLabel argentinienText  = new JLabel("6");
		argentinienText.setLocation(270, 478);
		argentinienText.setSize(new Dimension(10,10));
		argentinienText.setBackground(Color.WHITE);
		argentinienText.setOpaque(true);
		kartenLayer.add(argentinienText);
		
		JLabel islandText  = new JLabel("8");
		islandText.setLocation(412, 62);
		islandText.setSize(new Dimension(10,10));
		islandText.setBackground(Color.WHITE);
		islandText.setForeground(Color.RED);
		islandText.setOpaque(true);
		kartenLayer.add(islandText);
		
		JLabel großbritanienText  = new JLabel("3");
		großbritanienText.setLocation(350, 62);
		großbritanienText.setSize(new Dimension(10,10));
		großbritanienText.setBackground(Color.WHITE);
		großbritanienText.setOpaque(true);	
		
		JLabel kamtschatkaText  = new JLabel("9");
		kamtschatkaText.setLocation(918, 62);
		kamtschatkaText.setSize(new Dimension(10,10));
		kamtschatkaText.setBackground(Color.WHITE);
		kamtschatkaText.setForeground(Color.RED);
		kamtschatkaText.setOpaque(true);
		kartenLayer.add(kamtschatkaText);
		
		JLabel ostAustralienText  = new JLabel("7");
		ostAustralienText.setLocation(920, 430);
		ostAustralienText.setSize(new Dimension(10,10));
		ostAustralienText.setBackground(Color.WHITE);
		ostAustralienText.setOpaque(true);
		kartenLayer.add(ostAustralienText);
		
		
		
		
		
	//	kartenLayer.add(alaska);
		kartenLayer.add(myImageZeigen);
		kartenLayer.add(myImage);

		orginalLayerPane.add(kartenLayer);
		
	//	ImagePanel myImage = new ImagePanel(this);
//		this.add(myImage);
		
		this.setVisible(true);

	}
	
	
	public void addMapAndPhasenPanel(PhasenPanel phasenPanel) {
		this.phasenPanel = phasenPanel;
		
	//	ImagePanel myImage = new ImagePanel(phasenPanel);
	//	this.add(myImage);
	//	this.setVisible(true);
	}

	public void setAktuellerSpieler(int aktuellerSpielerID) {
		this.aktuellerSpielerID = aktuellerSpielerID;
	//	aktuellerSpieler.setText(risiko.getSpielerName(aktuellerSpielerID));
		System.out.println("naechste Spieler: " + risiko.getSpielerName(aktuellerSpielerID));
	}

	public void setClickedProvinz(int provinzByColor) {
		if(provinzByColor == 42) {
			informationsPanel.setNachricht("Bitte waehle eine Provinz aus.");
		}
		phasenPanel.setClickedProvinz(provinzByColor);

	}

}
