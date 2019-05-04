package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.AnzahlDerSpielerNichtKorrektException;
import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
import risiko.local.domain.exceptions.KeineEinheitenKartenInBesitzException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.domain.exceptions.TauschenNichtMoeglichException;
import risiko.local.valueobjects.Einheitenkarte;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() throws IOException {
		spielerRegistrierung();
		spielMenue();
	}

	
	
	// -----------------------------SPIELERREGISTRIERUNG----------------------------------

	
	
	private void spielerRegistrierung() throws IOException {

		//Abfrage der Anzahl der Spieler mit Exception fuer falsche Eingabe
		int anzahl = 0;
		boolean anzahlKorrekt = false;
		
		while (!anzahlKorrekt) {
			try {
				anzahl = erfrageSpielerAnzahl();
				anzahlKorrekt = true;
			} catch (AnzahlDerSpielerNichtKorrektException e1) {
				System.out.println(e1.getMessage() + "\nBitte eine andere Spieleranzahl waehlen!\n");
			}
		}
		
		//Anmelden der Spieler mit Exception fuer falsche Eingabe
		boolean angemeldet = false;
		for (int i = 0; i < anzahl; i++) {
			angemeldet = false;
			while (!angemeldet) {
				System.out.println("Geben Sie bitte den Namen fuer Spieler " + (i + 1) + " ein:");
				String name = liesEingabe();
				
				try {
					if (!risiko.spielerNameVorhanden(name)) {
						risiko.spielerHinzufuegen(name);
						angemeldet = true;
					}
				} catch (SpielerBereitsVorhandenException e2) {
					System.out.println(e2.getMessage() + "\nBitte einen anderen Namen waehlen!\n");
				}
			}
		}
	}

	private int erfrageSpielerAnzahl() throws AnzahlDerSpielerNichtKorrektException {
		int anzahl = 0;

		System.out.println("Gib die Anzahl der Spieler an (zwischen 2 und 6):");
		// Eingabe der Anzahl der Spieler
		
		try {
			anzahl = Integer.parseInt(liesEingabe());
			if (anzahl < 2 || anzahl > 6) {
				throw new AnzahlDerSpielerNichtKorrektException();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Bitte eine positive, ganze Zahl eingeben.");
			anzahl = erfrageSpielerAnzahl();
		}
		return anzahl;
	}

	
	
	// -----------------------------SPIELMENUE----------------------------------

	
	
	private void spielMenue() {
		//Auswahl im Spielmenue
		String input = "";
		do {
			spielMenueAusgeben();
			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}
			verarbeiteSpielmenue(input);
		} while (!input.equals("q"));
	}

	private void spielMenueAusgeben() {
		System.out.println("Neues Spiel starten:        'n'");
		System.out.println("Spiel laden: 'l'"); //zukuenftig
		// System.out.println("Spiel beitreten: 'b'"); //zukuenftig
		System.out.println("---------------------");
		System.out.println("Beenden:        'q'");
	}

	private void verarbeiteSpielmenue(String input) {
		//Eingabe aus spielMenue() verarbeiten
		switch (input) {
		case "n":
		case "N": //Spiel starten
			spielStarten();
			break;
		case "l":
		case "L": //Spiel laden
			//TODO: Einladen pruefen
			System.out.println("Spiel ID: ");
			try {
				input = liesEingabe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int spielerID = risiko.spielLaden(input);
			spielen(++spielerID);
			break;
//		case "b":
//		case "B": //Spiel beitreten
//			spielBeitreten();
//			break;
		case "Q":
		case "q": //Spiel beenden
			System.exit(0);
		default:
			//wenn kein Case eintritt --> falsche Eingabe
			System.out.println("\nEingabe fehlerhaft! \nBitte waehle eine der folgenden Optionen:");
		}
	}

	private void spielStarten() {
		risiko.spielVorbereiten();
		weltkarteAusgeben();
		einheitenVerteilen();
		String gewinner = spielen(0);
		gewinnerAusgeben(gewinner);
	}

	

	// ---------------------------EINHEITEN VERTEILEN-------------------------------

	

	
	//setzen der ersten Einheiten
	private void einheitenVerteilen() {
		for (int j = 0; j < risiko.getSpielerAnzahl(); j++) {
			missionenVerraten(j);
			while (risiko.getVerteilbareEinheiten(j) > 0) {
				System.out.println(risiko.getSpielerName(j) + " ist an der Reihe und darf "
						+ risiko.getVerteilbareEinheiten(j) + " Einheiten setzen!");
				einheitenwahlVerarbeiten(j, 42, 0);
			}
		}
	}
	
	private void missionenVerraten(int spielerID) {
		System.out.println("\n**Mission: " + risiko.getMissionVonSpieler(spielerID)+"**\n");
	}

	private void einheitenwahlVerarbeiten(int spielerID, int provinzID, int anzahlEinheiten) {
		try {
			System.out.print("Provinz ID: ");
			provinzID = Integer.parseInt(liesEingabe());

			System.out.print("Anzahl Einheiten: ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());

		} catch (NumberFormatException e) {
			System.out.println("Bitte eine positive, ganze Zahl eingeben.");
			einheitenwahlVerarbeiten(spielerID, provinzID, anzahlEinheiten);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			risiko.setzeNeueEinheiten(provinzID, anzahlEinheiten, spielerID);
			risiko.berechneVerteilbareEinheiten(-anzahlEinheiten, spielerID);
			// auf korrekte Eingaben pruefen
		} catch (Exception e) {
			System.out.println(e.getMessage());
			einheitenwahlVerarbeiten(spielerID, 42, 0);
		}
	}

	
	
	// --------------------------------SPIELEN-----------------------------------------

	
	
	private String spielen(int spielerID) {
		String gewinner = "";
		//Runden (=jeder Spieler durchlaeuft jede Phase ein mal)
//		
		if(spielerID == risiko.getSpielerAnzahl()) {
			spielerID = 0;
		}
		
		
		while (true) {
			//einzelnen Spielzuege mit jeweiligen Phasen
			for (int o = spielerID; o < risiko.getSpielerAnzahl(); o++) {
				weltkarteAusgeben();
				
				menueNeueEinheitenPhase(o);
				// Einheiten bekommen / berechnen
				// Einheiten setzen
				gewinner = angreifen(o);
				// angreifen
				if (!gewinner.equals("")) {
					return gewinner;
				}
				// verschieben
				verschiebeMenu(o);
				//Einheitenkarten verwalten
				einheitenkarteVerteilen(o);
				// Einheiten, die in der Runde involviert waren fuer naechste Runde zuruecksetzen
				risiko.resetSpielerAttribute(o);
				
			}
			spielerID = 0;
		}
	}


	// ----------------------------------NEUE EINHEITEN---------------------------------

	

	private void menueNeueEinheitenPhase(int spielerID) {
		
		System.out.println("\n------------Spieler: " + risiko.getSpielerName(spielerID) + "--------------");
		System.out.println("------------Phase: Einheiten setzen--------------\n");
		
		while (true) {
			
			System.out.println("Karten fuer zusaetzliche Einheiten anzeigen:      'a'");
			System.out.println("Karten fuer zusaetzliche Einheiten eintauschen:   'e'");
			System.out.println("Einheiten setzen:                                 's'");

			String input = "";
			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (input) {
			case "a":
				try {
					eineheitenKartenAusgeben(spielerID);
				} catch (KeineEinheitenKartenInBesitzException e) {
					System.out.println(e.getMessage()+ "\n");
				}
				break;
			case "e":
				einheitenkartenEintauschen(spielerID);
				break;
			case "s":
				neueEinheitenPhase(spielerID);
				return;
			default:
				System.out.println("\nEingabe fehlerhaft! \nBitte waehle eine der folgenden Optionen:");
			}
		}
	}

	private void neueEinheitenPhase(int spielerID) {		
		neueEinheiten(spielerID);
	}

	private void einheitenkartenEintauschen(int spielerID) {
			
		while (true) {
			try {
				risiko.kannEintauschen(spielerID);
			}catch(TauschenNichtMoeglichException e) {
				System.out.println(e.getMessage() + "\n");
				return;
			}
				
			String input = "";
				
			System.out.println("Welche Karten moechtest du Eintauschen? ");
			System.out.println("Drei Soldaten eintauschen:     							  's'");
			System.out.println("Drei Reiter eintauschen:       							  'r'");
			System.out.println("Drei Kanonen eintauschen:   						      'k'");
			System.out.println("Einen Soldat, einen Reiter und eine Kanone eintauschen:   'a'");
					
			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				risiko.einheitenKartenEintauschen(input, spielerID);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
		}
	}
	

	private void neueEinheiten(int spielerID) {
		// Einheiten, die zu Beginn jeder Runde verteilt werden duerfen
		risiko.berechneNeueEinheiten(spielerID);

		int toProvinz = 42;
		int anzahlEinheitenWollen = 0;
		

		while (risiko.getVerteilbareEinheiten(spielerID) > 0) {
			toProvinz = 42;
			anzahlEinheitenWollen = 0;
			
			System.out.println("\nAnzahl der Einheiten: " + risiko.getVerteilbareEinheiten(spielerID));
			try {
				System.out.print("Auf welche Provinz (ID) moechtest du deine Einheit(en) setzen? : ");
				toProvinz = Integer.parseInt(liesEingabe());

				System.out.print("Wie viele Einheiten moechtest du setzen? : ");
				anzahlEinheitenWollen = Integer.parseInt(liesEingabe());

				risiko.setzeNeueEinheiten(toProvinz, anzahlEinheitenWollen, spielerID);
				risiko.berechneVerteilbareEinheiten(-anzahlEinheitenWollen, spielerID);
				
			} catch (NumberFormatException e) {
				System.out.println("Bitte eine positive, ganze Zahl eingeben.");
				neueEinheiten(spielerID);
				return;
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}

		}
	}

	
	
	// -------------------------------ANGRIFF--------------------------------------

	
	
	private String angreifen(int spielerID) {
		String gewinner = "";
		System.out.println("\n------------Phase: Angreifen--------------");
		String input = "";

		while (gewinner.equals("")) {
			System.out.println("-------------Spieler " + risiko.getSpielerName(spielerID) + "--------------\n");

			System.out.println("Angreifen:              'a'");
			System.out.println("Weltkarte anzeigen:     'w'");
			System.out.println("Phase beenden:          'q'");

			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (input) {
			case "q":
				return "";
			case "a":
				angriffAusfuehren(spielerID);
				break;
			case "w":
				weltkarteAusgeben();
				break;
			default:
				System.out.println("\nEingabe fehlerhaft! \nBitte waehle eine der folgenden Optionen:");
			}
			gewinner = risiko.einerHatGewonnen(spielerID);

		}
		return gewinner;
	}

	private void angriffAusfuehren(int spielerID) {
		// Einlesen der Eingaben fuer den Angriff
		int fromProvinz = 42;
		int toProvinz = 42;
		int anzahlEinheiten = 0;

		try {
			System.out.print("Von Provinz (ID): ");
			fromProvinz = Integer.parseInt(liesEingabe());

			System.out.print("Nach Provinz (ID): ");
			toProvinz = Integer.parseInt(liesEingabe());

			System.out.print("Mit wie vielen Einheiten moechtest du angreifen? (max 3): ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());

			risiko.validiereAngriffEingaben(fromProvinz, toProvinz, spielerID, anzahlEinheiten);
			//Inhaltliche ueberpruefung der Eingaben
		} catch (NumberFormatException e) {
			System.out.println("Bitte eine positive, ganze Zahl eingeben.");
			angriffAusfuehren(spielerID);
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			angriffAusfuehren(spielerID);
			return;
		}
		System.out.println("");

		String verteidiger = risiko.getProvinz(toProvinz).getBesitzer().getName();
		int[] wuerfelErgebnisse;
		wuerfelErgebnisse = risiko.wuerfeln(anzahlEinheiten, toProvinz);

		try {
			String[][] wuerfelVergleich = risiko.angreifen(fromProvinz, toProvinz, anzahlEinheiten, wuerfelErgebnisse);

			// Ausgaben
			for (int t = 0; t < anzahlEinheiten; t++) {
				System.out.println("Spieler " + risiko.getSpielerName(spielerID) + " hat eine " + wuerfelErgebnisse[t]
						+ " gewuerfelt!");
			}
			for (int k = anzahlEinheiten; k < wuerfelErgebnisse.length; k++) {
				System.out.println("Der verteidigende Spieler " + verteidiger + " hat eine " + wuerfelErgebnisse[k]
						+ " gewuerfelt!");
			}
			System.out.println("");
			// Ergebnisse der einzelnen Wurf-Vergleiche ausgeben
			wuerfelVergleichAusgeben(wuerfelVergleich, toProvinz, fromProvinz, spielerID, verteidiger);

		} catch (ProvinzNichtNachbarException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n");
	}

	private void wuerfelVergleichAusgeben(String[][] wuerfelVergleich, int toProvinz, int fromProvinz, int spielerID,
			String verteidiger) {
		// Ergebnisse der einzelnen Wurf-Vergleiche
		System.out.println("Vergleich der Wuerfel:");
		System.out.println("Angreifer: " + wuerfelVergleich[0][0] + ", Verteidiger: " + wuerfelVergleich[0][1] + " -> "
				+ wuerfelVergleich[0][2] + " verliert eine Einheit.");
		if (wuerfelVergleich[1][0] != null) {
			System.out.println("Angreifer: " + wuerfelVergleich[1][0] + ", Verteidiger: " + wuerfelVergleich[1][1]
					+ " -> " + wuerfelVergleich[1][2] + " verliert eine Einheit.");
		}

		if (risiko.getProvinz(toProvinz).getBesitzer().getName().equals(risiko.getSpielerName(spielerID))) {
			risiko.provinzWurdeErobert(spielerID); //aendert boolean, um Einheitskarte zu bekommen (boolean provinzErobert)
			System.out.println("*********************");
			System.out.println(risiko.getSpielerName(spielerID) + " hat die Provinz "
					+ risiko.getProvinz(toProvinz).getName() + " von " + verteidiger + " erobert!");
			System.out.println("*********************\n");
			// Provinz erobert
			einheitenNachruecken(spielerID, fromProvinz, toProvinz);
		} else {
			System.out.println("Der Verteidiger " + verteidiger + " hat seine Pronvinz verteidigt.");
		}
	}

	private void einheitenNachruecken(int spielerID, int fromProvinz, int toProvinz) {
		//nach einem Angriff, indem eine Provinz erobert wurde, k�nnen Einheiten nachgerueckt werden
		if (risiko.kannEinheitenNachruecken(spielerID, fromProvinz)) {
			try {
				if (willEinruecken()) {
					int anzahl = 0;
					System.out.println("Wie viele Einheiten moechtest du nachruecken? ");
					try {
						anzahl = Integer.parseInt(liesEingabe());
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahl);
					} catch (AnzahlEinheitenFalschException | NichtProvinzDesSpielersException
							| ProvinzNichtNachbarException | ProvinzIDExistiertNichtException e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Bitte eine positive, ganze Zahl eingeben.");
				einheitenNachruecken(spielerID, fromProvinz, toProvinz);
				return;
			}
		}
	}

	private boolean willEinruecken() {
		//Abfrage, ob der Spieler ueberhautp einruecken moechte, wenn er kann
		String input = "";
		System.out.println("Moechtest du weitere Einheiten nachruecken?");
		try {
			System.out.println("Ja:             'j'");
			System.out.println("Nein:           'n'");
			input = liesEingabe();

		} catch (IOException e) {
			e.printStackTrace();
		}

		switch (input) {
		case "j":
		case "J":
			return true;
		case "n":
		case "N":
			return false;
		default:
			System.out.println("Fehlerhafte Eingabe, bitte erneut eingeben: ");
			willEinruecken();
		}
		return false;
	}

	
	
	// ------------------------------------VERSCHIEBEN--------------------------------------

	
	
	// NACHSCHAUEN: verschieben ueber mehrere Laender (Matrix?)git
//	risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahlEinheiten);

	private void verschiebeMenu(int spielerIndex) {
		System.out.println("----------Phase: Einheiten Verschieben-----------");
		String input = "";

		while (true) {
			System.out.println(
					"-------------Laender von Spieler " + risiko.getSpielerName(spielerIndex) + "--------------");

			System.out.println("Einheiten verschieben:        'v'");
			System.out.println("Weltkarte ausgeben:        'w'");
			System.out.println("Zug beenden:        'q'");
			System.out.println("Zug beenden und speichern:        's'");
			System.out.println("Spiel beenden und speichern:        'b'");

			try {
				input = liesEingabe();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (input) {

			case "q":
				return;
			case "w":
				weltkarteAusgeben();
				break;
			case "v":
				einheitenVerschieben(spielerIndex);
				break;
			case "s":
//				String name = "";
//				System.out.println("Spiel ID: ");
//				try {
//					name = liesEingabe();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
				risiko.speichern(spielerIndex);
				return;
			case "b":
//				String id = "";
//				System.out.println("Spiel ID: ");
//				try {
//					id = liesEingabe();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
				risiko.speichern(spielerIndex);
				System.exit(0);
			default:
				System.out.println("\nFehlerhafte Eingabe.");
			}
		}
	}

	private void einheitenVerschieben(int spielerIndex) {

		int fromProvinz = 42;
		int toProvinz = 42;
		int anzahlEinheiten = 0;

		try {
			System.out.print("Von Provinz (ID): ");
			fromProvinz = Integer.parseInt(liesEingabe());

			System.out.print("Nach Provinz (ID): ");
			toProvinz = Integer.parseInt(liesEingabe());

			System.out.print("Anzahl der Einheiten: ");
			anzahlEinheiten = Integer.parseInt(liesEingabe());

			risiko.einheitenVerschieben(fromProvinz, toProvinz, anzahlEinheiten);

		} catch (NumberFormatException e) {
			System.out.println("Bitte eine positive, ganze Zahl eingeben.");
			einheitenVerschieben(spielerIndex);
			return;
		} catch (AnzahlEinheitenFalschException e) {
			System.out.println(e.getMessage() + "\nBereits in den Kampf involvierte Einheiten koennen nicht mehr verschoben werden.");
		} catch (ProvinzIDExistiertNichtException | NichtProvinzDesSpielersException | ProvinzNichtNachbarException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void gewinnerAusgeben(String gewinnerName) {
		// ein Spieler hat alle Provinzen erobert
		System.out.println("*************************************************************");
		System.out.println("Spieler " + gewinnerName + " hat gewonnen!");
		System.out.println("*************************************************************\n\n");
	}

	
	//-------------------------------Einheitenkarten-------------------------------	
	
	
	
	private void einheitenkarteVerteilen(int spielerID) {
		if(risiko.isProvinzErobert(spielerID)) {
			Einheitenkarte neueKarte = risiko.einheitenkarteVerteilen(spielerID);
			System.out.println("Du hast eine Karte vom Typ " + neueKarte.getTyp() + " erhalten.");
		}
	}
	
	
	 
	
	
	
	
	
	
	
	
	// ----------------------------MEHRFACH GEBRAUCHT-------------------------

	
	
	private String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}

	private void weltkarteAusgeben() {
		System.out.println("\nAKTUELLE WELTKARTE:\n");
		for (int i = 0; i < risiko.getSpielerAnzahl(); i++) {
			System.out.println("\n-------------Laender von Spieler " + risiko.getSpielerName(i) + "--------------\n");
			laenderInfoAusgeben(i);

		}
	}

	private void laenderInfoAusgeben(int i) {
		//fuer Spieler i alle Provinzen ausgeben, die er besitzt
		for (Provinz provinz : risiko.getProvinzenVonSpieler(i)) {
			System.out.println(provinz);
		}
	}
	
	private void eineheitenKartenAusgeben(int spielerID) throws KeineEinheitenKartenInBesitzException  {
		for(int i = 0; i<risiko.getKartenVonSpieler(spielerID).size(); i++) {
			System.out.println(i+1 + ") " + risiko.getKartenVonSpieler(spielerID).get(i));
		}
		System.out.println("");
	}
}
