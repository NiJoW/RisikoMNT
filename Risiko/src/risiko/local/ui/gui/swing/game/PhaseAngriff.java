package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhaseAngriff extends JPanel {
	
	GridBagConstraints c;
	JLabel phaseAngriffText;
	JSlider angriffAnzahl;
	JButton angriffBeenden;
	
	public PhaseAngriff() {
		setUpUI();
	}
	
	private void setUpUI() {
		//this = new JPanel();
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
		c.gridy = 1;
		phaseAngriffText = new JLabel("Phase: Angriff");
		layout.setConstraints(phaseAngriffText, c);
		this.add(phaseAngriffText);
		// leere Zeile
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		this.add(leereZeile2);

		c.gridy = 3;
		angriffAnzahl = new JSlider(0, 3);
		layout.setConstraints(angriffAnzahl, c);
		this.add(angriffAnzahl);

		c.gridy = 4;
		angriffBeenden = new JButton("Angriff beenden");
		angriffBeenden.setActionCommand("3");
		layout.setConstraints(angriffBeenden, c);
		this.add(angriffBeenden);

		this.setVisible(false);
		
	}

	public void setUpEvents(PhaseBeendenListener phaseBeendenListener, PhaseEinheitenVerschieben phaseDrei) {
		angriffBeenden.addActionListener(phaseBeendenListener);
		angriffBeenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PhaseAngriff.this.setVisible(false);
				phaseDrei.setVisible(true);
			}

		});
		
	}
}
