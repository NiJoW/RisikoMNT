package risiko.local.ui.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;
import risiko.local.ui.gui.swing.RisikoMainGUI.StarteSpielActionListener;
import risiko.local.valueobjects.Spieler;

public class AnmeldefensterGUI extends JPanel {

	public interface LadeListener {
		public void ladeSpiel(int spielID, String name);
	}

	Risiko risiko;
	LadeListener ladeListener;

	StarteSpielActionListener starteSpielActionListener;
	JDialog anmeldeFenster;
	JPanel anmeldePanel;
	JPanel ladePanel;
	GridBagLayout anmeldeLayout;
	GridBagConstraints c;
	
	GridBagLayout ladeLayout;

	JButton spielerAnmeldenButton;
	JTextField s1name;
	JLabel s2;
	JTextField s2name;
	JLabel s3;
	JTextField s3name;
	JLabel s4;
	JTextField s4name;
	JLabel s5;
	JTextField s5name;
	JLabel s6;
	JTextField s6name;
	JButton neuesSpiel;
	JButton ladeSpiel;

	String nameS1;
	String nameS2;
	String nameS3;
	String nameS4;
	String nameS5;
	String nameS6;
	
	
	List<String> spielNamen;
	
	JButton bestaetigungsButton;
	String name = null;
	int spielID;

	public AnmeldefensterGUI(Risiko risiko, StarteSpielActionListener starteSpielActionListener, LadeListener ladeListener) {
		this.risiko = risiko;
		this.starteSpielActionListener = starteSpielActionListener;
		this.ladeListener = ladeListener;
		initialize();
		ereignisErzeugt();
	}

	private void initialize() {
		anmeldeFenster = new JDialog();
		anmeldeFenster.setTitle("Bitte Anmelden");
		anmeldeFenster.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
		ladePanel = new JPanel();
		anmeldePanel = new JPanel();

		JLabel welcomeText = new JLabel("Willkommen bei unserem Risiko!!!!");
		welcomePanel.setBackground(Color.blue);
		welcomePanel.setPreferredSize(new Dimension(400, 200));
		welcomePanel.add(welcomeText);

		anmeldeLayout = new GridBagLayout();
		anmeldePanel.setLayout(anmeldeLayout);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.1;

		JLabel bitteAnmelden = new JLabel("Bitte Spieler anmelden:");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(bitteAnmelden, c);
		anmeldePanel.add(bitteAnmelden);

		// Spieler 1

		JLabel s1 = new JLabel("Spieler 1:");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s1, c);
		anmeldePanel.add(s1);

		s1name = new JTextField();
		s1name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 1;
		anmeldeLayout.setConstraints(s1name, c);
		anmeldePanel.add(s1name);
		
		// Button
		spielerAnmeldenButton = new JButton("Spieler anmelden");
		c.gridx = 3;
		c.gridy = 1;
		anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
		anmeldePanel.add(spielerAnmeldenButton);

		// Spieler 2
		s2 = new JLabel("Spieler 2:");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s2, c);

		s2name = new JTextField();
		s2name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 2;
		anmeldeLayout.setConstraints(s2name, c);

		// Spieler 3

		s3 = new JLabel("Spieler 3:");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s3, c);

		s3name = new JTextField();
		s3name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 3;
		anmeldeLayout.setConstraints(s3name, c);

		// Spieler 4
		s4 = new JLabel("Spieler 4:");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s4, c);

		s4name = new JTextField();
		s4name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 4;
		anmeldeLayout.setConstraints(s4name, c);

		// Spieler 5
		s5 = new JLabel("Spieler 5:");
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s5, c);

		s5name = new JTextField();
		s5name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 5;
		anmeldeLayout.setConstraints(s5name, c);

		// Spieler 6
		s6 = new JLabel("Spieler 6:");
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s6, c);

		s6name = new JTextField();
		s6name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 6;
		anmeldeLayout.setConstraints(s6name, c);

		// Start-Button
		neuesSpiel = new JButton("neues Spiel starten");
		neuesSpiel.setActionCommand("neuesSpiel");
		c.gridx = 1;
		c.gridy = 7;
		anmeldeLayout.setConstraints(neuesSpiel, c);
		neuesSpiel.setEnabled(false);
		anmeldePanel.add(neuesSpiel);

		// Lade-Button
		ladeSpiel = new JButton("Spiel laden");
		ladeSpiel.setActionCommand("ladeSpiel");
		c.gridx = 0;
		c.gridy = 7;
		anmeldeLayout.setConstraints(ladeSpiel, c);
		ladeSpiel.setEnabled(false);
		anmeldePanel.add(ladeSpiel);

		anmeldeFenster.add(welcomePanel, BorderLayout.NORTH);
		anmeldeFenster.add(anmeldePanel, BorderLayout.CENTER);

		anmeldeFenster.setSize(500, 700);
		anmeldeFenster.setVisible(true);

	}
	
	
	//----------------------ACTION LISTENER: AnmeldeFenster--------------------------------
	
	private void ereignisErzeugt() {
		
		spielerAnmeldenButton.addActionListener(new ActionListener() {
			int zaehler = 2;

			@Override
			public void actionPerformed(ActionEvent e) {
				String name;
				switch (zaehler) {
				case 2:
					try {
						name = s1name.getText();
						if(!name.equals("")) {
							spielerAnmelden(name);
							s1name.setEnabled(false);
							anmeldePanel.add(s2);
							anmeldePanel.add(s2name);
		
							// button
							c.gridx = 3;
							c.gridy = zaehler;
							anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
		
							anmeldeFenster.add(anmeldePanel);
							anmeldeFenster.setVisible(true);
							zaehler++;
						}
					} catch (SpielerBereitsVorhandenException exception) {
						s1name.setText("");
					}
					break;
				case 3:
					try {
						name = s2name.getText();
						if(!name.equals("")) {
							spielerAnmelden(name);
							anmeldePanel.add(s3);
							anmeldePanel.add(s3name);
		
							// button
							c.gridx = 3;
							c.gridy = zaehler;
							anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
		
							anmeldeFenster.add(anmeldePanel);
							anmeldeFenster.setVisible(true);
							neuesSpiel.setEnabled(true);
							ladeSpiel.setEnabled(true);
							s2name.setEnabled(false);
							zaehler++;
						}
					} catch (SpielerBereitsVorhandenException exception) {
						s2name.setText("");
					}
					break;
				case 4:
					try {
						name = s3name.getText();
						if(!name.equals("")) {
							spielerAnmelden(name);
							anmeldePanel.add(s4);
							anmeldePanel.add(s4name);
							
		
							// button
							c.gridx = 3;
							c.gridy = zaehler;
							anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
		
							anmeldeFenster.add(anmeldePanel);
							anmeldeFenster.setVisible(true);
							s3name.setEnabled(false);
							zaehler++; 
						}
					} catch (SpielerBereitsVorhandenException exception) {
						s3name.setText("");
					}
					break;
				case 5:
					try {
						name = s4name.getText();
						if(!name.equals("")) {
							spielerAnmelden(name);
							anmeldePanel.add(s5);
							anmeldePanel.add(s5name);
							
		
							// button
							c.gridx = 3;
							c.gridy = zaehler;
							anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
		
							anmeldeFenster.add(anmeldePanel);
							anmeldeFenster.setVisible(true);
							s4name.setEnabled(false);
							zaehler++;
						}
					} catch (SpielerBereitsVorhandenException exception) {
						s4name.setText("");
					}
					break;
				case 6:
					try {
						name = s5name.getText();
						if(!name.equals("")) {
							spielerAnmelden(name);
							anmeldePanel.add(s6);
							anmeldePanel.add(s6name);
							
							//Button
							c.gridx = 3;
							c.gridy = zaehler;
							anmeldeLayout.setConstraints(spielerAnmeldenButton, c);
							
							anmeldePanel.add(neuesSpiel);
							anmeldeFenster.add(anmeldePanel);
							anmeldeFenster.setVisible(true);
							s5name.setEnabled(false);
							zaehler++;
						}
					} catch (SpielerBereitsVorhandenException exception) {
						s5name.setText("");
					}
					break;
				case 7:
					name = s6name.getText();
					if(!name.equals("")) {
						try {
							spielerAnmelden(name);
						} catch (SpielerBereitsVorhandenException e1) {
							s6name.setText("");
						}
						s6name.setEnabled(false);
						spielerAnmeldenButton.setVisible(false);
				}
				
				//aber besser eine bessen Weg für anmelden und überprüfen finden
				//denn kein Button für den letzten und so wären bei Verschreiben Namen nicht änderbar, direkt angemeldet
			}
		}
		});

		neuesSpiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("SPIEL!!!!");
				nameS1 = s1name.getText();
				nameS2 = s2name.getText();
				if (s3.isVisible()) {
					nameS3 = s3name.getText();
					System.out.println(nameS3);
				}
				if (s4.isVisible()) {
					nameS4 = s4name.getText();
					System.out.println(nameS4);
				}
				if (s5.isVisible()) {
					nameS5 = s5name.getText();
					System.out.println(s5name.getText());
				}
				if (s6.isVisible()) {
					nameS6 = s6name.getText();
					System.out.println(nameS6);
				}
				anmeldeFenster.setVisible(false);
				anmeldeFenster.dispose();
			}
			
		});

		neuesSpiel.addActionListener(starteSpielActionListener);

		
		ladeSpiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				anmeldePanel.setVisible(false);
				setUpLadePanel();		
				setUpLadeEvents();
			}
		});
	}
	
	
	
	//---------------------LADE PANEL-----------------------------
	
	private void setUpLadePanel() {
		ladeLayout = new GridBagLayout();
		anmeldePanel.setLayout(ladeLayout);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		
		JLabel leereZeile = new JLabel(" ");
		c.gridy = 0;
		ladeLayout.setConstraints(leereZeile, c);
		ladePanel.add(leereZeile);
		
		JLabel anweisung = new JLabel("Bitte w�hle eines der folgenden Spiele aus:");
		c.gridy = 1;
		ladeLayout.setConstraints(anweisung, c);
		ladePanel.add(anweisung);
		
		JLabel leereZeile2 = new JLabel(" ");
		c.gridy = 2;
		ladeLayout.setConstraints(leereZeile2, c);
		ladePanel.add(leereZeile2);
		
		
		//Ladebare Spiele
		spielNamen = risiko.spielnamenAusgeben();
		

		bestaetigungsButton = new JButton("Spiel starten");
		c.gridy = spielNamen.size() + 4;
		ladeLayout.setConstraints(bestaetigungsButton, c);
		bestaetigungsButton.setEnabled(false);
		ladePanel.add(bestaetigungsButton);
		
		for(int i = 0; i < spielNamen.size(); i++) {
			JButton spiel = new JButton(spielNamen.get(i));
			c.gridy = i+3;
			spiel.setActionCommand(i+"");
			ladeLayout.setConstraints(spiel, c);
			ladePanel.add(spiel);
			
			setUpSpielButtons(spiel);
		}
		
		ladePanel.setVisible(true);
		anmeldeFenster.add(ladePanel, BorderLayout.CENTER);
	}

	private void setUpLadeEvents() {
		bestaetigungsButton.addActionListener(starteSpielActionListener);
		bestaetigungsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(name != null) {
					ladeListener.ladeSpiel(spielID, name);
					anmeldeFenster.setVisible(false);
					anmeldeFenster.dispose();
			}
				
			}
		});
	}

	private void setUpSpielButtons(JButton spiel) {
		spiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				spielID = Integer.parseInt(e.getActionCommand());
				
				try {
					name = pruefeSpiel(spielID);
					
					//TODO: Border wieder entfernen
				    spiel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
				    bestaetigungsButton.setEnabled(true);
				} catch (SpielNichtVorhandenException | SpielerNichtTeilDesSpielsException e1) {
					System.out.println("exception");
					JOptionPane.showMessageDialog(null, "Dieses Spiel wurde nicht von euch begonnen");
				} 
			}

			private String pruefeSpiel(int spielID) throws SpielNichtVorhandenException, SpielerNichtTeilDesSpielsException {
				String name = null;
				name = risiko.validiereSpiel(spielID, spielNamen);
				return name;
			}
		});
	}

	private void spielerAnmelden(String name) throws SpielerBereitsVorhandenException  {
		if (!risiko.spielerNameVorhanden(name)) {
			risiko.spielerHinzufuegen(name);
			System.out.println("Spieler "+name+" wurde angemeldet");
		}
	}

}
