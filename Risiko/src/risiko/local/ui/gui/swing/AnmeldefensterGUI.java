package risiko.local.ui.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Anmeldefenster extends JPanel {
	
	JDialog anmeldeFenster;
	JPanel anmeldePanel;
	GridBagLayout anmeldeLayout;
	GridBagConstraints c;
	
	JButton addSpieler;
	JLabel s3;
	JTextField s3name;
	JLabel s4;
	JTextField s4name;
	JLabel s5;
	JTextField s5name;
	JLabel s6;
	JTextField s6name;
	JButton start;
	
	public Anmeldefenster() {
		//abstrakte Klasse Fenster?
		initialize();
		ereignisErzeugt();
	}
	
	private void initialize() {
			anmeldeFenster = new JDialog();
			anmeldeFenster.setTitle("Bitte Anmelden");
			// Panel
		//	JPanel anmeldePanel = new JPanel();
		//	anmeldePanel.setBackground(Color.cyan);
//			anmeldeFenster.add(anmeldePanel);
			// Input
			anmeldeFenster.setLayout(new BorderLayout());
			JPanel welcomePanel = new JPanel();
			anmeldePanel = new JPanel();
			
			
//			GridBagLayout mainGBL = new GridBagLayout();
//			this.setLayout(mainGBL);
			
			JLabel welcomeText = new JLabel("Willkommen bei unserem Risiko!!!!");
			welcomePanel.setBackground(Color.blue);
			welcomePanel.setPreferredSize(new Dimension(400, 300));
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
			
			//Spieler 1
			
			JLabel s1 = new JLabel("Spieler 1:");
			c.gridx = 0;
			c.gridy = 1;
			c.anchor = GridBagConstraints.WEST;
			anmeldeLayout.setConstraints(s1, c);
			anmeldePanel.add(s1);
			
			JTextField s1name = new JTextField("Spielername eingeben");
			//TODO: besser onHover Text angeben 
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
			
			
			//Button
			
			addSpieler = new JButton("+");
			c.gridx = 3;
			c.gridy = 2;
			anmeldeLayout.setConstraints(addSpieler, c);
			anmeldePanel.add(addSpieler);
			System.out.println("Button erstellt");
			
			//Spieler 3
			
			s3 = new JLabel("Spieler 3:");
			c.gridx = 0;
			c.gridy = 3;
			c.anchor = GridBagConstraints.WEST;
			anmeldeLayout.setConstraints(s3, c);
			//anmeldePanel.add(s3);
			
			s3name = new JTextField("Spielername eingeben");
			c.gridx = 1;
			c.gridy = 3;
			anmeldeLayout.setConstraints(s3name, c);
			//anmeldePanel.add(s3name);
	
			
			//Spieler 4
			
			s4 = new JLabel("Spieler 4:");
			c.gridx = 0;
			c.gridy = 4;
			c.anchor = GridBagConstraints.WEST;
			anmeldeLayout.setConstraints(s4, c);
			//anmeldePanel.add(s4);
			
			s4name = new JTextField("Spielername eingeben");
			c.gridx = 1;
			c.gridy = 4;
			anmeldeLayout.setConstraints(s4name, c);
			//anmeldePanel.add(s4name);
			
			
			//Spieler 5
			
			s5 = new JLabel("Spieler 5:");
			c.gridx = 0;
			c.gridy = 5;
			c.anchor = GridBagConstraints.WEST;
			anmeldeLayout.setConstraints(s5, c);
			//anmeldePanel.add(s5);
			
			s5name = new JTextField("Spielername eingeben");
			c.gridx = 1;
			c.gridy = 5;
			anmeldeLayout.setConstraints(s5name, c);
			//anmeldePanel.add(s5name);
			
			
			//Spieler 6
			
			s6 = new JLabel("Spieler 6:");
			c.gridx = 0;
			c.gridy = 6;
			c.anchor = GridBagConstraints.WEST;
			anmeldeLayout.setConstraints(s6, c);
			//anmeldePanel.add(s6);
			
			s6name = new JTextField("Spielername eingeben");
			c.gridx = 1;
			c.gridy = 6;
			anmeldeLayout.setConstraints(s6name, c);
			//anmeldePanel.add(s6name);
			
			
			
			
			start = new JButton("start");
			c.gridx = 1;
			c.gridy = 7;
			anmeldeLayout.setConstraints(start, c);
			start.setEnabled(false);
			anmeldePanel.add(start);
			
			
			
//			//anmeldeLayout.setConstraints(comp, constraints);
//			anmeldePanel.setLayout(anmeldeLayout);
//			anmeldePanel.setBackground(Color.red);
//			anmeldePanel.add(bitteAnmelden);
			
			
			
			anmeldeFenster.add(welcomePanel, BorderLayout.NORTH);
			anmeldeFenster.add(anmeldePanel, BorderLayout.CENTER);
			
			anmeldeFenster.setSize(400, 600);		
			anmeldeFenster.setVisible(true);
		
	}
	
	public void ereignisErzeugt() {
		addSpieler.addActionListener(new ActionListener() {
			int zaehler = 3;
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (zaehler) {
				case 3:
					System.out.println(zaehler);
					anmeldePanel.add(s3);
					anmeldePanel.add(s3name);
					
					//button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);
					
					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					zaehler++;
					break;
				case 4:
					System.out.println(zaehler);
					anmeldePanel.add(s4);
					anmeldePanel.add(s4name);
					
					//button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);
					
					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					zaehler++;
					break;
				case 5:
					System.out.println(zaehler);
					anmeldePanel.add(s5);
					anmeldePanel.add(s5name);
					
					//button
					c.gridx = 3;
					c.gridy = zaehler;
					anmeldeLayout.setConstraints(addSpieler, c);
					
					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					zaehler++;
					break;
				case 6:
					System.out.println(zaehler);
					addSpieler.setVisible(false);
					anmeldePanel.add(s6);
					anmeldePanel.add(s6name);
					start.setEnabled(true);
					
					anmeldePanel.add(start);
					anmeldeFenster.add(anmeldePanel);
					anmeldeFenster.setVisible(true);
					break;
				}
				
			}
			
		});
		
//		start.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Anmeldefenster.this.setVisible(false);
//				dispose();
//				System.exit(0);
//			}
//			
//		});
		
	}
	
}
