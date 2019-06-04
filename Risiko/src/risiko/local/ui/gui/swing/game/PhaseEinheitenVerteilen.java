package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private InformationsPanel informationsPanel;
	int gewaehlteProvinzID = -1;
	JButton initialesVerteilenButton;
	InitialeRundeBeendet initialeRundeBeendet;
	
	
	
	public PhaseEinheitenVerteilen(Risiko risiko, InformationsPanel anweisungsPanel, int aktuellerSpieler, InitialeRundeBeendet initialeRundeBeendet) {
		this.risiko = risiko;
		this.informationsPanel = anweisungsPanel;
		this.aktuellerSpieler = aktuellerSpieler;
		this.initialeRundeBeendet = initialeRundeBeendet;
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
		anweisungsLabel1 = new JLabel("Du darfst noch " + 
									  verteilbareEinheiten + 
									  " Einheiten verteilen.");
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
//		einheitenPhaseBeenden.setEnabled(false);
//		this.add(einheitenPhaseBeenden);

	}
	
	public JPanel winnerPanel(String gewinner) {

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 444, 271);
        panel.setBackground(new Color(176, 224, 230));
        getRootPane().add(panel);
        panel.setLayout(null);

        JLabel lblIcon = new JLabel("");
        lblIcon.setBounds(0, 0, 200, 200); //(30,30,
        lblIcon.setIcon(new ImageIcon("images/Gewonnen.png"));
        panel.add(lblIcon);

        JLabel lblText2 = new JLabel(gewinner+" hat gewonnen!");
        lblText2.setVerticalAlignment(SwingConstants.TOP);
      //  lblText2.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lblText2.setHorizontalAlignment(SwingConstants.CENTER);
        lblText2.setBounds(240, 130, 175, 100);
        panel.add(lblText2);

        JLabel lblText1 = new JLabel("Yaaaay!");
        lblText1.setHorizontalAlignment(SwingConstants.CENTER);
//        lblText1.setFont(new Font("Tahoma", Font.ITALIC, 14));
        lblText1.setBounds(240, 30, 175, 100);
        panel.add(lblText1);

        return panel;

    }
	

	public void setUpEvents(ActionListener phaseBeendenListener, PhaseAngriff phaseZwei) {
		
		einheitenPlus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				informationsPanel.setNachricht("");
				if (verteilbareEinheiten > 0) {
					anweisungsLabel1.setText("Du darfst noch " + --verteilbareEinheiten + 
											 " Einheiten verteilen.");
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
				informationsPanel.setNachricht("");
				if (einheitenWollen > 0) {
					anweisungsLabel1.setText("Du darfst noch " + ++verteilbareEinheiten + 
							                 " Einheiten verteilen.");
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
				informationsPanel.setNachricht("");
				
				System.out.println("pID: " + gewaehlteProvinzID);
				System.out.println(risiko.getProvinzenVonSpieler(aktuellerSpieler));
				System.out.println(risiko.getProvinz(gewaehlteProvinzID));
				if (einheitenWollen > 0) {
					risiko.setzeNeueEinheiten(gewaehlteProvinzID, einheitenWollen, aktuellerSpieler);
					risiko.berechneVerteilbareEinheiten(-einheitenWollen, aktuellerSpieler);
					System.out.println("Verteilbare Einheiten: " + risiko.getVerteilbareEinheiten(aktuellerSpieler));
					einheitenWollen = 0;
					einheitenLabel.setText(einheitenWollen+"");
					bestaetigenButton.setEnabled(false);
					initialesVerteilenButton.setEnabled(false);
					gewaehlteProvinzID = -1;					

					if (verteilbareEinheiten == 0) { 
						initialesVerteilenButton.setEnabled(true);
						einheitenPhaseBeenden.setEnabled(true);
					}
//					String gewinner = risiko.einerHatGewonnen(aktuellerSpieler);
//					//gewinner = "tab";
//					System.out.println("<" + gewinner + ">");
//					if(!gewinner.equals(""))  {
//						//Gewinner Panel
//						//String gewinner ausgeben
//						JDialog d = new JDialog(); 
//			  
////			            // create a label 
////			            JLabel l = new JLabel("Glueckwusch!! "+gewinner+" hat gewonnen!"); 
////			            d.add(l); 
////			            // setsize of dialog 
//			            d.setSize(400, 400); 
//			            
//			            d.getContentPane().add(winnerPanel(gewinner));
//			            d.repaint();
//			            d.revalidate();
//			  
//			            // set visibility of dialog 
//			            d.setVisible(true); 
//					}
				}
				
				
			}
		});
		
		initialesVerteilenButton.addActionListener(initialeRundeBeendet);
		initialesVerteilenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				informationsPanel.setNachricht("");
				informationsPanel.setMissionsNachricht(aktuellerSpieler);
				gewaehlteProvinzID = -1;
			}

		});

		
		einheitenPhaseBeenden.addActionListener(phaseBeendenListener);
		einheitenPhaseBeenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				informationsPanel.setNachricht("");
				provinzLabel2.setText(" ");
				PhaseEinheitenVerteilen.this.setVisible(false);
				phaseZwei.setVisible(true);
				informationsPanel.setEinheitenKartenNachricht(aktuellerSpieler);
				einheitenPhaseBeenden.setEnabled(false);
				
			}

		});
	}
	
	public void setProvinz(int provinzID) {		
		if (risiko.validiereGUIProvinz(provinzID, aktuellerSpieler)) {
			informationsPanel.setNachricht("");
			gewaehlteProvinzID = provinzID;
			provinzLabel2.setText(risiko.getProvinz(gewaehlteProvinzID).getName());
			if (einheitenWollen > 0) {
				bestaetigenButton.setEnabled(true);
//				initialesVerteilenButton.setEnabled(true);
			}
		} else {
			informationsPanel.setNachricht("Diese Provinz gehoert dir nicht!");
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
		einheitenPhaseBeenden.setEnabled(false);
	}
	
	private void resetUI(int spieler) {
		aktuellerSpieler = spieler;
		
		verteilbareEinheiten = risiko.getVerteilbareEinheiten(aktuellerSpieler);
		anweisungsLabel1.setText("Du darfst noch " + verteilbareEinheiten + 
				" Einheiten verteilen.");
		
		provinzLabel2.setText(" ");
		
		einheitenWollen = 0;
		einheitenLabel.setText(einheitenWollen+"");
		
	}

	public void updateData(int spieler) {
		
		//TODO: WO KOMMT DIE 7 HER??
		
		if (spieler == risiko.getSpielerAnzahl()-1) {
			spieler = 0;
			risiko.berechneNeueEinheiten(spieler);
		} else {
			risiko.berechneNeueEinheiten(++spieler);
		}
		
		verteilbareEinheiten = risiko.getVerteilbareEinheiten(spieler);
		provinzLabel2.setText(" ");
		anweisungsLabel1.setText("Du darfst noch " + verteilbareEinheiten + " Einheiten verteilen.");
		System.out.println("Durch Verschieben aktualisierte Einheiten: " + 
		verteilbareEinheiten);
		System.out.println("SName: " + risiko.getSpielerName(spieler));
		System.out.println("updateData-> naechsterSpieler: " + risiko.getSpielerName(spieler));
		this.setVisible(true);
	}

	public void setAktuellerSpieler(int spielerID) {
		aktuellerSpieler = spielerID;
	}
}
