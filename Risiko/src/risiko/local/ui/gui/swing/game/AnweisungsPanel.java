package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;

public class AnweisungsPanel extends JPanel {

	Risiko risiko;
	int phasenID;
	
	public AnweisungsPanel(Risiko risiko, int screenWidth, int screenHeight) {
		this.risiko = risiko;
		initialize(screenWidth, screenHeight);
	}

	private void initialize(int screenWidth, int screenHeight) {
		JLabel phasenText = new JLabel("Du darfst deine Einheiten verteilen.");
		this.add(phasenText);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension pageEndSize = new Dimension(screenWidth, screenHeight/5);
		this.setMinimumSize(pageEndSize);
		this.setPreferredSize(pageEndSize);
		
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
}
