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

public class PhaseEinheitenVerteilen extends JPanel{
	Risiko risiko;
	int aktuellerSpieler;
	
	GridBagConstraints c;
	GridBagLayout layout;
	
	JLabel phaseEinheitenText;
	JLabel provinzLabel1;
	
	JButton einheitenPlus;
	JLabel einheitenLabel;
	JButton einheitenMinus;
	
	JButton bestaetigenButton;
	JButton einheitenPhaseBeenden;
	
	int einheitenWollen = 0;
	int verteilbareEinheiten;
	private AnweisungsPanel anweisungsPanel;
	int gewaehlteProvinzID = -1;
	
	
	
	public PhaseEinheitenVerteilen(Risiko risiko, AnweisungsPanel anweisungsPanel, int aktuellerSpieler) {
		this.risiko = risiko;
		this.anweisungsPanel = anweisungsPanel;
		this.aktuellerSpieler = aktuellerSpieler;
		verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
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
		provinzLabel1 = new JLabel("Du darfst noch " + verteilbareEinheiten + " Einheiten verteilen.");
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
//		c.gridx = 0;
		einheitenPlus = new JButton("+");
		layout.setConstraints(einheitenPlus, c);
		this.add(einheitenPlus);
		
		c.gridy = 9;
//		c.gridx = 1;
		einheitenLabel = new JLabel(einheitenWollen+"");
		layout.setConstraints(einheitenLabel, c);
		this.add(einheitenLabel);
		
		c.gridy = 10;
//		c.gridx = 2;
		einheitenMinus = new JButton("-");
		layout.setConstraints(einheitenMinus, c);
		this.add(einheitenMinus);
		
		bestaetigenButton = new JButton("Einheiten setzen");
		c.gridy = 11;
		layout.setConstraints(bestaetigenButton, c);
		bestaetigenButton.setEnabled(false);
		this.add(bestaetigenButton);
		
		// Button
		einheitenPhaseBeenden = new JButton("Phase beenden");
		einheitenPhaseBeenden.setActionCommand("2");
		c.gridx = 1;
		c.gridy = 12;
		layout.setConstraints(einheitenPhaseBeenden, c);
		einheitenPhaseBeenden.setEnabled(false);
		this.add(einheitenPhaseBeenden);

	}
	

	public void setUpEvents(ActionListener phaseBeendenListener, PhaseAngriff phaseZwei) {
		
		einheitenPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (verteilbareEinheiten > 0) {
					provinzLabel1.setText("Du darfst noch " + --verteilbareEinheiten + " Einheiten verteilen.");
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
					provinzLabel1.setText("Du darfst noch " + ++verteilbareEinheiten + " Einheiten verteilen.");
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
				
				System.out.println("Einheiten:" + risiko.getProvinzenVonSpieler(aktuellerSpieler).get(gewaehlteProvinzID).getArmeeGroesse());

				if (einheitenWollen > 0) {
					risiko.setzeNeueEinheiten(gewaehlteProvinzID, einheitenWollen, aktuellerSpieler);
				}
				
				if (einheitenWollen == 0 && verteilbareEinheiten == 0) {
					einheitenPhaseBeenden.setEnabled(true);
				}
				System.out.println("Einheiten:" + risiko.getProvinzenVonSpieler(aktuellerSpieler).get(gewaehlteProvinzID).getArmeeGroesse());
			}
		});
		
		
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
		try {
			risiko.validiereProvinz(provinzID, aktuellerSpieler);
			anweisungsPanel.setNachricht("");
			gewaehlteProvinzID = provinzID;
			if (einheitenWollen > 0) {
				bestaetigenButton.setEnabled(true);
			} 
		} catch (NichtProvinzDesSpielersException | ProvinzIDExistiertNichtException e) {
			anweisungsPanel.setNachricht(e.getMessage());
		} 
			
		
		
		
	}
}
