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
	InformationsPanel informationsPanel;
	
	int aktuellerSpielerID;
	private PhasenPanel phasenPanel;
	JLabel aktuellerSpieler;
	BufferedImage karte;
	int width = 1024;
	int height = 525;
	
	public KartenPanel(Risiko risiko, InformationsPanel informationsPanel, int aktuellerSpielerID, int screenWidth, int screenHeight) {
		this.risiko = risiko;
		this.informationsPanel = informationsPanel;
		
		this.aktuellerSpielerID = aktuellerSpielerID;
		System.out.println(aktuellerSpielerID);
		String spielername  = risiko.getSpielerName(aktuellerSpielerID);
		initialize(spielername, screenWidth, screenHeight);
	}
	
	private void initialize(String spielername, int screenWidth, int screenHeight) {
		this.setSize(width,height);
		this.setPreferredSize(new Dimension(width,height));
		
		ImagePanel myImage = new ImagePanel(this);
		this.add(myImage);
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
