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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;
import risiko.local.ui.cui.RisikoClientCUI;
import risiko.local.ui.gui.swing.game.InformationsPanel;
import risiko.local.ui.gui.swing.game.KartenPanel;
import risiko.local.ui.gui.swing.game.KartenPanelV1;
import risiko.local.ui.gui.swing.game.PhasenPanel;

public class RisikoGameGUI extends JFrame  { 
	private Risiko risiko;
	private BufferedReader in;
	
	KartenPanel kartenPanel;
	PhasenPanel phasenPanel;
	InformationsPanel informationsPanel;
	int aktuellerSpieler;
	int phase;
	
	public RisikoGameGUI(Risiko risiko, int aktuellerSpieler, int phase, boolean neuesSpiel) {
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		this.phase = phase;
		initialize(neuesSpiel);
		try {
			run(aktuellerSpieler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	private void initialize(boolean neuesSpiel) {
		JLayeredPane lp = getLayeredPane();
		//JLayeredPane lp =  new JLayeredPane();
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30;
		int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30;
		
		this.setMaximizedBounds(new Rectangle(0,0 , 500, 500));
		this.setLayout(new BorderLayout());
		this.setSize((screenWidth),(screenHeight));
		

		informationsPanel = new InformationsPanel(risiko, aktuellerSpieler);
		kartenPanel = new KartenPanel(risiko, informationsPanel, aktuellerSpieler, screenWidth, screenHeight, lp);
		phasenPanel = new PhasenPanel(risiko, new PhaseBeendenListener(), informationsPanel, kartenPanel, neuesSpiel);
		kartenPanel.addMapAndPhasenPanel(phasenPanel);

		
		this.add(phasenPanel, BorderLayout.LINE_END);
		this.add(informationsPanel, BorderLayout.PAGE_END);
		this.add(kartenPanel, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.pack();
		System.out.println(kartenPanel.getSize());
		informationsPanel.setUpUI();
//		kartenPanel.addMap(risiko.getSpielerName(aktuellerSpieler));
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
		
	}
	
	private void run(int letzterAktiverSpielerID) throws IOException {
		
		//spielerRegistrierung();
		//spielMenue();
		//risiko.spielVorbereiten();
		for(int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println(risiko.getSpielerName(i) + ": " + risiko.getProvinzenVonSpieler(i));
		}
		
		spielen(++letzterAktiverSpielerID);
	}
	
	private void spielen(int spielerID) {
		String gewinner = "";
		//Runden (jeder Spieler durchlaeuft jede Phase ein mal)
		
		//Phase: EInheitenverteilen
		System.out.println("Phase: " + phase);
		phasenPanel.setPhase(phase);
		informationsPanel.setPhase(phase);

		phasenPanel.setAktuellerSpieler(spielerID);
		informationsPanel.setAktuellerSpieler(spielerID);
	
		
				//menueNeueEinheitenPhase(o);
				//gewinner = angreifen(o);
				//verschiebeMenu(o);
				//einheitenkarteVerteilen(o);
				//risiko.resetSpielerAttribute(o);
				
	}
	
	
	public class PhaseBeendenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nextPhase = Integer.parseInt(e.getActionCommand());
			//Phase: angreifen
			if(nextPhase == 1) {
				aktuellerSpieler++;
				if(aktuellerSpieler == risiko.getSpielerAnzahl()) {
					aktuellerSpieler = 0;
				}
				phasenPanel.setAktuellerSpieler(aktuellerSpieler);
				informationsPanel.setAktuellerSpieler(aktuellerSpieler);
				kartenPanel.setAktuellerSpieler(aktuellerSpieler);
				
			}
			informationsPanel.setPhase(nextPhase);
			phasenPanel.setPhase(nextPhase);
			System.out.println("Phase beendet, Phase "+nextPhase+" beginnen");
		}
	}
	

}
