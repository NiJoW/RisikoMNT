package risiko.local.ui.gui.swing;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Button;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Frame;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Label;
//import java.awt.Panel;
//import java.awt.TextField;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;
import risiko.local.ui.cui.RisikoClientCUI;

public class RisikoClientGUI extends JFrame implements ActionListener  { 
	private Risiko risiko;
	private BufferedReader in;
	private JButton addSpieler6;
	
	public RisikoClientGUI(Risiko risiko) {
//		risiko = new Risiko();
//		in = new BufferedReader(new InputStreamReader(System.in));
		this.risiko = risiko;
		try {
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initialize();
		
	}
	private void run() throws IOException {
		//spielerRegistrierung();
		//spielMenue();
		//risiko.spielVorbereiten();
		List<String> spielNamen = risiko.spielnamenAusgeben();
		
		
		try {
			int letzterAktiverSpielerID = risiko.spielLaden(0, spielNamen);
		} catch (SpielNichtVorhandenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpielerNichtTeilDesSpielsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//spielen(++letzterAktiverSpielerID);
	}
	
	
	

	private void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20;
		int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-20;
		
		this.setSize((screenWidth),(screenHeight));
		this.setMaximizedBounds(new Rectangle(0,0 , 500, 500));
		
		
		
		String spieler = risiko.getSpielerName(0); 
		JPanel panelCenter = new JPanel();
		JLabel aktSpieler = new JLabel("Spieler "+spieler+" ist an der Reihe");
		
		//panelCenter.setLayout(null);
		aktSpieler.setHorizontalTextPosition(JLabel.RIGHT);
		aktSpieler.setVerticalTextPosition(JLabel.TOP);
		JLabel myImage = new JLabel(new ImageIcon("images/world_map_free_vector.png"));
		myImage.setBounds(0, 0, 50, 50);
		panelCenter.add(myImage);
//		JLabel background;
//		setSize(panelCenter.getWidth(),panelCenter.getHeight());
//		setLayout(null);
//		ImageIcon img = new ImageIcon("images/world_map_free_vector.png");
//		
//		background = new JLabel("", img, JLabel.CENTER);
//		background.setBounds(0,0,panelCenter.getWidth(),panelCenter.getHeight());
//		add(background);
		panelCenter.add(aktSpieler);
		panelCenter.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(panelCenter, BorderLayout.CENTER);

		
		JPanel panelLineEnd = new JPanel();
		JLabel anweisungsText = new JLabel("Phase: Einheiten Verteilen");
		panelLineEnd.add(anweisungsText);
		panelLineEnd.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension lineEndSize = new Dimension(screenWidth/5, screenHeight);
		panelLineEnd.setMinimumSize(lineEndSize);
		panelLineEnd.setPreferredSize(lineEndSize);
		this.add(panelLineEnd, BorderLayout.LINE_END);
		
		
		JPanel panelPageEnd = new JPanel();
		JLabel phasenText = new JLabel("Du darfst deine Einheiten verteilen.");
		panelPageEnd.add(phasenText);
		panelPageEnd.setBorder(BorderFactory.createLineBorder(Color.black));
		Dimension pageEndSize = new Dimension(screenWidth, screenHeight/5);
		panelPageEnd.setMinimumSize(pageEndSize);
		panelPageEnd.setPreferredSize(pageEndSize);
		this.add(panelPageEnd, BorderLayout.PAGE_END);

		
		this.setVisible(true);
		//this.setEnabled(false);
		buildMyDialog();
	}
	private void buildMyDialog() {
		//Dialog Fenster
		JDialog anmeldeFenster = new JDialog();
		anmeldeFenster.setTitle("Bitte Anmelden");
		// Panel
	//	JPanel anmeldePanel = new JPanel();
	//	anmeldePanel.setBackground(Color.cyan);
//		anmeldeFenster.add(anmeldePanel);
		// Input
		anmeldeFenster.setLayout(new BorderLayout());
		JPanel welcomePanel = new JPanel();
		JPanel anmeldePanel = new JPanel();
		
		
//		GridBagLayout mainGBL = new GridBagLayout();
//		this.setLayout(mainGBL);
		
		JLabel welcomeText = new JLabel("Willkommen bei unserem Risiko!!!!");
		welcomePanel.setBackground(Color.blue);
		welcomePanel.setPreferredSize(new Dimension(400, 200));
		welcomePanel.add(welcomeText);
		
//		this.add(welcomePanel);
		
		GridBagLayout anmeldeLayout = new GridBagLayout();
		anmeldePanel.setLayout(anmeldeLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 	
		c.weighty = 0.1;
		
		JLabel bitteAnmelden = new JLabel("Bitte Spieler anmelden:");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(bitteAnmelden, c);
		anmeldePanel.add(bitteAnmelden);
		
		//Spieler 1
		
		JLabel s1 = new JLabel("Spieler 1:");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s1, c);
		anmeldePanel.add(s1);
		
		JTextField s1name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 1;
		anmeldeLayout.setConstraints(s1name, c);
		anmeldePanel.add(s1name);
		
		//Spieler 2
		
		JLabel s2 = new JLabel("Spieler 2:");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s2, c);
		anmeldePanel.add(s2);
		
		JTextField s2name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 2;
		anmeldeLayout.setConstraints(s2name, c);
		anmeldePanel.add(s2name);
		
		JButton addSpieler3 = new JButton("+");
		c.gridx = 3;
		c.gridy = 2;
		anmeldeLayout.setConstraints(addSpieler3, c);
		anmeldePanel.add(addSpieler3);
		
		//Spieler 3
		
		JLabel s3 = new JLabel("Spieler 3:");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s3, c);
		anmeldePanel.add(s3);
		
		JTextField s3name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 3;
		anmeldeLayout.setConstraints(s3name, c);
		anmeldePanel.add(s3name);
		
		JButton addSpieler4 = new JButton("+");
		c.gridx = 3;
		c.gridy = 3;
		anmeldeLayout.setConstraints(addSpieler4, c);
		anmeldePanel.add(addSpieler4);
		
		//Spieler 4
		
		JLabel s4 = new JLabel("Spieler 4:");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s4, c);
		anmeldePanel.add(s4);
		
		JTextField s4name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 4;
		anmeldeLayout.setConstraints(s4name, c);
		anmeldePanel.add(s4name);
		
		JButton addSpieler5 = new JButton("+");
		c.gridx = 3;
		c.gridy = 4;
		anmeldeLayout.setConstraints(addSpieler5, c);
		anmeldePanel.add(addSpieler5);
		
		//Spieler 5
		
		JLabel s5 = new JLabel("Spieler 5:");
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s5, c);
		anmeldePanel.add(s5);
		
		JTextField s5name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 5;
		anmeldeLayout.setConstraints(s5name, c);
		anmeldePanel.add(s5name);
		
		JButton addSpieler6 = new JButton("+");
		c.gridx = 3;
		c.gridy = 5;
		anmeldeLayout.setConstraints(addSpieler6, c);
		anmeldePanel.add(addSpieler6);
		addSpieler6.addActionListener(this);
		
		//Spieler 6
		
		JLabel s6 = new JLabel("Spieler 6:");
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s6, c);
		anmeldePanel.add(s6);
		
		JTextField s6name = new JTextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 6;
		anmeldeLayout.setConstraints(s6name, c);
		anmeldePanel.add(s6name);
		
		
		
		
		JButton start = new JButton("start");
		c.gridx = 1;
		c.gridy = 7;
		anmeldeLayout.setConstraints(start, c);
		anmeldePanel.add(start);
		
		
		
//		//anmeldeLayout.setConstraints(comp, constraints);
//		anmeldePanel.setLayout(anmeldeLayout);
//		anmeldePanel.setBackground(Color.red);
//		anmeldePanel.add(bitteAnmelden);
		
		
		
		
		
		
		anmeldeFenster.add(welcomePanel, BorderLayout.NORTH);
		anmeldeFenster.add(anmeldePanel, BorderLayout.CENTER);
		
		anmeldeFenster.setSize(400, 600);		
		anmeldeFenster.setVisible(true);
	}
	
	private void initializeAnmeldung() {
		
	
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource().equals(addSpieler6)) {
			 System.out.println("Klick!");
		 }
		
	}

	

}
