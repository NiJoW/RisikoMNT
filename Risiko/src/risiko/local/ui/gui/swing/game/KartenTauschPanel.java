package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.FalscheEingabeException;
import risiko.local.domain.exceptions.NichtGenugKartenFuerAktionException;

public class KartenTauschPanel extends JPanel{
	
	TauscheListener tauscheListener;
	private Risiko risiko;
	private int aktuellerSpieler;
	GridLayout layout;
	
	JButton tauscheMoeglichkeit1;
	JButton tauscheMoeglichkeit2;
	JButton tauscheMoeglichkeit3;
	JButton tauscheMoeglichkeit4;
	private JButton abbrechenButton;
	private JLabel infoLabel;
	
	PhaseEinheitenVerteilen phaseEins;
	

	public KartenTauschPanel(Risiko risiko, int aktuellerSpieler, PhaseEinheitenVerteilen phaseEins) {
		tauscheListener = new TauscheListener();
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		this.phaseEins = phaseEins;
		setUpUI();
		setUpEvents();
	}

	private void setUpUI() {
		layout = new GridLayout(8, 1);
		this.setLayout(layout);
		
		JLabel titel = new JLabel("Einheitenkarten tauschen");
		this.add(titel);
		
		tauscheMoeglichkeit1 = new JButton("3 Soldaten eintauschen");
		tauscheMoeglichkeit1.setEnabled(false);
		tauscheMoeglichkeit1.setActionCommand("s");
		this.add(tauscheMoeglichkeit1);
		
		tauscheMoeglichkeit2 = new JButton("3 Reiter eintauschen");
		tauscheMoeglichkeit2.setEnabled(false);
		tauscheMoeglichkeit1.setActionCommand("r");
		this.add(tauscheMoeglichkeit2);
		
		tauscheMoeglichkeit3 = new JButton("3 Kanonen eintauschen");
		tauscheMoeglichkeit3.setEnabled(false);
		tauscheMoeglichkeit1.setActionCommand("k");
		this.add(tauscheMoeglichkeit3);
		
		tauscheMoeglichkeit4 = new JButton("jeweils 1 eintauschen");
		tauscheMoeglichkeit4.setEnabled(false);
		tauscheMoeglichkeit1.setActionCommand("a");
		this.add(tauscheMoeglichkeit4);
		
		JLabel leereZeile = new JLabel(" ");
		this.add(leereZeile);
		
		abbrechenButton = new JButton("Abbrechen");
		this.add(abbrechenButton);
		
		infoLabel = new JLabel(" ");
		this.add(infoLabel);
		this.setVisible(false);
		tauscheMoeglichkeitenPruefen();
	}
	
	private void tauscheMoeglichkeitenPruefen() {
		
		for(int i = 0; i < 2; i++) {
			Vector <Integer> moeglichkeiten = risiko.kannDreiGleicheVonTypTauschen(aktuellerSpieler, i);
			if(moeglichkeiten != null) {
				enableButton(i);
			}
		}
		
		if(risiko.jeEineEintauschen(aktuellerSpieler)) {
			tauscheMoeglichkeit4.setEnabled(true);
		}
	}

	private void enableButton(int typ) {
		switch(typ) {
		case 0:
			tauscheMoeglichkeit1.setEnabled(true);
			break;
		case 1:
			tauscheMoeglichkeit2.setEnabled(true);
			break;
		case 2:
			tauscheMoeglichkeit3.setEnabled(true);
			break;
		}
	}

	private void setUpEvents() {
		tauscheMoeglichkeit1.addActionListener(tauscheListener);
		tauscheMoeglichkeit2.addActionListener(tauscheListener);
		tauscheMoeglichkeit3.addActionListener(tauscheListener);
		tauscheMoeglichkeit4.addActionListener(tauscheListener);
		
		abbrechenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoLabel.setText(" ");
				KartenTauschPanel.this.setVisible(false);
				phaseEins.setVisible(true);
			}
		});

	}
	
	public class TauscheListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String typ = e.getActionCommand();
			try {
				int tauscheBonus = risiko.einheitenKartenEintauschen(typ, aktuellerSpieler);
				infoLabel.setText("Du hast " + tauscheBonus + " Einheiten erhalten.");
			} catch (FalscheEingabeException | NichtGenugKartenFuerAktionException e1) {
				//Bereits abgefangen
			} 
		}
	}

}
