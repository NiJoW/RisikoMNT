package risiko.local.ui.gui.swing.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.ui.gui.swing.game.PhasenPanel.InitialeRundeBeendet;

public class PhaseEinheitenVerteilen extends JPanel{
	Risiko risiko;
	int aktuellerSpieler;
	
	GridBagConstraints c;
	GridBagLayout layout;
	
	JLabel phaseEinheitenText;
	JLabel anweisungsLabel1;
	
	JLabel provinzLabel2;
	
	JButton einheitenPlus;
	JLabel einheitenLabel;
	JButton einheitenMinus;
	
	JButton bestaetigenButton;
	JButton einheitenPhaseBeenden;
	
	int einheitenWollen = 0;
	int verteilbareEinheiten;
	private InformationsPanel anweisungsPanel;
	int gewaehlteProvinzID = -1;
	JButton initialesVerteilenButton;
	InitialeRundeBeendet initialeRundeBeendet;
	
	
	
	public PhaseEinheitenVerteilen(Risiko risiko, InformationsPanel anweisungsPanel, int aktuellerSpieler, InitialeRundeBeendet initialeRundeBeendet) {
		this.risiko = risiko;
		this.anweisungsPanel = anweisungsPanel;
		this.aktuellerSpieler = aktuellerSpieler;
		this.initialeRundeBeendet = initialeRundeBeendet;
		setUpUI();
	}
	
	private void setUpUI() {
		layout = new GridBagLayout();
		this.setLayout(layout);
		c = new GridBagConstraints();
		
		c.gridx = 1;
		c.gridy = 0;
		JLabel leereZeile = new JLabel(" ");
		layout.setConstraints(leereZeile, c);
		this.add(leereZeile);
		
		// Text
		phaseEinheitenText = new JLabel("Vorbereitung: Einheiten setzen");
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
		

		c.gridy = 4;
		verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
		anweisungsLabel1 = new JLabel("Du darfst noch " + verteilbareEinheiten + " Einheiten verteilen.");
		layout.setConstraints(anweisungsLabel1, c);
		this.add(anweisungsLabel1);
		
		c.gridy = 5;
		JLabel anweisungsLabel2 = new JLabel("Auf welche Provinz m�chtest du setzen? ");
		layout.setConstraints(anweisungsLabel2, c);
		this.add(anweisungsLabel2);

		// Label für geklickte Provinzen
		c.gridy = 6;
		JLabel provinzLabel1 = new JLabel("Provinz: ");
		layout.setConstraints(provinzLabel1, c);
		this.add(provinzLabel1);
		
		c.gridy = 7;
		provinzLabel2 = new JLabel(" "); // zuerst leer / geupdatet bei setProvinz
		layout.setConstraints(provinzLabel2, c);
		this.add(provinzLabel2);
		
		c.gridy = 8;
//		c.gridx = 0;
		einheitenMinus = new JButton("-");
		layout.setConstraints(einheitenMinus, c);
		this.add(einheitenMinus);
		
		c.gridy = 9;
//		c.gridx = 1;
		einheitenLabel = new JLabel(einheitenWollen+"");
		layout.setConstraints(einheitenLabel, c);
		this.add(einheitenLabel);
		
		c.gridy = 10;
//		c.gridx = 2;
		einheitenPlus = new JButton("+");
		layout.setConstraints(einheitenPlus, c);
		this.add(einheitenPlus);
		
		
		bestaetigenButton = new JButton("Einheiten setzen");
		c.gridy = 11;
		layout.setConstraints(bestaetigenButton, c);
		bestaetigenButton.setEnabled(false);
		this.add(bestaetigenButton);
		
		initialesVerteilenButton = new JButton("fertig");
		c.gridx = 1;
		c.gridy = 12;
		layout.setConstraints(initialesVerteilenButton, c);
		initialesVerteilenButton.setEnabled(false);
		this.add(initialesVerteilenButton);
		
		// Button
		einheitenPhaseBeenden = new JButton("Phase beenden");
		einheitenPhaseBeenden.setActionCommand("2");
		c.gridx = 1;
		c.gridy = 12;
		layout.setConstraints(einheitenPhaseBeenden, c);
		einheitenPhaseBeenden.setEnabled(false);
//		this.add(einheitenPhaseBeenden);

	}
	

	public void setUpEvents(ActionListener phaseBeendenListener, PhaseAngriff phaseZwei) {
		
		einheitenPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (verteilbareEinheiten > 0) {
					anweisungsLabel1.setText("Du darfst noch " + --verteilbareEinheiten + " Einheiten verteilen.");
					einheitenLabel.setText(++einheitenWollen + "");
				}
				if(einheitenWollen > 0 && gewaehlteProvinzID != -1) {
					bestaetigenButton.setEnabled(true);
				}
			}
		});
		
		einheitenMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einheitenWollen > 0) {
					anweisungsLabel1.setText("Du darfst noch " + ++verteilbareEinheiten + " Einheiten verteilen.");
					einheitenLabel.setText(--einheitenWollen + "");
				}
				if (einheitenWollen == 0) {
					bestaetigenButton.setEnabled(false);
				}
			}
		});
		
		bestaetigenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				System.out.println("pID: " + gewaehlteProvinzID);
				System.out.println(risiko.getProvinzenVonSpieler(aktuellerSpieler));
				System.out.println(risiko.getProvinz(gewaehlteProvinzID));
				if (einheitenWollen > 0) {
					risiko.setzeNeueEinheiten(gewaehlteProvinzID, einheitenWollen, aktuellerSpieler);
					einheitenWollen = 0;
					einheitenLabel.setText(einheitenWollen+"");
					bestaetigenButton.setEnabled(false);
					initialesVerteilenButton.setEnabled(false);
					
					String gewinner = risiko.einerHatGewonnen(aktuellerSpieler);
					if(gewinner.equals(""))  {
						//Gewinner Panel
						//String gewinner ausgeben
					}
				}
				
				if (verteilbareEinheiten == 0) { //einheitenWollen == 0 && 
					initialesVerteilenButton.setEnabled(true);
					einheitenPhaseBeenden.setEnabled(true);
				}
				
			}
		});
		
		initialesVerteilenButton.addActionListener(initialeRundeBeendet);
		
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
	
	public void setProvinz(int provinzID) {		
		if (risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
			anweisungsPanel.setNachricht("");
			gewaehlteProvinzID = provinzID;
			provinzLabel2.setText(risiko.getProvinz(gewaehlteProvinzID).getName());
			if (einheitenWollen > 0) {
				bestaetigenButton.setEnabled(true);
				initialesVerteilenButton.setEnabled(true);
			}
		} else {
			anweisungsPanel.setNachricht("Diese Provinz gehoert dir nicht!");
		}
				
	}

	public void initialesVerteilen(int spielerID) {
		resetUI(spielerID);
		
		initialesVerteilenButton.setEnabled(false);
		initialesVerteilenButton.setActionCommand(++spielerID+"");
	}

	public void beginneSpiel(int spieler) {
		resetUI(spieler);
		phaseEinheitenText.setText("Phase: Einheiten verteilen");
		initialesVerteilenButton.setVisible(false);
		
		this.add(einheitenPhaseBeenden);
	}
	
	private void resetUI(int spieler) {
		aktuellerSpieler = spieler;
		
		verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
		anweisungsLabel1.setText("Du darfst noch " + verteilbareEinheiten + " Einheiten verteilen.");
		
		provinzLabel2.setText(" ");
		
		einheitenWollen = 0;
		einheitenLabel.setText(einheitenWollen+"");
		
	}
}
