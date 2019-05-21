package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhaseEinheitenVerteilen extends JPanel{
	GridBagConstraints c;
	JLabel phaseEinheitenText;
	JButton einheitenPhaseBeenden;
	
	public PhaseEinheitenVerteilen() {
		 setUpUI();
	}
	private void setUpUI() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		c = new GridBagConstraints();
		c.gridx = 0;

		// leere Zeile
		c.gridy = 0;
		JLabel leereZeile = new JLabel(" ");
		layout.setConstraints(leereZeile, c);
		this.add(leereZeile);
		// Text
		phaseEinheitenText = new JLabel("Phase: Einheiten Verteilen");
		c.gridy = 1;
		layout.setConstraints(phaseEinheitenText, c);
		this.add(phaseEinheitenText);
		// leere Zeilen
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		this.add(leereZeile2);
		c.gridy = 3;
		JLabel leereZeile3 = new JLabel(" ");
		layout.setConstraints(leereZeile3, c);
		this.add(leereZeile3);
		// Button
		einheitenPhaseBeenden = new JButton("Einheiten verteilen");
		einheitenPhaseBeenden.setActionCommand("2");
		c.gridy = 4;
		layout.setConstraints(einheitenPhaseBeenden, c);
		this.add(einheitenPhaseBeenden);

	}
	

	public void setUpEvents(ActionListener phaseBeendenListener, PhaseAngriff phaseZwei) {
		
		einheitenPhaseBeenden.addActionListener(phaseBeendenListener);
		einheitenPhaseBeenden.addActionListener(new ActionListener() {
			// TODO: aendern in Phase beenden Button mit zaehler?

			@Override
			public void actionPerformed(ActionEvent e) {
				PhaseEinheitenVerteilen.this.setVisible(false);
				phaseZwei.setVisible(true);
			}

		});
	}
}
