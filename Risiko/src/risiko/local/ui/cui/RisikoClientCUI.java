package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import risiko.local.domain.Risiko;
import risiko.local.valueobjects.Provinz;

public class RisikoClientCUI {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientCUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));		
	}
	
	private void run() {//gegebenenfalls lï¿½schen
		spielerRegistrierung();
		spielMenue();
	}
	
	
	private void spielerRegistrierung() {
		int anzahl = erfrageSpielerAnzahl();
		for(int i = 0; i < anzahl; i++) {
		    System.out.println("Geben Sie bitte den Namen fÃ¼r Spieler "+(i+1)+" ein:");
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
		risiko.spielVorbereiten(); // TODO: Einheiten verteilen
		einheitenVerteilen();
		gameMenuAusgeben();
		//risiko.spielStarten();
	}
	
	private void einheitenVerteilen() {
		weltkarteAusgeben();
		for(int j = 0; j < risiko.getSpielerAnzahl(); j++) {
			System.out.println(risiko.getSpielerName(j) + " ist an der Reihe und darf " + risiko.getVerteilbareEinheiten(j) + " Einheiten setzen!");
			einheitenwahlVerarbeiten(j, 42, 0);
		}
	}
	
	private void weltkarteAusgeben() {
		System.out.println("AKTUELLE WELTKARTE:");
		for(int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println("-------------Länder von Spieler " + risiko.getSpielerName(i) + "--------------");
			laenderInfoAusgeben(i);
			
		}
	}
	
	private void einheitenwahlVerarbeiten(int spielerID, int provinzID, int anzahlEinheiten) {
		try {
			System.out.print("Provinz ID: ");
			provinzID = Integer.parseInt(liesEingabe());
			System.out.print("> ");
			
			System.out.print("Anzahl Einheiten: ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());
			System.out.print("> ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(validiereProvinzID(provinzID, spielerID) && validiereAnzahlEinheiten(anzahlEinheiten, spielerID)) {
			risiko.einheitenVerteilen(provinzID, anzahlEinheiten);
		}	else {
			einheitenwahlVerarbeiten(spielerID, 42, 0);
		}
	}
	
	private boolean validiereProvinzID(int provinzID, int spielerID) {
		if(provinzID > 41 || provinzID < 0) {
			System.out.println("Provinz ID muss eine Zahl zwischen 0 und 41 sein! Bitte erneut eingeben: ");
			return false;
		}else if(!(risiko.getProvinz(provinzID).getBesitzer().equals(risiko.getSpieler(spielerID)))){
			System.out.println("Diese Provinz gehört dir nicht! Bitte eine Provinz von dir angeben: ");
			return false;
		}
		return true;
	}
	
	
	
	private boolean validiereAnzahlEinheiten(int anzahl, int spielerID) {
		if((anzahl > 0) && risiko.getVerteilbareEinheiten(spielerID) >= anzahl) {
			return true;
		}
		System.out.println("Anzahl der Einheiten ist nicht korrekt, bitte neu eingeben!");
		return false;
	}


	private void laenderInfoAusgeben(int i) {
		for(Provinz provinz : risiko.getProvinzenVonSpieler(i)) {
			System.out.println(provinz);
		}
			
	}
	
	
	
	private void gameMenuAusgeben() {
		//Einheiten bekommen / berechnen
		//Einheiten setzen
		//angreiefen
		//verschieben
		System.out.println("AKTUELLE WELTKARTE:");
		for(int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println("-------------Länder von Spieler " + risiko.getSpielerName(i) + "--------------");
			laenderInfoAusgeben(i);
			
		}
		risiko.kannVerschieben();
	}
	
	
	public static void main(String[] args) {
		RisikoClientCUI cui;
		//try-catch exception
		cui = new RisikoClientCUI();
		cui.run();
	}
	
}
