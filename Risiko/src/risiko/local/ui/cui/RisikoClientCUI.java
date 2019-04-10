package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import risiko.local.domain.Risiko;
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
		//try-catch exception
		cui = new RisikoClientCUI();
		cui.run();
	}
	
	private void run() {//gegebenenfalls l�schen
		spielerRegistrierung();
		spielMenue();
	}
	
	
	//-----------------------------SPIELERREGISTRIERUNG----------------------------------
	
	
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
	
	
	//-----------------------------SPIELMEN�----------------------------------
	
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(!validiereProvinzID(provinzID, spielerID) ) {
//			System.out.println("Die angegebene Prvinz ID gibt es nicht oder die Provinz geh�rt dir nicht! Bitte erneut eingeben: ");
//		} else if(!validiereAnzahlEinheiten(anzahlEinheiten, spielerID))
//			System.out.println("Anzahl der Einheiten ist nicht korrekt, bitte neu eingeben!");
//		}
		
		
		if(risiko.validiereProvinzID(provinzID, spielerID) && risiko.validiereAnzahlEinheiten(anzahlEinheiten, spielerID)) {
			risiko.berechneVerteilbareEinheiten(-anzahlEinheiten, spielerID);
			risiko.einheitenVerteilen(provinzID, anzahlEinheiten);
		} else if(!risiko.validiereProvinzID(provinzID, spielerID)){
			System.out.println("Die angegebene Prvinz ID gibt es nicht oder die Provinz geh�rt dir nicht! Bitte erneut eingeben: ");
			einheitenwahlVerarbeiten(spielerID, 42, 0);
		} else if(!risiko.validiereAnzahlEinheiten(anzahlEinheiten, spielerID)) {
			System.out.println("Anzahl der Einheiten ist nicht korrekt, bitte neu eingeben!");
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
		
		while(anzahlMoeglich>0) {
			toProvinz = 42;
			anzahlEinheitenWollen = 0;
			System.out.println("So viele einheiten: " + anzahlMoeglich);
			try {			
				System.out.print("Auf welche Provinz (ID) m�chtest du deine Einheit(en) setzen? : ");
				toProvinz = Integer.parseInt(liesEingabe());
			
				System.out.print("Wie viele Einheiten m�chtest du setzen? : ");
				anzahlEinheitenWollen = Integer.parseInt(liesEingabe());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			risiko.setzeNeueEinheiten(toProvinz, anzahlEinheitenWollen);
			anzahlMoeglich -= anzahlEinheitenWollen;
		}
	}
	
	
	//-------------------------------ANGRIFF--------------------------------------
	
	
	private void angreifen(int spielerID) {
		System.out.println("------------Phase: Angreifen--------------");
		String input = "";
		int fromProvinz = 42;
		int toProvinz = 42;
		int anzahlEinheiten = 0;
		
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
			if(input.equals("q")) {
				return;
			}
			
			try {
				System.out.print("Von Provinz (ID): ");
				fromProvinz = Integer.parseInt(liesEingabe());
				
				System.out.print("Nach Provinz (ID): ");
				toProvinz = Integer.parseInt(liesEingabe());
				
				System.out.print("Mit wie vielen Einheiten m�chtest du angreifen? (max 3) ");
				anzahlEinheiten = Integer.parseInt(liesEingabe());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int[] wuerfelErgebnisse = risiko.wuerfeln(anzahlEinheiten, toProvinz);
			
			String verteidiger = risiko.getProvinz(toProvinz).getBesitzer().getName();
			
			if(!risiko.validiereProvinzID(fromProvinz, spielerID)) {
				System.out.println("Die angegebene Prvinz ID gibt es nicht oder die Provinz geh�rt dir nicht! Bitte erneut eingeben: ");
				//NACHSCHAUEN: verschieben �ber mehrere L�nder (Matrix?)git
//				risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahlEinheiten);
			} else if(!risiko.validiereZielProvinz(fromProvinz, toProvinz, spielerID)) {
				System.out.println("Die angegebene Prvinz ID gibt es nicht, geh�rt dir oder grenzt nicht an die angreifende Provinz an! Bitte erneut eingeben: ");
			} else if(!risiko.validiereAnzahlAngreifendeEinheiten(fromProvinz, spielerID, anzahlEinheiten)) {
				System.out.println("Sie haben entweder nicht genug Einheiten ");
			} else {
				String[][] ergebnis = risiko.angreifen(fromProvinz, toProvinz, anzahlEinheiten, wuerfelErgebnisse);
				//ToDo Try Catch + Exception statt if ergebnis == null
				if(ergebnis!=null) {
					for(int t = 0; t < anzahlEinheiten; t++) {
						System.out.println("Spieler " + risiko.getSpielerName(spielerID) + " hat eine " + wuerfelErgebnisse[t] + " gew�rfelt!");
					}
					
					for(int k = anzahlEinheiten; k < wuerfelErgebnisse.length; k++) {
						System.out.println("Der verteidigende Spieler " + verteidiger + " hat eine " + wuerfelErgebnisse[k] + "gew�rfelt!");
					}
					//Ergebnisse der einzelnen Wurf-Vergleiche
					System.out.println("Vergleich der Würfel:");
					System.out.println("Angreifer: "+ ergebnis[0][0] + ", Verteidiger: "+ ergebnis[0][1] + " -> " + ergebnis[0][2] + " verlieht eine Einheit.");
					if( ergebnis[1][0]!=null) {
						System.out.println("Angreifer: "+ ergebnis[1][0] + ", Verteidiger: "+ ergebnis[1][1] + " -> " + ergebnis[1][2] + " verlieht eine Einheit.");
					}
					
					if(risiko.getProvinz(toProvinz).getBesitzer().getName().equals(risiko.getSpielerName(spielerID))) {
						System.out.println(risiko.getSpielerName(spielerID) + " hat die Provinz " + risiko.getProvinz(toProvinz) + " von " + verteidiger + " erobert!");
					}else {
						System.out.println("Der Verteidiger " + verteidiger + " hat seine Pronvinz erfolgreich verteidigt.");
					}
				}else {
					System.out.println("Du kannst leider nicht angreifen. Bitte prüfe deine Eingabe.");
				}
			}
			
			
			
			
			

		}
	}
	
	
	
	//------------------------------------VERSCHIEBEN--------------------------------------
	
	

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
	
	
	
	private void weltkarteAusgeben() {
		System.out.println("AKTUELLE WELTKARTE:");
		for(int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println("-------------L�nder von Spieler " + risiko.getSpielerName(i) + "--------------");
			laenderInfoAusgeben(i);
			
		}
	}
	
	private String liesEingabe()  throws IOException{
		// einlesen von Konsole
		return in.readLine();
	}
}
