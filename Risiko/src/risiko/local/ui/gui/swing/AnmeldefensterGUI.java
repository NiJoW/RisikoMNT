package risiko.local.ui.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.ui.gui.swing.RisikoMainGUI.StarteSpielActionListener;

public class AnmeldefensterGUI extends JPanel {

	public interface AnmeldeListener {
		public void habenSpieler(String nameS1, String nameS2, String nameS3, String nameS4, String nameS5,
				String nameS6);
	}
	
	public interface LadeListener {
		public void ladeSpiel(int spielID);
	}

	Risiko risiko;
	AnmeldeListener anmeldeListener;
	LadeListener ladeListener;

	StarteSpielActionListener starteSpielActionListener;
	JDialog anmeldeFenster;
	JPanel anmeldePanel;
	JPanel ladePanel;
	GridBagLayout anmeldeLayout;
	GridBagConstraints c;

	JButton addSpieler;
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
	

	public AnmeldefensterGUI(Risiko risiko, StarteSpielActionListener starteSpielActionListener,
			AnmeldeListener anmeldeListener, LadeListener ladeListener) {
		this.risiko = risiko;
		this.starteSpielActionListener = starteSpielActionListener;
		this.anmeldeListener = anmeldeListener;
		this.ladeListener = ladeListener;
		// abstrakte Klasse Fenster?
		initialize();
		ereignisErzeugt();
	}

	private void initialize() {
		anmeldeFenster = new JDialog();
		anmeldeFenster.setTitle("Bitte Anmelden");
		// Panel
		// JPanel anmeldePanel = new JPanel();
		// anmeldePanel.setBackground(Color.cyan);
//			anmeldeFenster.add(anmeldePanel);
		// Input
		anmeldeFenster.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
		ladePanel = new JPanel();
		anmeldePanel = new JPanel();

//			GridBagLayout mainGBL = new GridBagLayout();
//			this.setLayout(mainGBL);

		JLabel welcomeText = new JLabel("Willkommen bei unserem Risiko!!!!");
		welcomePanel.setBackground(Color.blue);
		welcomePanel.setPreferredSize(new Dimension(400, 200));
		welcomePanel.add(welcomeText);

//			this.add(welcomePanel);

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

		addSpieler = new JButton("+");
		c.gridx = 3;
		c.gridy = 1;
		anmeldeLayout.setConstraints(addSpieler, c);
		anmeldePanel.add(addSpieler);

		// Spieler 2

		s2 = new JLabel("Spieler 2:");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s2, c);
		//anmeldePanel.add(s2);

		s2name = new JTextField();
		s2name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 2;
		anmeldeLayout.setConstraints(s2name, c);
		//anmeldePanel.add(s2name);

		// Spieler 3

		s3 = new JLabel("Spieler 3:");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s3, c);
		// anmeldePanel.add(s3);

		s3name = new JTextField();
		s3name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 3;
		anmeldeLayout.setConstraints(s3name, c);
		// anmeldePanel.add(s3name);

		// Spieler 4

		s4 = new JLabel("Spieler 4:");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s4, c);
		// anmeldePanel.add(s4);

		s4name = new JTextField();
		s4name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 4;
		anmeldeLayout.setConstraints(s4name, c);
		// anmeldePanel.add(s4name);

		// Spieler 5

		s5 = new JLabel("Spieler 5:");
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s5, c);
		// anmeldePanel.add(s5);

		s5name = new JTextField();
		s5name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 5;
		anmeldeLayout.setConstraints(s5name, c);
		// anmeldePanel.add(s5name);

		// Spieler 6

		s6 = new JLabel("Spieler 6:");
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s6, c);
		// anmeldePanel.add(s6);

		s6name = new JTextField();
		s6name.setToolTipText("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 6;
		anmeldeLayout.setConstraints(s6name, c);
		// anmeldePanel.add(s6name);

		// TODO zuk�nftig Button "anmelden" und danach Spiel-Laden GUI + �berpr�fung,
		// boolean oder spiel-id zur�ck geben an main
		// Start-Button
		neuesSpiel = new JButton("neues Spiel starten");
		neuesSpiel.setActionCommand("neuesSpiel");
		c.gridx = 1;
		c.gridy = 7;
		anmeldeLayout.setConstraints(neuesSpiel, c);
		anmeldePanel.add(neuesSpiel);

		// Lade-Button
		ladeSpiel = new JButton("Spiel laden");
		ladeSpiel.setActionCommand("ladeSpiel");
		c.gridx = 0;
		c.gridy = 7;
		anmeldeLayout.setConstraints(ladeSpiel, c);
		anmeldePanel.add(ladeSpiel);

//			//anmeldeLayout.setConstraints(comp, constraints);
//			anmeldePanel.setLayout(anmeldeLayout);
//			anmeldePanel.setBackground(Color.red);
//			anmeldePanel.add(bitteAnmelden);

		anmeldeFenster.add(welcomePanel, BorderLayout.NORTH);
		anmeldeFenster.add(anmeldePanel, BorderLayout.CENTER);

		anmeldeFenster.setSize(500, 700);
		anmeldeFenster.setVisible(true);

	}

	public void ereignisErzeugt() {
		s1name.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			};

			@Override
			public void mousePressed(MouseEvent e) {
			};

			@Override
			public void mouseReleased(MouseEvent e) {
			};

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Spielername eingeben");
			}

			@Override
			public void mouseExited(MouseEvent e) {
			};

		});

		addSpieler.addActionListener(new ActionListener() {
			int zaehler = 2;

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (zaehler) {
				case 2:
					System.out.println(zaehler);
					anmeldePanel.add(s2);
					anmeldePanel.add(s2name);

					// button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);

					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					spielerAnmelden(s1name.getText());
					zaehler++;
					break;
				case 3:
					System.out.println(zaehler);
					anmeldePanel.add(s3);
					anmeldePanel.add(s3name);

					// button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);

					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					spielerAnmelden(s2name.getText());
					zaehler++;
					break;
				case 4:
					System.out.println(zaehler);
					anmeldePanel.add(s4);
					anmeldePanel.add(s4name);

					// button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);

					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					spielerAnmelden(s3name.getText());
					zaehler++;
					break;
				case 5:
					System.out.println(zaehler);
					anmeldePanel.add(s5);
					anmeldePanel.add(s5name);

					// button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);

					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					spielerAnmelden(s4name.getText());
					zaehler++;
					break;
				case 6:
					System.out.println(zaehler);
					addSpieler.setVisible(false);
					anmeldePanel.add(s6);
					anmeldePanel.add(s6name);
					neuesSpiel.setEnabled(true);

					anmeldePanel.add(neuesSpiel);
					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					spielerAnmelden(s5name.getText());
					break;
				}
				
				//TODO: müsste spieler 6 auch spielerAnmelden(s6name.getText());
				//aber besser eine bessen Weg für anmelden und überprüfen finden
				//denn kein Button für den letzten und so wären bei Verschreiben Namen nicht änderbar, direkt angemeldet
			}

		});

		neuesSpiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameS1 = s1name.getText();
				nameS2 = s2name.getText();
				System.out.println(nameS1 + ", " + nameS2);
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
				anmeldeListener.habenSpieler(nameS1, nameS2, nameS3, nameS4, nameS5, nameS6);
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
			}

			
			
		});
		

	}
	
	private void setUpLadePanel() {
		GridBagLayout ladeLayout = new GridBagLayout();
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
		List<String> spielNamen = risiko.spielnamenAusgeben();
		
		for(int i = 0; i < spielNamen.size(); i++) {
			JButton spiel = new JButton(spielNamen.get(i));
			c.gridy = i+3;
			spiel.setActionCommand(i+"");
			ladeLayout.setConstraints(spiel, c);
			ladePanel.add(spiel);
			
			setUpLadeEvent(spiel);
		}
		ladePanel.setVisible(true);
		anmeldeFenster.add(ladePanel, BorderLayout.CENTER);
	}

	private void setUpLadeEvent(JButton spiel) {
		spiel.addActionListener(starteSpielActionListener);
		spiel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int spielID = Integer.parseInt(e.getActionCommand());
				ladeListener.ladeSpiel(spielID);
				anmeldeFenster.setVisible(false);
				anmeldeFenster.dispose();
			}
		});
	}

	private void spielerAnmelden(String name) {
		try {
			if (!risiko.spielerNameVorhanden(name)) {
				risiko.spielerHinzufuegen(name);
				// angemeldet = true;
				System.out.println("Spieler "+name+" wurde angemeldet");
			}
		} catch (SpielerBereitsVorhandenException e2) {
			System.out.println(e2.getMessage() + "\nBitte einen anderen Namen waehlen!\n");

		}
	}

}
