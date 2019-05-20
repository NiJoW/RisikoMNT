package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseAngreifenBeendeListener;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseNeueEinheitenBeendenListener;

public class PhasenPanel extends JPanel {
	
	Risiko risiko;
	JLabel phaseEinheitenText;
	JButton einheitenPhaseBeenden;
	JLabel phaseAngriffText;
	JSlider angriffAnzahl;
	JButton angriffBeenden;
	JLabel phaseVerschiebenText;
	
	int phasenID;
	PhaseNeueEinheitenBeendenListener actionListenerEinheitenVerteilen;
	PhaseAngreifenBeendeListener phaseAngreifenBeenden;
	
	public PhasenPanel(Risiko risiko, int screenWidth, int screenHeight, PhaseNeueEinheitenBeendenListener actionListenerEinheitenVerteilen, PhaseAngreifenBeendeListener phaseAngreifenBeenden) {
		this.risiko = risiko;
		this.actionListenerEinheitenVerteilen = actionListenerEinheitenVerteilen;
		this.phaseAngreifenBeenden = phaseAngreifenBeenden;
		initialize(screenWidth, screenHeight);
		ereignisErzeugt();
	}

	private void initialize(int screenWidth, int screenHeight) {
		phaseEinheitenText = new JLabel("Phase: Einheiten Verteilen");
		this.add(phaseEinheitenText);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		einheitenPhaseBeenden = new JButton("Einheiten verteilen");
		this.add(einheitenPhaseBeenden);
		
		
		//-------Angriff
		phaseAngriffText = new JLabel("Phase: Angriff");
		phaseAngriffText.setVisible(false);
		this.add(phaseAngriffText);
		
		angriffAnzahl = new JSlider(0, 3);
		angriffAnzahl.setVisible(false);
		this.add(angriffAnzahl);
		
		angriffBeenden = new JButton("Angriff beenden");
		angriffBeenden.setVisible(false);
		this.add(angriffBeenden);
		
		//-----------------Verschieben
		phaseVerschiebenText = new JLabel("Phase: Verschieben");
		phaseVerschiebenText.setVisible(false);
		this.add(phaseVerschiebenText);
		
		
		Dimension lineEndSize = new Dimension(screenWidth/5, screenHeight);
		this.setMinimumSize(lineEndSize);
		this.setPreferredSize(lineEndSize);
		
	}
	
	public void ereignisErzeugt() {
		einheitenPhaseBeenden.addActionListener(actionListenerEinheitenVerteilen);
		einheitenPhaseBeenden.addActionListener(new ActionListener() {
			//TODO: aendern in Phase beenden Button mit zaehler?

			@Override
			public void actionPerformed(ActionEvent e) {
				phaseEinheitenText.setVisible(false);
				einheitenPhaseBeenden.setVisible(false);
				phaseAngriffText.setVisible(true);
				angriffAnzahl.setVisible(true);
				angriffBeenden.setVisible(true);
			}
			
		});
		
		angriffBeenden.addActionListener(phaseAngreifenBeenden);
		angriffBeenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				phaseAngriffText.setVisible(false);
				angriffBeenden.setVisible(false);
				angriffAnzahl.setVisible(false);
				phaseVerschiebenText.setVisible(true);
			}
			
		});
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
}
