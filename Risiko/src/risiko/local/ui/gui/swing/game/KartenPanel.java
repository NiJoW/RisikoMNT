package risiko.local.ui.gui.swing.game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;

public class KartenPanel extends JPanel {
	
	Risiko risiko;
	int aktuellerSpielerID;
	
	public KartenPanel(Risiko risiko, int aktuellerSpielerID) {
		this.risiko = risiko;
		this.aktuellerSpielerID = aktuellerSpielerID;
		System.out.println(aktuellerSpielerID);
		String spielername  = risiko.getSpielerName(aktuellerSpielerID);
		initialize(spielername);
	}

	private void initialize(String spielername) {

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
	}
	public void addMap(String spielername) {
		//JLayeredPane layeredPane = new JLayeredPane();
		//layeredPane.setPreferredSize(this.getSize());
		// System.out.println("Breite "+this.getWidth() + " und " + this.getHeight());
		 
		JLabel myImage = new JLabel(new ImageIcon("images/Weltkarte.jpeg"));
		myImage.setPreferredSize(this.getSize());
		this.add(myImage); //layeredPane.add(myImage);
		
		JLabel aktSpieler = new JLabel("Spieler "+ spielername +" ist an der Reihe");
		aktSpieler.setHorizontalTextPosition(JLabel.RIGHT);
		aktSpieler.setVerticalTextPosition(JLabel.TOP);
		this.add(aktSpieler);
		//this.add(layeredPane);
	}
}
