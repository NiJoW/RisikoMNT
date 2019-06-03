package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;

public class KartenTauschPanel extends JPanel{
	
	private Risiko risiko;
	private int aktuellerSpieler;
	GridLayout layout;
	
	JButton tauscheMoeglichkeit1;
	JButton tauscheMoeglichkeit2;
	JButton tauscheMoeglichkeit3;
	JButton tauscheMoeglichkeit4;
	private JButton abbrechenButton;
	

	public KartenTauschPanel(Risiko risiko, int aktuellerSpieler) {
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		setUpUI();
		setUpEvents();
	}

	private void setUpUI() {
		layout = new GridLayout(6, 1);
		this.setLayout(layout);
		
		JLabel titel = new JLabel("Einheitenkarten tauschen");
		this.add(titel);
		
		tauscheMoeglichkeit1 = new JButton("3 Soldaten eintauschen");
		this.add(tauscheMoeglichkeit1);
		
		tauscheMoeglichkeit2 = new JButton("3 Reiter eintauschen");
		this.add(tauscheMoeglichkeit2);
		
		tauscheMoeglichkeit3 = new JButton("3 Kanonen eintauschen");
		this.add(tauscheMoeglichkeit3);
		
		tauscheMoeglichkeit4 = new JButton("jeweils 1 eintauschen");
		this.add(tauscheMoeglichkeit4);
		
		abbrechenButton = new JButton("Abbrechen");
		this.add(abbrechenButton);
		
		tauscheMoeglichkeitenPruefen();
	}
	
	private void tauscheMoeglichkeitenPruefen() {
		
	}

	private void setUpEvents() {
		
	}

}
