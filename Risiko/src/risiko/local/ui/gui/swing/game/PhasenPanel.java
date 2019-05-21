package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhasenPanel extends JPanel {

	Risiko risiko;
	
	GridBagConstraints c;
	PhaseEinheitenVerteilen phaseEins;
	PhaseAngriff phaseZwei;
	PhaseEinheitenVerschieben phaseDrei;

	int phasenID;
	PhaseBeendenListener phaseBeendenListener;
	

	public PhasenPanel(Risiko risiko, int screenWidth, int screenHeight, PhaseBeendenListener phaseBeendenListener) {
		this.risiko = risiko;
		this.phaseBeendenListener = phaseBeendenListener;
		setUpUI(screenWidth, screenHeight);
		ereignisErzeugt();
	}

	private void setUpUI(int screenWidth, int screenHeight) {
		phaseEins = new PhaseEinheitenVerteilen();
		this.add(phaseEins);
		phaseZwei = new PhaseAngriff();
		this.add(phaseZwei);
		phaseDrei = new PhaseEinheitenVerschieben();
		this.add(phaseDrei);

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension lineEndSize = new Dimension(screenWidth / 5, screenHeight);
		this.setMinimumSize(lineEndSize);
		this.setPreferredSize(lineEndSize);
	}


	public void ereignisErzeugt() {
		phaseEins.setUpEvents(phaseBeendenListener, phaseZwei);
		phaseZwei.setUpEvents(phaseBeendenListener, phaseDrei);
		phaseDrei.setUpEvents(phaseBeendenListener, phaseEins);
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
}
