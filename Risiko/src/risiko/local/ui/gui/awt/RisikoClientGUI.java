package risiko.local.ui.gui.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import risiko.local.domain.Risiko;
import risiko.local.ui.cui.RisikoClientCUI;

public class RisikoClientGUI extends Frame implements ActionListener {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientGUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
		initialize();
	}
	
	public static void main(String[] args) {
		RisikoClientGUI gui;
		try {
			gui = new RisikoClientGUI();
			gui.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() throws IOException {
		//spielerRegistrierung();
		//spielMenue();
	}
	
	private void initialize() {
		
		this.addWindowListener(new WindowCloser());
		
		this.setLayout(new BorderLayout());
		Panel welcomePanel = new Panel();
		Panel anmeldePanel = new Panel();
		
		
//		GridBagLayout mainGBL = new GridBagLayout();
//		this.setLayout(mainGBL);
		
		Label welcomeText = new Label("Willkommen bei unserem Risiko!!!!");
		welcomePanel.setBackground(Color.blue);
		welcomePanel.setPreferredSize(new Dimension(400, 200));
		welcomePanel.add(welcomeText);
		
		
		
		
		
		
		
		
//		
		
//		
//		this.add(welcomePanel);
		
		GridBagLayout anmeldeLayout = new GridBagLayout();
		anmeldePanel.setLayout(anmeldeLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 	
		c.weighty = 0.1git;
		
		Label bitteAnmelden = new Label("Bitte Spieler anmelden:");
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(bitteAnmelden, c);
		anmeldePanel.add(bitteAnmelden);
		
		//Spieler 1
		
		Label s1 = new Label("Spieler 1:");
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s1, c);
		anmeldePanel.add(s1);
		
		TextField s1name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 1;
		anmeldeLayout.setConstraints(s1name, c);
		anmeldePanel.add(s1name);
		
		//Spieler 2
		
		Label s2 = new Label("Spieler 2:");
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s2, c);
		anmeldePanel.add(s2);
		
		TextField s2name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 2;
		anmeldeLayout.setConstraints(s2name, c);
		anmeldePanel.add(s2name);
		
		Button addSpieler3 = new Button("+");
		c.gridx = 3;
		c.gridy = 2;
		anmeldeLayout.setConstraints(addSpieler3, c);
		anmeldePanel.add(addSpieler3);
		
		//Spieler 3
		
		Label s3 = new Label("Spieler 2:");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s3, c);
		anmeldePanel.add(s3);
		
		TextField s3name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 3;
		anmeldeLayout.setConstraints(s3name, c);
		anmeldePanel.add(s3name);
		
		Button addSpieler4 = new Button("+");
		c.gridx = 3;
		c.gridy = 3;
		anmeldeLayout.setConstraints(addSpieler4, c);
		anmeldePanel.add(addSpieler4);
		
		//Spieler 4
		
		Label s4 = new Label("Spieler 2:");
		c.gridx = 0;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s4, c);
		anmeldePanel.add(s4);
		
		TextField s4name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 4;
		anmeldeLayout.setConstraints(s4name, c);
		anmeldePanel.add(s4name);
		
		Button addSpieler5 = new Button("+");
		c.gridx = 3;
		c.gridy = 4;
		anmeldeLayout.setConstraints(addSpieler5, c);
		anmeldePanel.add(addSpieler5);
		
		//Spieler 5
		
		Label s5 = new Label("Spieler 2:");
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s5, c);
		anmeldePanel.add(s5);
		
		TextField s5name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 5;
		anmeldeLayout.setConstraints(s5name, c);
		anmeldePanel.add(s5name);
		
		Button addSpieler6 = new Button("+");
		c.gridx = 3;
		c.gridy = 5;
		anmeldeLayout.setConstraints(addSpieler6, c);
		anmeldePanel.add(addSpieler6);
		
		//Spieler 6
		
		Label s6 = new Label("Spieler 2:");
		c.gridx = 0;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		anmeldeLayout.setConstraints(s6, c);
		anmeldePanel.add(s6);
		
		TextField s6name = new TextField("Spielername eingeben");
		c.gridx = 1;
		c.gridy = 6;
		anmeldeLayout.setConstraints(s6name, c);
		anmeldePanel.add(s6name);
		
		
		
		
		Button start = new Button("start");
		c.gridx = 1;
		c.gridy = 7;
		anmeldeLayout.setConstraints(start, c);
		anmeldePanel.add(start);
		
		
		
//		//anmeldeLayout.setConstraints(comp, constraints);
//		anmeldePanel.setLayout(anmeldeLayout);
//		anmeldePanel.setBackground(Color.red);
//		anmeldePanel.add(bitteAnmelden);
		
		
		
		
		
		
		this.add(welcomePanel, BorderLayout.NORTH);
		this.add(anmeldePanel, BorderLayout.CENTER);
		
		this.setSize(400, 600);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
