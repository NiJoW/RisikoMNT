package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.AnzahlDerSpielerNichtKorrektException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.valueobjects.Provinz;

public class RisikoClientCUI {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientCUI() {
		
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		RisikoClientCUI cui;
		try {
			cui = new RisikoClientCUI();
			cui.run();
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
	
	private void run() throws IOException {
		spielerRegistrierung();
		spielMenue();
	}
	
	
	//-----------------------------SPIELERREGISTRIERUNG----------------------------------
	
	
	private void spielerRegistrierung() throws IOException {
		
		int anzahl = 0;
		boolean anzahlKorrekt = false;
		while (!anzahlKorrekt) {
			try {
				anzahl = erfrageSpielerAnzahl();
				anzahlKorrekt = true;
			} catch (AnzahlDerSpielerNichtKorrektException e1) {
				System.out.println(e1.getMessage() + "\nBitte eine andere Spieleranzahl w�hlen!\n");
			}
		}
		boolean angemeldet = false;
		for(int i = 0; i < anzahl; i++) {
			angemeldet = false;
			while (!angemeldet) {
				System.out.println("Geben Sie bitte den Namen für Spieler "+(i+1)+" ein:");
				String name = liesEingabe();
				try {
					if(!risiko.spielerNameVorhanden(name)) {
						risiko.spielerHinzufuegen(name); 
						angemeldet = true;
					}
				} catch (SpielerBereitsVorhandenException e2) {
					System.out.println(e2.getMessage() + "\nBitte einen anderen Namen w�hlen!\n");
				}
			}
		 }		
	}
	
	private int erfrageSpielerAnzahl() throws AnzahlDerSpielerNichtKorrektException {
		int anzahl = 0;
		
		System.out.println("Gib die Anzahl der Spieler an (zwischen 2 und 6):");
		//Eingabe einlesen
		try {
			anzahl = Integer.parseInt(liesEingabe());
			if(anzahl<2 || anzahl>6 ) {
				throw new AnzahlDerSpielerNichtKorrektException("Anzahl der Spieler muss zwischen 2 und 6 liegen");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return anzahl;
	}
	
	
	//-----------------------------SPIELMEN�----------------------------------
	
	
	
	private void spielMenue() {
		String input = "";
		do {
			spielMenueAusgeben();
			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}
			verarbeiteSpielmenue(input);
		}while(!input.equals("q"));
	}
	
	private void spielMenueAusgeben() {
		System.out.println("Neues Spiel starten:        'n'");
		//System.out.println("Spiel laden:        'l'"); //zukünftig
		//System.out.println("Spiel beitreten:           'b'"); //zukünftig
		System.out.println("---------------------");
		System.out.println("Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}
	
	
	private void verarbeiteSpielmenue(String input) {
		switch(input) {
		case "n":
			spielStarten();
			break;
		case "q": //Spiel beenden
			break;	
		default:
			System.out.println("\nEingabe fehlerhaft! \nBitte w�hle eine der folgenden Optionen:");
		//Zukünftig: weitere CASES
		}
	}
	
	private void spielStarten() {
		risiko.spielVorbereiten();
		einheitenVerteilen();
		spielen();
	}
	
	
	//---------------------------EINHEITEN VERTEILEN--------------------------------
	
	
	
	private void einheitenVerteilen() {
		weltkarteAusgeben();
		for(int j = 0; j < risiko.getSpielerAnzahl(); j++) {
			while(risiko.getVerteilbareEinheiten(j) > 0) {
				System.out.println(risiko.getSpielerName(j) + " ist an der Reihe und darf " + risiko.getVerteilbareEinheiten(j) + " Einheiten setzen!");
				einheitenwahlVerarbeiten(j, 42, 0);
			}
		}
	}
	
	private void laenderInfoAusgeben(int i) {
		for(Provinz provinz : risiko.getProvinzenVonSpieler(i)) {
			System.out.println(provinz);
		}
			
	}
	
	private void einheitenwahlVerarbeiten(int spielerID, int provinzID, int anzahlEinheiten) {
		try {
			System.out.print("Provinz ID: ");
			provinzID = Integer.parseInt(liesEingabe());
			
			System.out.print("Anzahl Einheiten: ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			risiko.setzeNeueEinheiten(provinzID, anzahlEinheiten, spielerID);
			risiko.berechneVerteilbareEinheiten(-anzahlEinheiten, spielerID);
		}catch(Exception e) {
			System.out.println(e.getMessage()); 
			einheitenwahlVerarbeiten(spielerID, 42, 0);
		}
	}
	
	
	//--------------------------------SPIELEN-----------------------------------------
	
	
	
	private void spielen() {
		//Runden (=jeder Spieler durchl�uft jede Phase ein mal)
		while(!einerHatGewonnen()) {
			//einzelnen Spielz�ge mit jeweiligen Phasen
			for(int o = 0; o < risiko.getSpielerAnzahl(); o++) {
				weltkarteAusgeben();
				//Einheiten bekommen / berechnen
				neueEinheiten(o);
				//Einheiten setzen
				angreifen(o);
				//verschieben
				einheitenVerschieben(o);
			}
		}	
	}
	
	//Pr�fen, ob jemand gewonnen hat
		private boolean einerHatGewonnen() {
			// TODO Auto-generated method stub
			return false;
		}
	
		
		
		//----------------------------------NEUE EINHEITEN---------------------------------
		
		
	private void neueEinheiten(int spielerID) {
		System.out.println("------------Spieler: " + risiko.getSpielerName(spielerID) + "--------------");
		System.out.println("------------Phase: Einheiten setzen--------------");
		
		int anzahlMoeglich = risiko.berechneNeueEinheiten(spielerID); //auch Kontinente pr�fen
		
		int toProvinz = 42;
		int anzahlEinheitenWollen = 0;
		

		risiko.berechneVerteilbareEinheiten(anzahlMoeglich, spielerID);
		
		while(anzahlMoeglich>0) {
			toProvinz = 42;
			anzahlEinheitenWollen = 0;
			System.out.println("\nSo viele einheiten: " + anzahlMoeglich);
			try {			
				System.out.print("Auf welche Provinz (ID) m�chtest du deine Einheit(en) setzen? : ");
				toProvinz = Integer.parseInt(liesEingabe());
			
				System.out.print("Wie viele Einheiten m�chtest du setzen? : ");
				anzahlEinheitenWollen = Integer.parseInt(liesEingabe());
				
				risiko.setzeNeueEinheiten(toProvinz, anzahlEinheitenWollen, spielerID);
				risiko.berechneVerteilbareEinheiten(-anzahlEinheitenWollen, spielerID);
				anzahlMoeglich -= anzahlEinheitenWollen;
			
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
		}
	}
	
	
	//-------------------------------ANGRIFF--------------------------------------
	
	
	private void angreifen(int spielerID) {
		System.out.println("------------Phase: Angreifen--------------");
		String input = "";
		
		
		while(true) {
			System.out.println("-------------L�nder von Spieler " + risiko.getSpielerName(spielerID) + "--------------");
			laenderInfoAusgeben(spielerID);
			
			System.out.println("Angreifen:        'a'");
			System.out.println("Phase beenden:        'q'");
			
			try {
				input = liesEingabe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(input) {
			case "q":
				return;
			case "a":
				angriffAusfuehren(spielerID);
				break;
			default:
				System.out.println("\nEingabe fehlerhaft! \nBitte w�hle eine der folgenden Optionen:");
			}
		
		
			
		}
	}
	
	
	
	private void angriffAusfuehren(int spielerID) {
		int fromProvinz = 42;
		int toProvinz = 42;
		int anzahlEinheiten = 0;
		
		try {
			System.out.print("Von Provinz (ID): ");
			fromProvinz = Integer.parseInt(liesEingabe());
			
			System.out.print("Nach Provinz (ID): ");
			toProvinz = Integer.parseInt(liesEingabe());
			
			System.out.print("Mit wie vielen Einheiten m�chtest du angreifen? (max 3) ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());
			
			risiko.validiereAngriffEingaben(fromProvinz, toProvinz, spielerID, anzahlEinheiten);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			angriffAusfuehren(spielerID);
			return;
		}
		System.out.println("");

		String verteidiger = risiko.getProvinz(toProvinz).getBesitzer().getName();
		
		int[] wuerfelErgebnisse;
		
		wuerfelErgebnisse = risiko.wuerfeln(anzahlEinheiten, toProvinz);
		
		String[][] wuerfelVergleich = risiko.angreifen(fromProvinz, toProvinz, anzahlEinheiten, wuerfelErgebnisse);
		//ToDo Try Catch + Exception statt if ergebnis == null
		if(wuerfelVergleich!=null) {
			for(int t = 0; t < anzahlEinheiten; t++) {
				System.out.println("Spieler " + risiko.getSpielerName(spielerID) + " hat eine " + wuerfelErgebnisse[t] + " gew�rfelt!");
			}
				
			for(int k = anzahlEinheiten; k < wuerfelErgebnisse.length; k++) {
				System.out.println("Der verteidigende Spieler " + verteidiger + " hat eine " + wuerfelErgebnisse[k] + " gew�rfelt!");
			}
			
			System.out.println("");
			
			//Ergebnisse der einzelnen Wurf-Vergleiche
			System.out.println("Vergleich der Würfel:");
			System.out.println("Angreifer: "+ wuerfelVergleich[0][0] + ", Verteidiger: "+ wuerfelVergleich[0][1] + " -> " + wuerfelVergleich[0][2] + " verlieht eine Einheit.");
			if( wuerfelVergleich[1][0]!=null) {
				System.out.println("Angreifer: "+ wuerfelVergleich[1][0] + ", Verteidiger: "+ wuerfelVergleich[1][1] + " -> " + wuerfelVergleich[1][2] + " verlieht eine Einheit.");
			}
				
			if(risiko.getProvinz(toProvinz).getBesitzer().getName().equals(risiko.getSpielerName(spielerID))) {
				System.out.println(risiko.getSpielerName(spielerID) + " hat die Provinz " + risiko.getProvinz(toProvinz) + " von " + verteidiger + " erobert!");
			}else {
				System.out.println("Der Verteidiger " + verteidiger + " hat seine Pronvinz erfolgreich verteidigt.");
			}
		}else {
			System.out.println("Du kannst leider nicht angreifen. Bitte prüfe deine Eingabe.");
		}
		System.out.println("\n");
	}


	//------------------------------------VERSCHIEBEN--------------------------------------
	
	//NACHSCHAUEN: verschieben �ber mehrere L�nder (Matrix?)git
//	risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahlEinheiten);

	private void einheitenVerschieben(int spielerIndex) {
		System.out.println("----------Phase: Einheiten Verschieben-----------");
		String input = "";
		int fromProvinz = 42;
		int toProvinz = 42;
		int anzahlEinheiten = 0;
		
		while(true) {
			System.out.println("-------------L�nder von Spieler " + risiko.getSpielerName(spielerIndex) + "--------------");
			laenderInfoAusgeben(spielerIndex);
			
			System.out.println("Einheiten verschieben:        'v'");
			System.out.println("Phase beenden:        'q'");
			//TODO : Weitere Option "Spiel beenden"
			
			try {
				input = liesEingabe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(input.equals("q")) {
				return;
			}
			
			try {
				System.out.print("Von Provinz (ID): ");
				fromProvinz = Integer.parseInt(liesEingabe());
				
				System.out.print("Nach Provinz (ID): ");
				toProvinz = Integer.parseInt(liesEingabe());
				
				System.out.print("Anzahl der Einheiten: ");
				anzahlEinheiten = Integer.parseInt(liesEingabe());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO: Validierung Eingaben -> in Risiko
			risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahlEinheiten);	
		}
	}	
	
	
	//----------------------------MEHRFACH GEBRAUCHT-------------------------
	
	
	
	private String liesEingabe()  throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}
	
	private void weltkarteAusgeben() {
		System.out.println("AKTUELLE WELTKARTE:");
		for(int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println("-------------L�nder von Spieler " + risiko.getSpielerName(i) + "--------------");
			laenderInfoAusgeben(i);
			
		}
	}
	
}
