package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
import risiko.local.domain.exceptions.EigeneProvinzAngreifenException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhaseAngriff extends JPanel {
	Risiko risiko;
	InformationsPanel anweisungsPanel;
	int aktuellerSpieler;
	int gewaehltFromID;
	int gewaehltToID;
	int aktiveWahl = 1;

	GridBagConstraints c;
	JLabel phaseAngriffText;

	JLabel provinzLabel1;
	JLabel provinzLabel2;
	JLabel vonProvinzLabel;
	JLabel nachProvinzLabel;
	
	JButton einheitenPlus;
	JLabel einheitenLabel;
	JButton einheitenMinus;

	int einheitenWollen = 0;
	int angriffsID = -1;
	int verteidigerID = -1;

	JButton wuerfelButton;
	JButton angriffBeenden;

	public PhaseAngriff(Risiko risiko, InformationsPanel anweisungsPanel, int aktuellerSpieler) {
		this.risiko = risiko;
		this.anweisungsPanel = anweisungsPanel;
		this.aktuellerSpieler = aktuellerSpieler;
		setUpUI();
	}

	private void setUpUI() {
		// this = new JPanel();
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
		c.gridx = 0;
		provinzLabel1 = new JLabel(" Von:  ");
		provinzLabel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		layout.setConstraints(provinzLabel1, c);
		this.add(provinzLabel1);

		c.gridy = 3;
		c.gridx = 1;
		vonProvinzLabel = new JLabel(" "); // zuerst leer / geupdatet bei setProvinz
		layout.setConstraints(vonProvinzLabel, c);
		this.add(vonProvinzLabel);

		c.gridy = 4;
		c.gridx = 0;
		provinzLabel2 = new JLabel(" Nach: ");
		layout.setConstraints(provinzLabel2, c);
		this.add(provinzLabel2);

		c.gridy = 4;
		c.gridx = 1;
		nachProvinzLabel = new JLabel(" "); // zuerst leer / geupdatet bei setProvinz
		layout.setConstraints(nachProvinzLabel, c);
		this.add(nachProvinzLabel);

		c.gridy = 5;
		c.gridx = 0;
		einheitenMinus = new JButton("-");
		einheitenMinus.setEnabled(false);
		layout.setConstraints(einheitenMinus, c);
		this.add(einheitenMinus);
		

		c.gridy = 5;
		c.gridx = 1;
		einheitenLabel = new JLabel(einheitenWollen + "");
		einheitenLabel.setEnabled(false);
		layout.setConstraints(einheitenLabel, c);
		this.add(einheitenLabel);

		c.gridy = 5;
		c.gridx = 2;
		einheitenPlus = new JButton("+");
		einheitenPlus.setEnabled(false);
		layout.setConstraints(einheitenPlus, c);
		this.add(einheitenPlus);

		wuerfelButton = new JButton("Angreifen");
		c.gridy = 6;
		c.gridx = 1;
		layout.setConstraints(wuerfelButton, c);
		wuerfelButton.setEnabled(false);
		this.add(wuerfelButton);

		c.gridy = 7;
		angriffBeenden = new JButton("Phase beenden");
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
				anweisungsPanel.setNachricht("");
			}

		});

		
		einheitenPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einheitenWollen < 3) {
					einheitenLabel.setText(++einheitenWollen + "");
				}
				try {
					risiko.validiereAngriffEingaben(gewaehltFromID, gewaehltToID, aktuellerSpieler, einheitenWollen);
					wuerfelButton.setEnabled(true);
				} catch (Exception e1) {
					anweisungsPanel.setNachricht(e1.getMessage());
				}
			}
		});
		
		einheitenMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (einheitenWollen > 1) {
					einheitenLabel.setText(--einheitenWollen + "");
				}
			}
		});
		
		wuerfelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("einheitenWollen: "+einheitenWollen);
				String verteidiger = risiko.getProvinz(gewaehltToID).getBesitzer().getName();
				int[] wuerfelErgebnisse = risiko.wuerfeln(einheitenWollen, gewaehltToID);
				

				try {
					String[][] wuerfelVergleich = risiko.angreifen(gewaehltFromID, gewaehltToID, einheitenWollen, wuerfelErgebnisse);
					String ausgabe = "<html>";
//					for(int i=0; i<wuerfelVergleich.length; i++) {
//						for(int j=0; j<wuerfelVergleich[i].length; j++) {
//							ausgabe += wuerfelVergleich[i][j] +" ";
//						}
//				}
					// Ausgaben
					for (int t = 0; t < einheitenWollen; t++) {
						ausgabe += "Spieler " + risiko.getSpielerName(aktuellerSpieler) + " hat eine " + wuerfelErgebnisse[t]
								+ " gewuerfelt!<p/>";
					}
					for (int k = einheitenWollen; k < wuerfelErgebnisse.length; k++) {
						ausgabe += "Der verteidigende Spieler " + verteidiger + " hat eine " + wuerfelErgebnisse[k]
								+ " gewuerfelt!<p/>";
					}
					// Ergebnisse der einzelnen Wurf-Vergleiche
					ausgabe += "Vergleich der Wuerfel: <p/>";
					ausgabe += "Angreifer: " + wuerfelVergleich[0][0] + ", Verteidiger: " + wuerfelVergleich[0][1] + " -> "
							+ wuerfelVergleich[0][2] + " verliert eine Einheit.<p/>";
					if (wuerfelVergleich[1][0] != null) {
						ausgabe += "Angreifer: " + wuerfelVergleich[1][0] + ", Verteidiger: " + wuerfelVergleich[1][1]
								+ " -> " + wuerfelVergleich[1][2] + " verliert eine Einheit.<p/>";
					}

					if (risiko.getProvinz(gewaehltToID).getBesitzer().getName().equals(risiko.getSpielerName(aktuellerSpieler))) {
						risiko.provinzWurdeErobert(aktuellerSpieler); //aendert boolean, um Einheitskarte zu bekommen (boolean provinzErobert)
						ausgabe += "*********************<p/>";
						ausgabe += risiko.getSpielerName(aktuellerSpieler) + " hat die Provinz "
								+ risiko.getProvinz(gewaehltToID).getName() + " von " + verteidiger + " erobert!<p/>";
						ausgabe += "*********************";
						// Provinz erobert
	//	//TODO:				einheitenNachruecken(aktuellerSpieler, gewaehltFromID, gewaehltToID);
					
					
					} else {
						ausgabe += "Der Verteidiger " + verteidiger + " hat seine Pronvinz verteidigt.";
					}
					ausgabe += "</html>";
					anweisungsPanel.setNachricht(ausgabe);
					// Gewinner pruefen
					String gewinner = risiko.einerHatGewonnen(aktuellerSpieler);
					if(!gewinner.equals(""))  {
						//Gewinner Panel
						//String gewinner ausgeben
					}
				}catch(ProvinzNichtNachbarException ex) {
					//bereits zuvor geprueft, nicht ausgeben
				}
				
			}
		});
		
		
		
	}
	
	
	
	

	public void setProvinz(int provinzID) {
		System.out.println("Wahl: " + aktiveWahl);
		if (aktiveWahl == 1) { //VON
			if (risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
				anweisungsPanel.setNachricht("Text");
				gewaehltFromID = provinzID;
				vonProvinzLabel.setText(risiko.getProvinz(gewaehltFromID).getName());
				aktiveWahl = 2;
				provinzLabel1.setBorder(BorderFactory.createEmptyBorder());
				provinzLabel2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				nachProvinzLabel.setText(" ");
				einheitenWollen = 0;
				einheitenLabel.setText(einheitenWollen+"");
				einheitenPlus.setEnabled(false);
				einheitenLabel.setEnabled(false);
				einheitenMinus.setEnabled(false);
			} else {
				anweisungsPanel.setNachricht("Diese Provinz gehoert dir nicht!");
			}
		} else { //NACH
			
			if (!risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
				anweisungsPanel.setNachricht("sind valinach");
				gewaehltToID = provinzID;
				System.out.println("ToProvinz: " + risiko.getProvinz(gewaehltToID).getName());
				nachProvinzLabel.setText(risiko.getProvinz(gewaehltToID).getName());
				aktiveWahl = 1;
				provinzLabel2.setBorder(BorderFactory.createEmptyBorder());
				provinzLabel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				try {
//					risiko.validiereAngriffEingaben(gewaehltFromID, gewaehltToID, aktuellerSpieler, einheitenWollen);
					risiko.provinzBenachbart(gewaehltFromID, gewaehltToID);
					einheitenPlus.setEnabled(true);
					einheitenLabel.setEnabled(true);
					einheitenMinus.setEnabled(true);
				} catch (Exception e) {
					anweisungsPanel.setNachricht(e.getMessage());
				}	 
				
			} else {
				anweisungsPanel.setNachricht("Diese Provinz kannst du nicht angreifen!");
			}
		}
		
			
		
		
		
	}
}
