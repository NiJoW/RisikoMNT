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
import risiko.local.ui.gui.swing.AnmeldefensterGUI.LadeListener;
import risiko.local.valueobjects.Spieler;

//import javax.swing.*;

public class RisikoMainGUI implements LadeListener{
	private Risiko risiko;
	private BufferedReader in;
	RisikoGameGUI spielFenster;
	AnmeldefensterGUI anmeldeFenster;

	String nameS1;
	String nameS2;
	int spielID;
	String name;

	public RisikoMainGUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {
		RisikoMainGUI main;
		// try {
		main = new RisikoMainGUI();
		main.run();
		// main.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void run() {
		anmeldeFenster = new AnmeldefensterGUI(risiko, new StarteSpielActionListener(), this);
	}

	/**
	 * Mitgliedsklasse fuer spielStarten - Button
	 * 
	 * @param args
	 */
	class StarteSpielActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("hoffentlich als zweites");
			starteSpiel(e.getActionCommand());
		}

	}

	public void starteSpiel(String typ) {
		System.out.println("Spiel starten");
		List<String> spielNamen = risiko.spielnamenAusgeben();
		
		int letzterAktiverSpielerID = -1;
		int aktuellerSpieler = 0;
		int phase = 0;
		boolean neuesSpiel = true;

		if (typ.equals("neuesSpiel")) {
			risiko.spielVorbereiten();
			for(int i = 0; i<risiko.getSpielerAnzahl(); i++) {
				System.out.println(i + ") " + risiko.getSpielerName(i));
			}
			aktuellerSpieler = 0;
		}else {
			neuesSpiel = false;
			for(int i = 0; i<risiko.getSpielerAnzahl(); i++) {
				System.out.println(i + ") " + risiko.getSpielerName(i));
			}
			letzterAktiverSpielerID = risiko.spielLaden(name);
			aktuellerSpieler = ++letzterAktiverSpielerID;
			if(aktuellerSpieler == risiko.getSpielerAnzahl()) {
				aktuellerSpieler = 0;
			}
			phase = 1;
		}
		
		spielFenster = new RisikoGameGUI(risiko, aktuellerSpieler, phase, neuesSpiel);
	}

	

	@Override
	public void ladeSpiel(int spielID, String name) {
		System.out.println("Spiel laden " + name + spielID);
		this.spielID = spielID;
		this.name = name;
	}

}
