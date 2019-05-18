package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseAngreifenBeendeListener;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseNeueEinheitenBeendenListener;

public class PhasenPanel extends JPanel {
	
	Risiko risiko;
	JButton einheitenPhaseBeenden;
	int phasenID;
	PhaseNeueEinheitenBeendenListener actionListenerEinheitenVerteilen;
	
	public PhasenPanel(Risiko risiko, int screenWidth, int screenHeight, PhaseNeueEinheitenBeendenListener actionListenerEinheitenVerteilen, PhaseAngreifenBeendeListener phaseAngreifenBeendeListener) {
		this.risiko = risiko;
		this.actionListenerEinheitenVerteilen = actionListenerEinheitenVerteilen;
		initialize(screenWidth, screenHeight);
	}

	private void initialize(int screenWidth, int screenHeight) {
		JLabel anweisungsText = new JLabel("Phase: Einheiten Verteilen");
		this.add(anweisungsText);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		einheitenPhaseBeenden = new JButton("Einheiten verteilen");
		this.add(einheitenPhaseBeenden);
		
		Dimension lineEndSize = new Dimension(screenWidth/5, screenHeight);
		this.setMinimumSize(lineEndSize);
		this.setPreferredSize(lineEndSize);
		
	}
	
	public void ereignisErzeugt() {
		einheitenPhaseBeenden.addActionListener(actionListenerEinheitenVerteilen);
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
}
