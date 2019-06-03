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
	
	private void initialize(String spielername, int screenWidth, int screenHeight,  JLayeredPane lp) {
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		JLayeredPane layers = lp;
		JLayeredPane mapPanel = new JLayeredPane();// new JPanel();
		mapPanel.setBounds(0, 0, 1024, 525);
		
		ImageIcon face = new ImageIcon("images/Gewonnen.png"); 
		JLabel fLab = new JLabel(face);
        fLab.setBounds(25, 25, 100, 100);
        fLab.setPreferredSize(new Dimension(100,100));
//        layers.add(fLab);
//        JLabel fLab2 = new JLabel(face);
//        fLab2.setBounds(75, 75, 100, 100);
//        layers.add(fLab2);
//        
//		
		ImagePanel myImage = new ImagePanel(this);
//		layers.add(myImage);
		ImagePanelZeigen myImageZeigen = new ImagePanelZeigen(this);
//		//this.add(myImageZeigen);
//		layers.add(myImageZeigen);
//	//	this.add(layers);
		mapPanel.add(fLab);
		mapPanel.add(myImageZeigen);
		mapPanel.add(myImage);
		
		
		
		
		layers.add(mapPanel);
		
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
