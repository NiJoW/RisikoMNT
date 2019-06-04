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
	
	private void initialize(String spielername, int screenWidth, int screenHeight, JLayeredPane orginaleLayerPane) {
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		JLayeredPane orginalLayerPane = orginaleLayerPane;
		JLayeredPane kartenLayer = new JLayeredPane();
		kartenLayer.setBounds(0, 0, 1024, 525);
		kartenLayer.setLayout(null);
		
		ImagePanel myImage = new ImagePanel(this);
//		this.add(myImage);
		
		ImagePanelZeigen myImageZeigen = new ImagePanelZeigen(this);
		
		Color farbe;
		String anzahlEinheiten;

//		ImageIcon face = new ImageIcon("images/Gewonnen.png"); 
//		JLabel alaska = new JLabel(face); //new JLabel(face);
//		alaska.setBounds(85, 65, 10, 10);
//		alaska.setPreferredSize(new Dimension(100,100));
//		alaskaText.setPreferredSize(new Dimension(100,100));
		JLabel alaskaText = new JLabel("3"); //new JLabel(face);
		alaskaText.setLocation(86, 62);
		alaskaText.setSize(new Dimension(10,10));
		alaskaText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(29).getArmeeGroesse();
		alaskaText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(29).getBesitzer().getFarbe();
		alaskaText.setForeground(farbe);
		alaskaText.setOpaque(true);
		kartenLayer.add(alaskaText);

		JLabel nwTerriteriumText  = new JLabel("3");
		nwTerriteriumText.setLocation(170, 62);
		nwTerriteriumText.setSize(new Dimension(10,10));
		nwTerriteriumText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(33).getArmeeGroesse();
		nwTerriteriumText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(33).getBesitzer().getFarbe();
		nwTerriteriumText.setForeground(farbe);
		nwTerriteriumText.setOpaque(true);
		kartenLayer.add(nwTerriteriumText);
		
		JLabel groenlandText  = new JLabel("5");
		groenlandText.setLocation(364, 37);
		groenlandText.setSize(new Dimension(10,10));
		groenlandText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(31).getArmeeGroesse();
		groenlandText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(31).getBesitzer().getFarbe();
		groenlandText.setForeground(farbe);
		groenlandText.setOpaque(true);
		kartenLayer.add(groenlandText);

		JLabel albertaText  = new JLabel("4");
		albertaText.setLocation(152, 100);
		albertaText.setSize(new Dimension(10,10));
		albertaText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(30).getArmeeGroesse();
		albertaText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(30).getBesitzer().getFarbe();
		albertaText.setForeground(farbe);		
		albertaText.setOpaque(true);
		kartenLayer.add(albertaText);
		
		JLabel ontarioText  = new JLabel("4");
		ontarioText.setLocation(214, 99);
		ontarioText.setSize(new Dimension(10,10));
		ontarioText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(34).getArmeeGroesse();
		ontarioText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(34).getBesitzer().getFarbe();
		ontarioText.setForeground(farbe);		
		ontarioText.setOpaque(true);
		kartenLayer.add(ontarioText);
		
		JLabel quebeckText  = new JLabel("5");
		quebeckText.setLocation(280, 100);
		quebeckText.setSize(new Dimension(10,10));
		quebeckText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(36).getArmeeGroesse();
		quebeckText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(36).getBesitzer().getFarbe();
		quebeckText.setForeground(farbe);
		quebeckText.setOpaque(true);
		kartenLayer.add(quebeckText);
		
		JLabel weststaatenText  = new JLabel("6");
		weststaatenText.setLocation(134, 142);
		weststaatenText.setSize(new Dimension(10,10));
		weststaatenText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(37).getArmeeGroesse();
		weststaatenText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(37).getBesitzer().getFarbe();
		weststaatenText.setForeground(farbe);
		weststaatenText.setOpaque(true);
		kartenLayer.add(weststaatenText);

		JLabel oststaatenText  = new JLabel("6");
		oststaatenText.setLocation(208, 159);
		oststaatenText.setSize(new Dimension(10,10));
		oststaatenText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(35).getArmeeGroesse();
		oststaatenText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(35).getBesitzer().getFarbe();
		oststaatenText.setForeground(farbe);
		oststaatenText.setOpaque(true);
		kartenLayer.add(oststaatenText);

		JLabel mittelAmerikaText  = new JLabel("7");
		mittelAmerikaText.setLocation(134, 212);
		mittelAmerikaText.setSize(new Dimension(10,10));
		mittelAmerikaText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(32).getArmeeGroesse();
		mittelAmerikaText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(32).getBesitzer().getFarbe();
		mittelAmerikaText.setForeground(farbe);
		mittelAmerikaText.setOpaque(true);
		kartenLayer.add(mittelAmerikaText);
		
		JLabel venezuelaText  = new JLabel("5");
		venezuelaText.setLocation(242, 281);
		venezuelaText.setSize(new Dimension(10,10));
		venezuelaText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(41).getArmeeGroesse();
		venezuelaText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(41).getBesitzer().getFarbe();
		venezuelaText.setForeground(farbe);
		venezuelaText.setOpaque(true);
		kartenLayer.add(venezuelaText);
		
		JLabel peruText  = new JLabel("3");
		peruText.setLocation(254, 364);
		peruText.setSize(new Dimension(10,10));
		peruText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(40).getArmeeGroesse();
		peruText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(40).getBesitzer().getFarbe();
		peruText.setForeground(farbe);
		peruText.setOpaque(true);
		kartenLayer.add(peruText);
		
		JLabel brasilienText  = new JLabel("8");
		brasilienText.setLocation(301, 334);
		brasilienText.setSize(new Dimension(10,10));
		brasilienText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(39).getArmeeGroesse();
		brasilienText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(39).getBesitzer().getFarbe();
		brasilienText.setForeground(farbe);
		brasilienText.setOpaque(true);
		kartenLayer.add(brasilienText);

		
		JLabel argentinienText  = new JLabel("6");
		argentinienText.setLocation(270, 478);
		argentinienText.setSize(new Dimension(10,10));
		argentinienText.setBackground(Color.WHITE);
		anzahlEinheiten = ""+risiko.getProvinz(38).getArmeeGroesse();
		argentinienText.setText(anzahlEinheiten);
		farbe = risiko.getProvinz(38).getBesitzer().getFarbe();
		argentinienText.setForeground(farbe);
		argentinienText.setOpaque(true);
		kartenLayer.add(argentinienText);

		
		//-----------------
		JLabel islandText  = new JLabel("8");
		islandText.setLocation(414, 62);
		islandText.setSize(new Dimension(10,10));
		islandText.setBackground(Color.WHITE);
		islandText.setForeground(Color.RED);
		islandText.setOpaque(true);
		kartenLayer.add(islandText);
		
		JLabel skandinavienText  = new JLabel("3");
		skandinavienText.setLocation(482, 69);
		skandinavienText.setSize(new Dimension(10,10));
		skandinavienText.setBackground(Color.WHITE);
		skandinavienText.setForeground(Color.RED);
		skandinavienText.setOpaque(true);
		kartenLayer.add(skandinavienText);

		JLabel mittelEuropaText  = new JLabel("3");
		mittelEuropaText.setLocation(482, 103);
		mittelEuropaText.setSize(new Dimension(10,10));
		mittelEuropaText.setBackground(Color.WHITE);
		mittelEuropaText.setForeground(Color.RED);
		mittelEuropaText.setOpaque(true);
		kartenLayer.add(mittelEuropaText);
		
		JLabel suedEuropaText  = new JLabel("3");
		suedEuropaText.setLocation(517, 131);
		suedEuropaText.setSize(new Dimension(10,10));
		suedEuropaText.setBackground(Color.WHITE);
		suedEuropaText.setForeground(Color.RED);
		suedEuropaText.setOpaque(true);
		kartenLayer.add(suedEuropaText);
		
		JLabel ukraineText  = new JLabel("9");
		ukraineText.setLocation(548, 98);
		ukraineText.setSize(new Dimension(10,10));
		ukraineText.setBackground(Color.WHITE);
		ukraineText.setForeground(Color.ORANGE);
		ukraineText.setOpaque(true);
		kartenLayer.add(ukraineText);

		JLabel grossbritanienText  = new JLabel("4");
		grossbritanienText.setLocation(407,105);
		grossbritanienText.setSize(new Dimension(10,10));
		grossbritanienText.setBackground(Color.WHITE);
		grossbritanienText.setOpaque(true);	
		kartenLayer.add(grossbritanienText);
		
		JLabel westEuropaText  = new JLabel("1");
		westEuropaText.setLocation(424, 150);
		westEuropaText.setSize(new Dimension(10,10));
		westEuropaText.setBackground(Color.WHITE);
		westEuropaText.setOpaque(true);	
		kartenLayer.add(westEuropaText);

	
		
		//---------------
		
		JLabel nordwestAfrikaText  = new JLabel("0");
		nordwestAfrikaText.setLocation(447, 224);
		nordwestAfrikaText.setSize(new Dimension(10,10));
		nordwestAfrikaText.setBackground(Color.WHITE);
		nordwestAfrikaText.setOpaque(true);	
		kartenLayer.add(nordwestAfrikaText);
		
		
		JLabel aegyptenText  = new JLabel("3");
		aegyptenText.setLocation(517, 200);
		aegyptenText.setSize(new Dimension(10,10));
		aegyptenText.setBackground(Color.WHITE);
		aegyptenText.setForeground(Color.RED);
		aegyptenText.setOpaque(true);
		kartenLayer.add(aegyptenText);

		JLabel ostafrikaText  = new JLabel("3");
		ostafrikaText.setLocation(566, 263);
		ostafrikaText.setSize(new Dimension(10,10));
		ostafrikaText.setBackground(Color.WHITE);
		ostafrikaText.setForeground(Color.BLUE);
		ostafrikaText.setOpaque(true);
		kartenLayer.add(ostafrikaText);
		
		JLabel kongoText  = new JLabel("9");
		kongoText.setLocation(517, 297);
		kongoText.setSize(new Dimension(10,10));
		kongoText.setBackground(Color.WHITE);
		kongoText.setForeground(Color.RED);
		kongoText.setOpaque(true);
		kartenLayer.add(kongoText);
		
		JLabel suedAfrikaText  = new JLabel("9");
		suedAfrikaText.setLocation(517, 381);
		suedAfrikaText.setSize(new Dimension(10,10));
		suedAfrikaText.setBackground(Color.WHITE);
		suedAfrikaText.setForeground(Color.RED);
		suedAfrikaText.setOpaque(true);
		kartenLayer.add(suedAfrikaText);
		
		JLabel madagaskaText  = new JLabel("3");
		madagaskaText.setLocation(597, 366);
		madagaskaText.setSize(new Dimension(10,10));
		madagaskaText.setBackground(Color.WHITE);
		madagaskaText.setForeground(Color.BLUE);
		madagaskaText.setOpaque(true);
		kartenLayer.add(madagaskaText);
		
//----------------

		JLabel kamtschatkaText  = new JLabel("9");
		kamtschatkaText.setLocation(906, 62);
		kamtschatkaText.setSize(new Dimension(10,10));
		kamtschatkaText.setBackground(Color.WHITE);
		kamtschatkaText.setForeground(Color.RED);
		kamtschatkaText.setOpaque(true);
		kartenLayer.add(kamtschatkaText);

		JLabel ostAustralienText  = new JLabel("7");
		ostAustralienText.setLocation(912, 430);
		ostAustralienText.setSize(new Dimension(10,10));
		ostAustralienText.setBackground(Color.WHITE);
		ostAustralienText.setOpaque(true);
		kartenLayer.add(ostAustralienText);


	//	kartenLayer.add(alaska);
		kartenLayer.add(myImageZeigen);
		kartenLayer.add(myImage);

		orginalLayerPane.add(kartenLayer);
		
		
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
