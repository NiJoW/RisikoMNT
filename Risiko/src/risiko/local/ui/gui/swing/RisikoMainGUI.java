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
import risiko.local.ui.gui.swing.AnmeldefensterGUI.AnmeldeListener;
import risiko.local.ui.gui.swing.AnmeldefensterGUI.LadeListener;

//import javax.swing.*;

public class RisikoMainGUI implements AnmeldeListener, LadeListener{
	private Risiko risiko;
	private BufferedReader in;
	RisikoGameGUI spielFenster;
	AnmeldefensterGUI anmeldeFenster;

	String nameS1;
	String nameS2;
	int spielID;

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

	@Override
	public void habenSpieler(String nameS1, String nameS2, String nameS3, String nameS4, String nameS5, String nameS6) {
		this.nameS1 = nameS1;
		this.nameS2 = nameS2;

	}

	private void run() {
		anmeldeFenster = new AnmeldefensterGUI(risiko, new StarteSpielActionListener(), this, this);
	}

	/**
	 * Mitgliedsklasse fuer spielStarten - Button
	 * 
	 * @param args
	 */
	class StarteSpielActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			starteSpiel(e.getActionCommand());
		}

	}

	public void starteSpiel(String typ) {
		System.out.println("Spiel starten");
		List<String> spielNamen = risiko.spielnamenAusgeben();
		int letzterAktiverSpielerID = -1;
		int aktuellerSpieler = 0;

		if (typ.equals("neuesSpiel")) {
			risiko.spielVorbereiten();
			aktuellerSpieler = 0;
		}else {
			try {
				letzterAktiverSpielerID = risiko.spielLaden(spielID, spielNamen);
				aktuellerSpieler = ++letzterAktiverSpielerID;
				if(aktuellerSpieler == risiko.getSpielerAnzahl()) {
					aktuellerSpieler = 0;
				}
			} catch (SpielNichtVorhandenException e) {
				e.printStackTrace();
			} catch (SpielerNichtTeilDesSpielsException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("spieler:" +aktuellerSpieler);
		spielVorbereitung(aktuellerSpieler);
		spielFenster = new RisikoGameGUI(risiko, aktuellerSpieler);
	}

	private void spielVorbereitung(int letzterAktiverSpielerID) {
		// fuer laden und neues Spiel
		// risiko.berechneNeueEinheiten(letzterAktiverSpielerID);
		System.out.println(risiko.getVerteilbareEinheiten(letzterAktiverSpielerID));

	}

	@Override
	public void ladeSpiel(int spielID) {
		this.spielID = spielID;
	}

}
