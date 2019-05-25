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
		c.gridx = 1;

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
		// Label fÃ¼r geklickte Provinzen
		c.gridy = 4;
		JLabel provinzLabel1 = new JLabel("Du darfst noch X Einheiten verteilen.");
		layout.setConstraints(provinzLabel1, c);
		this.add(provinzLabel1);
		c.gridy = 5;
		JLabel provinzLabel2 = new JLabel("Auf welches Land möchtest du setzen? ");
		layout.setConstraints(provinzLabel2, c);
		this.add(provinzLabel2);
		c.gridy = 6;
		JLabel provinzLabel3 = new JLabel("Land: ");
		layout.setConstraints(provinzLabel3, c);
		this.add(provinzLabel3);
		c.gridy = 7;
		JLabel provinzLabel4 = new JLabel("Landname "); // zuerst leer / geupdatet bei setProvinz
		layout.setConstraints(provinzLabel4, c);
		this.add(provinzLabel4);
		c.gridy = 8;
		c.gridx = 0;
		JButton einheitenPlus = new JButton("+");
		layout.setConstraints(einheitenPlus, c);
		this.add(einheitenPlus);
		c.gridx = 1;
		JLabel einheitenLabel = new JLabel("ZAHL");
		layout.setConstraints(einheitenLabel, c);
		this.add(einheitenLabel);
		c.gridx = 2;
		JButton einheitenMinus = new JButton("-");
		layout.setConstraints(einheitenMinus, c);
		this.add(einheitenMinus);
		// Button
		einheitenPhaseBeenden = new JButton("Einheiten verteilen");
		einheitenPhaseBeenden.setActionCommand("2");
		c.gridy = 9;
		c.gridx = 1;
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
	
	public void setProvinz(int provinz) {
		
	}
}
