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
	
	JButton soldatBtn;
	JButton reiterBtn;
	JButton kanoneBtn;
	JButton alleBtn;
	private JButton abbrechenButton;
	private JLabel infoLabel;
	
	PhaseEinheitenVerteilen phaseEins;
	private InformationsPanel informationsPanel;
	

	public KartenTauschPanel(Risiko risiko, int aktuellerSpieler, PhaseEinheitenVerteilen phaseEins, InformationsPanel informationsPanel) {
		this.informationsPanel = informationsPanel;
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
		
		soldatBtn = new JButton("3 Soldaten eintauschen");
		soldatBtn.setActionCommand("s");
		this.add(soldatBtn);
		
		reiterBtn = new JButton("3 Reiter eintauschen");
		reiterBtn.setActionCommand("r");
		this.add(reiterBtn);
		
		kanoneBtn = new JButton("3 Kanonen eintauschen");
		kanoneBtn.setActionCommand("k");
		this.add(kanoneBtn);
		
		alleBtn = new JButton("jeweils 1 eintauschen");
		alleBtn.setActionCommand("a");
		this.add(alleBtn);
		
		JLabel leereZeile = new JLabel(" ");
		this.add(leereZeile);
		
		abbrechenButton = new JButton("Abbrechen");
		this.add(abbrechenButton);
		
		infoLabel = new JLabel(" ");
		this.add(infoLabel);
		this.setVisible(false);
		
	}
	
	public void tauscheMoeglichkeitenPruefen() {
		soldatBtn.setEnabled(false);
		reiterBtn.setEnabled(false);
		kanoneBtn.setEnabled(false);
		alleBtn.setEnabled(false);
		String typName = "";
		int [] kartenAnzahlProTyp = risiko.getKartenAnzahlProTyp(aktuellerSpieler);
		for( int i = 0; i < 4; i++) {
			typName = risiko.kannTypTauschen(aktuellerSpieler, i, kartenAnzahlProTyp);
			System.out.println(typName + " Einheitenkarten!!");
			switch(typName) {
			case "soldat":
				soldatBtn.setEnabled(true);
				break;
			case "reiter":
				reiterBtn.setEnabled(true);
				break;
			case "kanone":
				kanoneBtn.setEnabled(true);
				break;
			case "alle":
				alleBtn.setEnabled(true);
				break;
			default:
			
			}
		}
	}

	private void enableButton(int typ) {
		switch(typ) {
		case 0:
			soldatBtn.setEnabled(true);
			break;
		case 1:
			reiterBtn.setEnabled(true);
			break;
		case 2:
			kanoneBtn.setEnabled(true);
			break;
		}
	}

	private void setUpEvents() {
		soldatBtn.addActionListener(tauscheListener);
		reiterBtn.addActionListener(tauscheListener);
		kanoneBtn.addActionListener(tauscheListener);
		alleBtn.addActionListener(tauscheListener);
		
		abbrechenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoLabel.setText(" ");
				KartenTauschPanel.this.setVisible(false);
				phaseEins.setVisible(true);
				phaseEins.updateVerteilbareEinheiten();
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
				risiko.berechneVerteilbareEinheiten(tauscheBonus, aktuellerSpieler);
				informationsPanel.setEinheitenKartenNachricht(aktuellerSpieler);
				tauscheMoeglichkeitenPruefen();
			} catch (FalscheEingabeException | NichtGenugKartenFuerAktionException e1) {
				//Bereits abgefangen
			} 
		}
	}

}
