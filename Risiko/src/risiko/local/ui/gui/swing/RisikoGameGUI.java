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
import risiko.local.ui.gui.swing.game.AnweisungsPanel;
import risiko.local.ui.gui.swing.game.KartenPanel;
import risiko.local.ui.gui.swing.game.PhasenPanel;

public class RisikoGameGUI extends JFrame  { 
	private Risiko risiko;
	private BufferedReader in;
	//MOumita
	KartenPanel kartenPanel;
	PhasenPanel phasenPanel;
	AnweisungsPanel anweisungsPanel;
	int aktuellerSpieler;
	
	public RisikoGameGUI(Risiko risiko, int aktuellerSpieler) {
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		initialize();
		try {
			run(aktuellerSpieler);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	

	private void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30;
		int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30;
		
		this.setMaximizedBounds(new Rectangle(0,0 , 500, 500));
		this.setLayout(new BorderLayout());
		
		
		kartenPanel = new KartenPanel(risiko, aktuellerSpieler);
		phasenPanel = new PhasenPanel(risiko, screenWidth, screenHeight, new PhaseBeendenListener());
		anweisungsPanel = new AnweisungsPanel(risiko, screenWidth, screenHeight, aktuellerSpieler);
		
		this.add(kartenPanel, BorderLayout.CENTER);
		this.add(phasenPanel, BorderLayout.LINE_END);
		this.add(anweisungsPanel, BorderLayout.PAGE_END);

		this.setSize((screenWidth),(screenHeight));
		this.setVisible(true);
	}
	
	private void run(int letzterAktiverSpielerID) throws IOException {
		
		//spielerRegistrierung();
		//spielMenue();
		//risiko.spielVorbereiten();
		
		spielen(++letzterAktiverSpielerID);
	}
	
	// Teste Git Tabea
	private void spielen(int spielerID) {
		String gewinner = "";
		//Runden (jeder Spieler durchlaeuft jede Phase ein mal)
		
		//Phase: EInheitenverteilen
		phasenPanel.setPhase(0);
		anweisungsPanel.setPhase(0);
		
		anweisungsPanel.setAktuellerSpieler(spielerID);
	
		
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
			anweisungsPanel.setPhase(nextPhase);
			System.out.println("Phase beendet, Phase "+nextPhase+" beginnen");
		}
	}
	

}
