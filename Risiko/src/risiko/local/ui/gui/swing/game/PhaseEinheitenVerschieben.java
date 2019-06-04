
package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhaseEinheitenVerschieben  extends JPanel {
	Risiko risiko;
	InformationsPanel informationsPanel;	
	int aktuellerSpieler;
	
	GridBagConstraints c;
	JLabel phaseVerschiebenText;
	JButton verschiebenBeenden;
	
	JLabel anweisungsLabel1;
	JLabel anweisungsLabel2;
	
	JLabel vonProvinzLabel;
	JLabel vonProvinzName;
	JLabel nachProvinzLabel;
	JLabel nachProvinzName;
	
	JButton einheitenMinus;
	JLabel einheitenLabel;
	JButton einheitenPlus;
	JButton bestaetigenButton;
	
	JButton speicherSpiel;
	
	int einheitenWollen = 0;
	int verschiebbareEinheiten;
	int aktiveWahl = 1;
	int fromProvinzID;
	int toProvinzID;
	String verschiebeProvinzName;
	
	PhaseEinheitenVerschieben(Risiko risiko, InformationsPanel anweisungsPanel, int aktuellerSpieler){
		this.risiko = risiko;
		this.informationsPanel = anweisungsPanel;
		this.aktuellerSpieler = aktuellerSpieler;
				
		setUpUI();
	}
	
	private void setUpUI() {
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		c = new GridBagConstraints();
		c.gridx = 1;

		// leere Zeile
		JLabel leereZeile = new JLabel(" ");
		c.gridy = 0;
		layout.setConstraints(leereZeile, c);
		this.add(leereZeile);
		
		// Text
		phaseVerschiebenText = new JLabel("Phase: Verschieben");
		c.gridy = 1;
		layout.setConstraints(phaseVerschiebenText, c);
		this.add(phaseVerschiebenText);
		
		// leere Zeile
		JLabel leereZeile2 = new JLabel(" ");	
		c.gridy = 2;
		layout.setConstraints(leereZeile2, c);
		this.add(leereZeile2);
		
		c.gridy = 3;
		anweisungsLabel1 = new JLabel("");
		anweisungsLabel1.setText("<html>Willst du Einheiten<p/>innerhalb "
				+ "deiner Provinzen<p/>verschieben?</html>");
		layout.setConstraints(anweisungsLabel1, c);
		this.add(anweisungsLabel1);
		
		// leere Zeile	
		c.gridy = 4;
		JLabel leereZeile4 = new JLabel(" ");
		layout.setConstraints(leereZeile4, c);
		this.add(leereZeile4);
				
		c.gridy = 5;
		anweisungsLabel2 = new JLabel(" ");
		layout.setConstraints(anweisungsLabel2, c);
		this.add(anweisungsLabel2);
		
		// leere Zeile	
		c.gridy = 6;
		JLabel leereZeile6 = new JLabel(" ");
		layout.setConstraints(leereZeile6, c);
		this.add(leereZeile6);

		vonProvinzLabel = new JLabel("Von: ");
		c.gridy = 7;
		c.gridx = 0;
		layout.setConstraints(vonProvinzLabel, c);
		vonProvinzLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		this.add(vonProvinzLabel);

		vonProvinzName = new JLabel(" "); // zuerst leer / geupdatet bei setProvinz
		c.gridy = 7;
		c.gridx = 1;
		layout.setConstraints(vonProvinzName, c);
		this.add(vonProvinzName);
		
		c.gridy = 8;
		c.gridx = 0;
		nachProvinzLabel = new JLabel("Nach: ");
		layout.setConstraints(nachProvinzLabel, c);
		this.add(nachProvinzLabel);
		
		c.gridy = 8;
		c.gridx = 1;
		nachProvinzName = new JLabel(" "); // zuerst leer / geupdatet bei setProvinz
		layout.setConstraints(nachProvinzName, c);
		this.add(nachProvinzName);
		

		einheitenMinus = new JButton("-");
		c.gridy = 9;
		c.gridx = 0;
		layout.setConstraints(einheitenMinus, c);
		einheitenMinus.setEnabled(false);
		this.add(einheitenMinus);
		

		einheitenLabel = new JLabel(einheitenWollen+"");
		c.gridy = 9;
		c.gridx = 1;
		layout.setConstraints(einheitenLabel, c);
		einheitenLabel.setEnabled(false);
		this.add(einheitenLabel);

		einheitenPlus = new JButton("+");
		c.gridy = 9;
		c.gridx = 2;
		layout.setConstraints(einheitenPlus, c);
		einheitenPlus.setEnabled(false);
		this.add(einheitenPlus);
		
		// leere Zeile	
		c.gridx = 1;
		c.gridy = 10;
		JLabel leereZeile8 = new JLabel(" ");
		layout.setConstraints(leereZeile8, c);
		this.add(leereZeile8);
		
		bestaetigenButton = new JButton("Einheiten verschieben");
		c.gridy = 11;
		layout.setConstraints(bestaetigenButton, c);
		bestaetigenButton.setEnabled(false);
		this.add(bestaetigenButton);
		
		// Button
		c.gridy = 12;
		verschiebenBeenden = new JButton("Phase beenden");
		verschiebenBeenden.setActionCommand("1");
		layout.setConstraints(verschiebenBeenden, c);
		this.add(verschiebenBeenden);
		
		c.gridy = 13;
		speicherSpiel = new JButton("Spiel speichern");
		layout.setConstraints(speicherSpiel, c);
		this.add(speicherSpiel);

		this.setVisible(false);
	}
	public void setUpEvents(PhaseBeendenListener phaseBeendenListener, PhaseEinheitenVerteilen phaseEins) {
		
		einheitenMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einheitenWollen > 0) {
					einheitenLabel.setText(--einheitenWollen + "");
				}
				if (einheitenWollen == 0) {
					bestaetigenButton.setEnabled(false);
				}
			}
		});
		
		einheitenPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einheitenWollen < verschiebbareEinheiten) {
					System.out.println(einheitenWollen++);
					einheitenLabel.setText(einheitenWollen + "");
					bestaetigenButton.setEnabled(true);
				}
				if (einheitenWollen == 0) {
					bestaetigenButton.setEnabled(false);
				}
			}
		});
		
		bestaetigenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				risiko.einheitenVerschieben(fromProvinzID, toProvinzID, einheitenWollen);
				verschiebbareEinheiten -= einheitenWollen;
				anweisungsLabel2.setText("<html>Von " + verschiebeProvinzName + 
						"<p/> kannst du noch " + verschiebbareEinheiten + 
						"<p/> Einheiten verschieben.");
				einheitenWollen = 0;
				einheitenLabel.setText(einheitenWollen+"");
				bestaetigenButton.setEnabled(false);
				for(int i = 0; i<2; i++) {
					System.out.println(risiko.getProvinzenVonSpieler(i));
				}
        // Gewinner pruefen
				String gewinner = risiko.einerHatGewonnen(aktuellerSpieler);
				if(!gewinner.equals(""))  {
					//Gewinner Panel
					//String gewinner ausgeben
				}
			}
		});
		
		verschiebenBeenden.addActionListener(phaseBeendenListener);
		verschiebenBeenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhaseEinheitenVerschieben.this.setVisible(false);
				
				informationsPanel.updateInfoPanel(aktuellerSpieler);
				phaseEins.updateData(aktuellerSpieler);
				
			}
		});
		
		speicherSpiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					risiko.speichern(aktuellerSpieler);
					System.out.println("letzter Spieler: " + aktuellerSpieler + "\nSpiel wurde erfolgreich gespeichert");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
	}
	
	public void setProvinz(int provinzID) {
		if(aktiveWahl == 1) {	//Von
			if (risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
				fromProvinzID = provinzID;
				verschiebeProvinzName = risiko.getProvinz(provinzID).getName();
				verschiebbareEinheiten = risiko.getVerschiebbareEinheiten(provinzID);
				System.out.println("Verschiebbar = " + verschiebbareEinheiten);
				anweisungsLabel2.setText("<html>Von " + verschiebeProvinzName + 
						"<p/> kannst du noch " + verschiebbareEinheiten +
						"<p/> Einheiten verschieben.</html>");
				informationsPanel.setNachricht("");
				vonProvinzName.setText(verschiebeProvinzName);
				
				vonProvinzLabel.setBorder(BorderFactory.createEmptyBorder());
				nachProvinzLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				nachProvinzName.setText(" ");
				
				einheitenMinus.setEnabled(false);
				einheitenLabel.setEnabled(false);
				einheitenPlus.setEnabled(false);
				aktiveWahl = 2;
			} else {
				informationsPanel.setNachricht("Diese Provinz gehoert dir nicht!");
			}
		} else {	//Nach
			toProvinzID = provinzID;
			informationsPanel.setNachricht("");
			nachProvinzName.setText(risiko.getProvinz(provinzID).getName());
			aktiveWahl = 1;
			nachProvinzLabel.setBorder(BorderFactory.createEmptyBorder());
			vonProvinzLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			if (risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
				try {
					risiko.validiereGUIVerschieben(provinzID, fromProvinzID);
					einheitenMinus.setEnabled(true);
					einheitenLabel.setEnabled(true);
					einheitenPlus.setEnabled(true);
				} catch (NichtProvinzDesSpielersException | ProvinzNichtNachbarException e) {
					informationsPanel.setNachricht(e.getMessage());
				}
				
			} else {
				informationsPanel.setNachricht("Diese Provinz gehoert dir nicht!");
			}
		}
	}
	
	
}