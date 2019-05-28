package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhaseEinheitenVerschieben  extends JPanel {
	GridBagConstraints c;
	JLabel phaseVerschiebenText;
	JButton verschiebenBeenden;
	
	PhaseEinheitenVerschieben(){
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
		c.gridy = 1;
		phaseVerschiebenText = new JLabel("Phase: Verschieben");
		layout.setConstraints(phaseVerschiebenText, c);
		this.add(phaseVerschiebenText);
		// leere Zeile
		c.gridy = 2;
		JLabel leereZeile2 = new JLabel(" ");
		layout.setConstraints(leereZeile2, c);
		this.add(leereZeile2);
		// Button
		c.gridy = 3;
		verschiebenBeenden = new JButton("Verschieben beenden");
		verschiebenBeenden.setActionCommand("1");
		layout.setConstraints(verschiebenBeenden, c);
		this.add(verschiebenBeenden);

		this.setVisible(false);
	}
	public void setUpEvents(PhaseBeendenListener phaseBeendenListener, PhaseEinheitenVerteilen phaseEins) {
		verschiebenBeenden.addActionListener(phaseBeendenListener);
		verschiebenBeenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhaseEinheitenVerschieben.this.setVisible(false);
				phaseEins.setVisible(true);
			}
		});
		
	}
	public void setProvinz(int provinzIDByColor) {
		// TODO Auto-generated method stub
		
	}
}
