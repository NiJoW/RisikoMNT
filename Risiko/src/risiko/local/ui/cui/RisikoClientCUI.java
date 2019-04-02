package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import risiko.local.domain.Risiko;

public class RisikoClientCUI {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientCUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));		
	}
	
	private void run() {//gegebenenfalls l�schen
		spielerRegistrierung();
		spielMenue();
	}
	
	
	private void spielerRegistrierung() {
		int anzahl = erfrageSpielerAnzahl();
		for(int i = 0; i < anzahl; i++) {
		    System.out.println("Geben Sie bitte den Namen für Spieler "+(i+1)+" ein:");
		    try {
		    	String name = liesEingabe();
				risiko.spielerHinzufuegen(name);
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    } 
		}
		risiko.gibSpielerlisteAus();
		
	}
	
	private int erfrageSpielerAnzahl() {
		int anzahl = 0;
		
		System.out.println("Gib die Anzahl der Spieler an (zwischen 2 und 6):");
		//Eingabe einlesen
		try {
			anzahl = Integer.parseInt(liesEingabe());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(anzahl<2 || anzahl>6) {
			anzahl = erfrageSpielerAnzahl();
		}
		return anzahl;
		
	}
	
	private void spielMenue() {
		String input = "";
		do {
			spielMenueAusgeben();
			try {
				input = liesEingabe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verarbeiteSpielmenue(input);
		}while(!input.equals("q"));
	}
	
	private void spielMenueAusgeben() {
		System.out.println("Neues Spiel starten:        'n'");
		System.out.println("Spiel laden:        'l'");
		System.out.println("Spiel beitreten:           'b'");
		System.out.println("---------------------");
		System.out.println("Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}
	
	private String liesEingabe()  throws IOException{
		// einlesen von Konsole
		return in.readLine();
	}
	
	
	private void verarbeiteSpielmenue(String input) {
		switch(input) {
		case "n":
			spielStarten();
			break;
		}
	}
	
	private void spielStarten() {
		risiko.spielStarten();
	}
	
	public static void main(String[] args) {
		RisikoClientCUI cui;
		//try-catch exception
		cui = new RisikoClientCUI();
		cui.run();
	}
}
