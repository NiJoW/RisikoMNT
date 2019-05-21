package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhasenPanel extends JPanel {

	Risiko risiko;
	JLabel phaseEinheitenText;
	JButton einheitenPhaseBeenden;
	JLabel phaseAngriffText;
	JSlider angriffAnzahl;
	JButton angriffBeenden;
	JLabel phaseVerschiebenText;
	JButton verschiebenBeenden;
	JPanel phaseEins;
	JPanel phaseZwei;
	JPanel phaseDrei;
	GridBagConstraints c;

	int phasenID;
	PhaseBeendenListener phaseBeendenListener;
	

	public PhasenPanel(Risiko risiko, int screenWidth, int screenHeight, PhaseBeendenListener phaseBeendenListener) {
		this.risiko = risiko;
		this.phaseBeendenListener = phaseBeendenListener;
		setUpUI(screenWidth, screenHeight);
		ereignisErzeugt();
	}

	private void setUpUI(int screenWidth, int screenHeight) {
		c = new GridBagConstraints();

		setUpEinheitenSetzen();
		setUpAngriff();
		setUpVerschieben();

		this.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension lineEndSize = new Dimension(screenWidth / 5, screenHeight);
		this.setMinimumSize(lineEndSize);
		this.setPreferredSize(lineEndSize);

	}

	private void setUpEinheitenSetzen() {
		phaseEins = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		phaseEins.setLayout(layout);
		c.gridx = 0;

		// leere Zeile
		c.gridy = 0;
		JLabel leereZeile = new JLabel(" ");
		layout.setConstraints(leereZeile, c);
		phaseEins.add(leereZeile);
		// Text
		phaseEinheitenText = new JLabel("Phase: Einheiten Verteilen");
		c.gridy = 1;
		layout.setConstraints(phaseEinheitenText, c);
		phaseEins.add(phaseEinheitenText);
		// leere Zeilen
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		phaseEins.add(leereZeile2);
		c.gridy = 3;
		JLabel leereZeile3 = new JLabel(" ");
		layout.setConstraints(leereZeile3, c);
		phaseEins.add(leereZeile3);
		// Button
		einheitenPhaseBeenden = new JButton("Einheiten verteilen");
		einheitenPhaseBeenden.setActionCommand("2");
		c.gridy = 4;
		layout.setConstraints(einheitenPhaseBeenden, c);
		phaseEins.add(einheitenPhaseBeenden);

		this.add(phaseEins);
	}

	private void setUpAngriff() {
		phaseZwei = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		phaseZwei.setLayout(layout);
		c.gridx = 0;

		// leere Zeile
		c.gridy = 0;
		JLabel leereZeile = new JLabel(" ");
		layout.setConstraints(leereZeile, c);
		phaseZwei.add(leereZeile);
		// Text
		c.gridy = 1;
		phaseAngriffText = new JLabel("Phase: Angriff");
		layout.setConstraints(phaseAngriffText, c);
		phaseZwei.add(phaseAngriffText);
		// leere Zeile
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		phaseZwei.add(leereZeile2);

		c.gridy = 3;
		angriffAnzahl = new JSlider(0, 3);
		layout.setConstraints(angriffAnzahl, c);
		phaseZwei.add(angriffAnzahl);

		c.gridy = 4;
		angriffBeenden = new JButton("Angriff beenden");
		angriffBeenden.setActionCommand("3");
		layout.setConstraints(angriffBeenden, c);
		phaseZwei.add(angriffBeenden);

		phaseZwei.setVisible(false);
		this.add(phaseZwei);
	}

	private void setUpVerschieben() {
		phaseDrei = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		phaseDrei.setLayout(layout);
		c.gridx = 0;

		// leere Zeile
		c.gridy = 0;
		JLabel leereZeile = new JLabel(" ");
		layout.setConstraints(leereZeile, c);
		phaseDrei.add(leereZeile);
		// Text
		c.gridy = 1;
		phaseVerschiebenText = new JLabel("Phase: Verschieben");
		layout.setConstraints(phaseVerschiebenText, c);
		phaseDrei.add(phaseVerschiebenText);
		// leere Zeile
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		phaseDrei.add(leereZeile2);
		// Button
		c.gridy = 3;
		verschiebenBeenden = new JButton("Verschieben beenden");
		verschiebenBeenden.setActionCommand("1");
		layout.setConstraints(verschiebenBeenden, c);
		phaseDrei.add(verschiebenBeenden);

		phaseDrei.setVisible(false);
		this.add(phaseDrei);
	}

	public void ereignisErzeugt() {
		einheitenPhaseBeenden.addActionListener(phaseBeendenListener);
		einheitenPhaseBeenden.addActionListener(new ActionListener() {
			// TODO: aendern in Phase beenden Button mit zaehler?

			@Override
			public void actionPerformed(ActionEvent e) {
				phaseEins.setVisible(false);
				phaseZwei.setVisible(true);
			}

		});

		angriffBeenden.addActionListener(phaseBeendenListener);
		angriffBeenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				phaseZwei.setVisible(false);
				phaseDrei.setVisible(true);
			}

		});
		verschiebenBeenden.addActionListener(phaseBeendenListener);
		verschiebenBeenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				phaseDrei.setVisible(false);
				phaseEins.setVisible(true);
			}
		});
	}

	public void setPhase(int phasenID) {
		this.phasenID = phasenID;
	}
}
