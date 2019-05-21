package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
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
		JLabel aktSpieler = new JLabel("Spieler "+ spielername +" ist an der Reihe");
		
		//panelCenter.setLayout(null);
		aktSpieler.setHorizontalTextPosition(JLabel.RIGHT);
		aktSpieler.setVerticalTextPosition(JLabel.TOP);
		JLabel myImage = new JLabel(new ImageIcon("images/world_map_free_vector.png"));
		myImage.setBounds(0, 0, 50, 50);
		this.add(myImage);
//		JLabel background;
//		setSize(panelCenter.getWidth(),panelCenter.getHeight());
//		setLayout(null);
//		ImageIcon img = new ImageIcon("images/world_map_free_vector.png");
//		
//		background = new JLabel("", img, JLabel.CENTER);
//		background.setBounds(0,0,panelCenter.getWidth(),panelCenter.getHeight());
//		add(background);
		this.add(aktSpieler);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
}
