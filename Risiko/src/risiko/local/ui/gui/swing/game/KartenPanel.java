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
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risiko.local.domain.Risiko;
import risiko.local.valueobjects.Provinz;

public class KartenPanel extends JPanel {
	
	private Risiko risiko;
	private InformationsPanel informationsPanel;
	
	private int aktuellerSpielerID;
	private PhasenPanel phasenPanel;
	private JLabel aktuellerSpieler;
	private BufferedImage karte;
	private int width = 1024;
	private int height = 525;
	private JLayeredPane kartenLayer;
	private Vector<JLabel> einheitenLabel;
	
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
		kartenLayer = new JLayeredPane();
		kartenLayer.setBounds(0, 0, 1024, 525);
		kartenLayer.setLayout(null);
		
		ImagePanel myImage = new ImagePanel(this);
		
		ImagePanelZeigen myImageZeigen = new ImagePanelZeigen(this);
		
		einheitenLabel = new Vector<JLabel>(); 
		Color farbe;
		String anzahlEinheiten;
		
	
		
		addEinheitenLabel(0, 517, 200); // Aegypten
		addEinheitenLabel(1, 517, 297); // Kongo
		addEinheitenLabel(2, 597, 366); // Madagaskar
		addEinheitenLabel(3, 447, 224);	// Nordwestafrika	
		addEinheitenLabel(4, 566, 263); // Ostafrika
		addEinheitenLabel(5, 517, 381); // Suedafrika

		// Todo: Koordinaten
		addEinheitenLabel(6, 517, 381); // Afghanistan
		addEinheitenLabel(7, 517, 381); // China
		addEinheitenLabel(8, 517, 381); // Indien
		addEinheitenLabel(9, 517, 381); // Irkutsk
		addEinheitenLabel(10, 517, 381); // Jakutien
		addEinheitenLabel(11, 517, 381); // Japan
		addEinheitenLabel(12, 906, 62); // Kamtschatka
		addEinheitenLabel(13, 517, 381); // Mittlerer Osten
		addEinheitenLabel(14, 517, 381); // Mongolei
		addEinheitenLabel(15, 517, 381); // Siam
		addEinheitenLabel(16, 517, 381); // Sibirien
		addEinheitenLabel(17, 517, 381); // Ural
		
		
		addEinheitenLabel(18, 517, 381); // Indonesien
		addEinheitenLabel(19, 517, 381); // Neuguinea
		addEinheitenLabel(20, 912, 430); // Ost-Australien
		addEinheitenLabel(21, 517, 381); // West-Australien
		
		addEinheitenLabel(22, 407,105); // Grossbritannien
		addEinheitenLabel(23, 414, 62); // Island
		addEinheitenLabel(24, 482, 103); // Mitteleuropa
		addEinheitenLabel(25, 482, 69); // Skandinavien
		addEinheitenLabel(26, 517, 131); // Sued-Europa
		addEinheitenLabel(27, 548, 98); // Ukraine
		addEinheitenLabel(28, 424, 150); // West-Europa
		
		addEinheitenLabel(29, 86, 62); // Alaska
		addEinheitenLabel(30, 152, 100); // Alberta
		addEinheitenLabel(31, 364, 37); // Groenland
		addEinheitenLabel(32, 134, 212); // Mittel-Amerika
		addEinheitenLabel(33, 170, 62); // Nordwest-Territorium
		addEinheitenLabel(34, 214, 99); // Ontario
		addEinheitenLabel(35, 208, 159); // Oststaaten
		addEinheitenLabel(36, 280, 100); // Quebeck
		addEinheitenLabel(37, 134, 142); // Weststaaten
		
		addEinheitenLabel(38, 270, 478); // Argentinien
		addEinheitenLabel(39, 301, 334); // Brasilien
		addEinheitenLabel(40, 254, 364); // Peru
		addEinheitenLabel(41, 242, 281); // Venezuela
		
		
	
		kartenLayer.add(myImageZeigen);
		kartenLayer.add(myImage);

		orginalLayerPane.add(kartenLayer);
		
		this.setVisible(true);		
	}
	
	public void addEinheitenLabel(int provinzID, int x, int y){
		einheitenLabel.add(new JLabel());
		einheitenLabel.get(provinzID).setLocation(x, y);
		einheitenLabel.get(provinzID).setSize(new Dimension(10,10));
		String anzahlEinheiten = ""+risiko.getProvinz(provinzID).getArmeeGroesse();
		einheitenLabel.get(provinzID).setText(anzahlEinheiten);
		einheitenLabel.get(provinzID).setBackground(Color.WHITE);
		Color farbe = risiko.getProvinz(provinzID).getBesitzer().getFarbe();
		einheitenLabel.get(provinzID).setForeground(farbe);
		einheitenLabel.get(provinzID).setOpaque(true);
		kartenLayer.add(einheitenLabel.get(provinzID));
	}
	


	public void addMapAndPhasenPanel(PhasenPanel phasenPanel) {
		this.phasenPanel = phasenPanel;
		
	//	ImagePanel myImage = new ImagePanel(phasenPanel);
	//	this.add(myImage);
	//	this.setVisible(true);
	}

	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpielerID = spielerID;
		
	//	aktuellerSpieler.setText(risiko.getSpielerName(aktuellerSpielerID));
		System.out.println("naechste Spieler: " + risiko.getSpielerName(aktuellerSpielerID));
	}

	public void setClickedProvinz(int provinzByColor) {
		if(provinzByColor == 42) {
			informationsPanel.setNachricht("Bitte waehle eine Provinz aus.");
		}
		phasenPanel.setClickedProvinz(provinzByColor);

	}
	public void updateEinheitenLabel(int index) {
			einheitenLabel.get(index).setText(""+risiko.getProvinz(index).getArmeeGroesse());
			Color farbe = risiko.getProvinz(index).getBesitzer().getFarbe();
			einheitenLabel.get(index).setForeground(farbe);
	}

}