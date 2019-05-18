package risiko.local.ui.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;

//import javax.swing.*;

public class RisikoMainGUI {
	private Risiko risiko;
	private BufferedReader in;
	RisikoGameGUI spielFenster;
	AnmeldefensterGUI anmeldeFenster;
	
	public RisikoMainGUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		RisikoMainGUI main;
		//try {
			main = new RisikoMainGUI();
			main.run();
		//	main.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	
	private void run() {
		anmeldeFenster = new AnmeldefensterGUI(new StarteSpielActionListener());
	}
	
	/**
	 * Mitgliedsklasse fuer spielStarten - Button
	 * @param args
	 */
	class StarteSpielActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			starteSpiel();
		}
		
	}
	
	public void starteSpiel() {
		System.out.println("Spiel starten");
		List<String> spielNamen = risiko.spielnamenAusgeben();
		int letzterAktiverSpielerID = -1;
		
		try {
			letzterAktiverSpielerID = risiko.spielLaden(0, spielNamen);
		} catch (SpielNichtVorhandenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpielerNichtTeilDesSpielsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spielFenster = new RisikoGameGUI(risiko, letzterAktiverSpielerID);
	}
	
}
