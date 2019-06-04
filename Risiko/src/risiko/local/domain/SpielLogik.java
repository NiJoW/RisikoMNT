package risiko.local.domain;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
import risiko.local.domain.exceptions.FalscheEingabeException;
import risiko.local.domain.exceptions.NichtGenugKartenFuerAktionException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.domain.exceptions.TauschenNichtMoeglichException;
import risiko.local.valueobjects.Einheitenkarte;
import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;
import risiko.local.valueobjects.Welt;
import risiko.local.valueobjects.missions.EliminiereEinenSpielerMission;
import risiko.local.valueobjects.missions.Mission;


public class SpielLogik {
	
	private WeltVerwaltung weltVW;
	private SpielerVerwaltung spielerVW;
	private SpielVerwaltung spielVW;
	
	public SpielLogik(WeltVerwaltung weltVW, SpielerVerwaltung spielerVW, SpielVerwaltung spielVW) {
		this.weltVW = weltVW;
		this.spielerVW = spielerVW;
		this.spielVW = spielVW;
	}
	
	public void weiseMissionZu() {
		Random rand = new Random();
		Vector<Spieler> sListe = spielerVW.getSpielerListe();
		Vector<Mission> mListe = spielVW.erstelleMissionen(sListe.size());
		
		for(Spieler spieler: sListe) {
			int zufall = rand.nextInt(mListe.size());
			spieler.setMission(mListe.get(zufall));
			//Ziel fuer EliminiereEinenSpielerMission festlegen --> nicht der Spieler selbst
			if(mListe.get(zufall) instanceof EliminiereEinenSpielerMission) {
				int targetID;
				do{
					targetID = rand.nextInt(sListe.size());
					
				}while(sListe.get(targetID).equals(spieler));
				((EliminiereEinenSpielerMission) mListe.get(zufall)).setTarget(sListe.get(targetID));
			}
			mListe.remove(zufall);
		}
	}
	
	
	public String einerHatGewonnen(int aktiverSpielerID) {
		Spieler aktiverSpieler = spielerVW.getSpieler(aktiverSpielerID);
		//aktiver Spieler hat die Welt erobert und gewinnt
		if(aktiverSpieler.getAnzahlAktuelleLaender() == 21) {
			return aktiverSpieler.getName();
		}
		//aktiver Spieler hat seine Mission erfüllt und gewonnen
		if(aktiverSpieler.getMission().isErfuellt(aktiverSpieler)) {
			return aktiverSpieler.getName();
		}
		//ein anderer Spieler hat seine Mission erfüllt und damit gewonnen
		Vector<Spieler> spielerListe =  spielerVW.getSpielerListe();
		for(Spieler s :spielerListe) {
			if(s.getMission().isErfuellt(s)) {
				return s.getName();
			}
		}
		//noch hat niemand gewonnen
		return "";
	}

	
	

//----------------- RUNDEN-VORBEREITUNG ---------------------------
	
	//Berechnet wie viele Einheiten der Spieler zu Beginn der Runde verteilen kann
	public void berechneNeueEinheiten(int spielerID) {
		Vector<Kontinent> kontinentListe = weltVW.getKontinentListe();
		Spieler spieler = spielerVW.getSpieler(spielerID);
		int anzahl = spieler.getAnzahlAktuelleLaender()/3;
		if(anzahl < 3) {
			anzahl = 3;
			// immer mindestens 3 Einheiten
		}
		// Bonus-Einheiten durch Besetzung eines vollstaendigen Kontinents erhalten	
		anzahl += getKontinentBonus(spieler, kontinentListe);
		spielerVW.berechneVerteilbareEinheiten(anzahl, spielerID);
	}

	private int getKontinentBonus(Spieler spieler, Vector<Kontinent> kontinentListe) {
		int bonus = 0;
		for(int i = 0; i < kontinentListe.size() ; i++) {
			if(kontinentListe.get(i).isHerrscher(spieler)) {
				switch(kontinentListe.get(i).getName()) {
					case "Asien":
						bonus += 7;
						break;
					case "Afrika":
						bonus += 3;	
						break;
					case "Europa": 
					case "Nord-Amerika": 	
						bonus += 5;
						break;
					case "Sued-Amerika": 
					case "Australien": 	
						bonus += 2;
						break;	
				}
				
			}
			
		}
			
		return bonus;
	}


	
	//----------------------ANGREIFEN------------------------	
	
	
	
	public void kannAngreifen(int from, int to) throws ProvinzNichtNachbarException {	
		Welt welt = weltVW.getWelt();
		
		if(!welt.isNachbar(from, to)) {
			throw new ProvinzNichtNachbarException("Angriff");
		}
	}
	
	
	
	public int[] wuerfeln(int anzahlEinheiten, int toProvinzID) {
		Provinz toProvinz = weltVW.getProvinz(toProvinzID);
		Random rand = new Random();
		int verteidigendeEinheiten = 1;
		if(toProvinz.getArmeeGroesse() >= 2) {
			verteidigendeEinheiten = 2;
		}
		int [] wuerfelErgebnisse = new int[anzahlEinheiten + verteidigendeEinheiten];
		//Array mit Laenge der im Kampf verwickelten Einheiten
		
		int zaehler = 0;
		switch(anzahlEinheiten) {
		case 3: 
			wuerfelErgebnisse[zaehler] = rand.nextInt(6)+1;
			zaehler++;
		case 2:
			wuerfelErgebnisse[zaehler] = rand.nextInt(6)+1;
			zaehler++;
		case 1:
			wuerfelErgebnisse[zaehler] = rand.nextInt(6)+1;
			zaehler++;
			break;
		}
		
		wuerfelErgebnisse[zaehler] = rand.nextInt(6)+1;
		zaehler++;
		if(verteidigendeEinheiten == 2) {
			wuerfelErgebnisse[zaehler] = rand.nextInt(6)+1;
		}
		return wuerfelErgebnisse;
	}
	
	public String[][] angriffAuswerten(int[] wuerfelErgebnisse, int fromProvinz, int toPrvinz, int anzahlEinheiten) {
		Provinz from = weltVW.getProvinz(fromProvinz);
		Provinz to = weltVW.getProvinz(toPrvinz);
		int angreiferArray[] = new int[anzahlEinheiten];
		int verteidigerArray[] = new int[wuerfelErgebnisse.length - anzahlEinheiten];
		String [][] ergebnis = new String[2][3]; 
		// Array, in dem die Ergebnisse gespeichert werden
		
		//Arrays nach Angreifer und Verteidiger trennen
		for(int i = 0; i < anzahlEinheiten; i++) {
			angreiferArray[i]  =  wuerfelErgebnisse[i];
		}
		int l = 0;
		for(int j = anzahlEinheiten; j < wuerfelErgebnisse.length; j++) {
			verteidigerArray[l]  =  wuerfelErgebnisse[j];
			l++;
		}
		//Arrays sortieren, um die hoechsten Zahlen vergleichen zu koennen
		angreiferArray = sortiereArray(angreiferArray);
		verteidigerArray = sortiereArray(verteidigerArray);
		
		int angreiferGewonnen = 0;
		int verteidigerGewonnen = 0;
		// Zaehlen der Siege von Angreifer/Verteidiger
		
		ergebnis[0][0] = angreiferArray[0]+"";
		ergebnis[0][1] = verteidigerArray[0]+"";
		
		// Auswertung/Vergleich der Wuerfelergebnisse
		if(angreiferArray[0] > verteidigerArray[0]) {
			//Bei gleichem Ergebniss gewinnt der Verteidiger
			angreiferGewonnen++;
			ergebnis[0][2] = to.getBesitzer().getName(); //Verlierer
		} else {
			verteidigerGewonnen++;
			ergebnis[0][2] = from.getBesitzer().getName();
		}
		
		if(anzahlEinheiten > 1 && verteidigerArray.length > 1) {
			//2. Wuerfel ueberpruefen
			ergebnis[1][0] = angreiferArray[1]+"";
			ergebnis[1][1] = verteidigerArray[1]+"";
			if(angreiferArray[1] > verteidigerArray[1]) {
				angreiferGewonnen++;
				ergebnis[1][2] = to.getBesitzer().getName();
			} else {
				verteidigerGewonnen++;
				ergebnis[1][2] = from.getBesitzer().getName();
			} 	
		}
		aendereEinheiten(from, to, angreiferGewonnen, verteidigerGewonnen, anzahlEinheiten );
		return ergebnis;
	}
	
	private int[] sortiereArray (int[] array) {
		//Bubble-Sort
		for (int k = array.length-1; k > 0; k--){
            for(int m = 0; m < k; m++){
                if(array[m] < array[m + 1]){
                    int temp = array[m];
                    array[m] = array [m+1];
                    array[m+1] = temp;
                }
            }
        }
		return array;
	}
	
	//bei Angriff Anzahl der Einheiten auf den Provinzen aendern
	private void aendereEinheiten(Provinz from, Provinz to, int angreiferGewonnen, int verteidigerGewonnen, int anzahl) {
		while(angreiferGewonnen > 0) {
			//Verteidiger hat sein Land verloren
			if((to.getArmeeGroesse()-1 == 0)) {
				//wenn Verteidiger Armee = 0 --> Anzahl der aktuellen Laender aendern
				to.getBesitzer().berechneAktuelleLaender(-1);
				from.getBesitzer().berechneAktuelleLaender(1);
			}
			//Angreifer: Armee um 1 verkleinern
			to.verkleinereArmee(1);
			if(to.getArmeeGroesse() == 0) {
				einruecken(from, to, anzahl-verteidigerGewonnen);
				//zukueftig: Laenderkarte verteilen
			}
			angreiferGewonnen--;
		}
		// Einheiten vom Verteidiger abziehen
		while(verteidigerGewonnen > 0) {
			from.verkleinereArmee(1);
			verteidigerGewonnen--;
		}
		
	}
	
	private void einruecken(Provinz from, Provinz to, int anzahlEinheiten) {
		from.verschiebeEinheitenNach(anzahlEinheiten, to);
	}
	
	// pruefen, ob nach Einnahme einer Provinz noch genuegend 
	// Einheiten vorhanden sind, um welche nachzuruecken
	public boolean kannEinheitenNachruecken(int spielerID, int fromProvinz) {
		Vector<Provinz> pListe = weltVW.getProvinzListe();
		int verschiebbareEinheiten = pListe.get(fromProvinz).getAnzahlVerschiebbareEinheiten();
		
		if(verschiebbareEinheiten > 1) {
			return true;
		}
		return false;
	}
	
	
	
	//----------------------VERSCHIEBEN------------------------
	
	
	
	public void kannVerschieben(int from, int to, int anzahlEinheiten) throws AnzahlEinheitenFalschException, NichtProvinzDesSpielersException, ProvinzNichtNachbarException, ProvinzIDExistiertNichtException {
		Vector<Provinz> pListe = weltVW.getProvinzListe();
		Welt welt = weltVW.getWelt();
		
		validiereProvinzID(from);
		validiereProvinzID(to);
		
		int verschiebbareEinheiten = pListe.get(from).getAnzahlVerschiebbareEinheiten();
		if(anzahlEinheiten > verschiebbareEinheiten) {
			// zu viele Einheiten ausgewaehlt
			throw new AnzahlEinheitenFalschException(verschiebbareEinheiten);
		}
			
		beziehungPruefen(welt, pListe, from, to);
		
		
	}
	

	public void beziehungPruefen(Welt welt, Vector<Provinz> pListe, int from, int to) throws NichtProvinzDesSpielersException, ProvinzNichtNachbarException {

		if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer()))) {
			//kann nur in eigene Provinzen Einheiten verschieden
			throw new NichtProvinzDesSpielersException();
		} 
		if(!(welt.isNachbar(from, to))) {
			//nicht direkte Nachbarn (wenn auch gleiche Besitzer) -> kann nicht verschieben
			throw new ProvinzNichtNachbarException("Verschieben");	
		}
// verschieben ueber mehrere eigene Laender:  prueft transitive Beziehung
//		else {
//			for(int dTo = 0; dTo < welt.getBeziehungsMatrix().length; dTo++) {
//				if(welt.getBeziehung(from, dTo) && kannVerschieben(dTo, to, pListe, welt)) {
//					//return true;
//				} else {
//					//throw		
//					//return false; 
//				}
//			}
//		}
	}

	private void validiereProvinzID(int provinz) throws ProvinzIDExistiertNichtException {
		if(provinz<0 || provinz>41) {
			//ungueltige ProvinzID
			throw new ProvinzIDExistiertNichtException();
		}
	}

	public void verschiebe(int anzahlEinheiten, int fromProvinz, int toProvinz) {
		Provinz from = weltVW.getProvinz(fromProvinz);
		Provinz to = weltVW.getProvinz(toProvinz);
		
		from.verschiebeEinheitenNach(anzahlEinheiten, to);
	}

	



//-----------------EINHEITENKARTEN--------------------



	public Einheitenkarte einheitenkarteVerteilen(Spieler spieler) {
		Random rand = new Random();
		Einheitenkarte neueKarte = null;
		Vector<Einheitenkarte> karten = spielVW.getKartenListe();
		int zufall = rand.nextInt(karten.size());
		neueKarte = karten.get(zufall);
		spieler.addKarte(neueKarte);
		karten.remove(zufall);
		return neueKarte;
	}
	
	
	public boolean kannEintauschen(Spieler spieler) throws TauschenNichtMoeglichException {
		Vector<Einheitenkarte> karten = spieler.getKarten();
		if(karten.size() >= 3) {
			int [] kartenAnzahl = EinheitenkartenZaehlen(karten);
			for(int i = 0; i < 3; i++) {
				if((kannDreiGleicheTypTauschen(kartenAnzahl, i)) != null) {
					return true;
				}
			}
			if(jeEineTauschen(kartenAnzahl)) {
				return true;
			}
		}
		throw new TauschenNichtMoeglichException();
	}
	
	public boolean jeEineTauschen(int [] kartenAnzahl) {
		
		if(kartenAnzahl[0] >= 1 && kartenAnzahl[1] >= 1 && kartenAnzahl[2] >= 1) {
			return true;
		}
		return false;
	}

	public Vector<Integer> kannDreiGleicheTypTauschen(int [] kartenAnzahl, int stelle) {
		//0 = Soldaten
		//1 = Reiter
		//2 = Kanonen
		Vector<Integer> moeglichkeiten = new Vector<Integer>();
		if(kartenAnzahl[stelle] >= 3) {
			moeglichkeiten.add(stelle);
		}
		return moeglichkeiten;
	}

	
	public int[] EinheitenkartenZaehlen(Vector<Einheitenkarte> karten) {
		int[] kartenAnzahl = new int[3];
		int anzahlSoldaten = 0;
		int anzahlReiter= 0;
		int anzahlKanonen = 0;
		
		for(Einheitenkarte karte: karten) {
			if(karte.getTyp().equals("Soldat")) {
				anzahlSoldaten++;
			} else if(karte.getTyp().equals("Reiter")) {
				anzahlReiter++;
			} else if(karte.getTyp().equals("Kanone")) {
				anzahlKanonen++;
			}
		}
		kartenAnzahl[0] = anzahlSoldaten;
		kartenAnzahl[1] = anzahlReiter;
		kartenAnzahl[2] = anzahlKanonen;
		return kartenAnzahl;
	}

	public int einheitenKartenEintauschen(String input, Spieler spieler) throws FalscheEingabeException, NichtGenugKartenFuerAktionException {
		String typ = "";
		int num = 3;
		
		switch(input) {
		case "s":
			typ = "Soldat";
			num = 0;
			break;
		case "r":
			typ = "Reiter";
			num = 1;
			break;
		case "k":
			typ = "Kanone";
			num = 2;
			break;
		case "a":
			return jeEineKarteEintauschen(spieler);
			
		default:
			throw new FalscheEingabeException();
		}
		return typEintauschen(spieler, typ, num);
		
	}

	private int typEintauschen(Spieler spieler, String typ, int num) throws NichtGenugKartenFuerAktionException {
		Vector<Einheitenkarte> karten = spieler.getKarten();
		int[] kartenAnzahl = EinheitenkartenZaehlen(karten);
		
		if(kartenAnzahl[num] >= 3) {
			int zaehler = 0;
			ArrayList<Einheitenkarte> removeIDs = new ArrayList<>();
			
			for(Einheitenkarte karte : karten) {
				if(karte.getTyp().equals(typ) && zaehler < 3) {
					removeIDs.add(karte);
					zaehler++;
				}
			}
			for (Einheitenkarte deleteKarte : removeIDs){
				  karten.remove(deleteKarte);
			}
		} else {
			throw new NichtGenugKartenFuerAktionException();
		}
		
		return einheitenkartenBonusErhoehen(spieler);
	}

	private int jeEineKarteEintauschen(Spieler spieler) throws NichtGenugKartenFuerAktionException {
		Vector<Einheitenkarte> karten = spieler.getKarten();
		int[] kartenAnzahl = EinheitenkartenZaehlen(karten);
		
		if(kartenAnzahl[0] >= 1 && kartenAnzahl[1] >= 1 && kartenAnzahl[2] >= 1) {
			int sZaehler = 0;
			int rZaehler = 0;
			int kZaehler = 0;
			ArrayList<Einheitenkarte> removeIDs = new ArrayList<>();
			
			for(Einheitenkarte karte : karten) {
				if(karte.getTyp().equals("Soldat") && sZaehler == 0) {
					removeIDs.add(karte);
					sZaehler++;
				}
				if(karte.getTyp().equals("Reiter") && rZaehler == 0) {
					removeIDs.add(karte);
					rZaehler++;
				}
				if(karte.getTyp().equals("Kanone") && kZaehler == 0) {
					removeIDs.add(karte);
					kZaehler++;
				}
			}
			
			for (Einheitenkarte deleteKarte : removeIDs){
				  karten.remove(deleteKarte);
			}
			
		} else {
			throw new NichtGenugKartenFuerAktionException();
		}
		return einheitenkartenBonusErhoehen(spieler);

	}

	private int einheitenkartenBonusErhoehen(Spieler spieler) {
		int kartenTauschBonus = spielVW.getKartenTauschBonus();
		if(kartenTauschBonus < 12) {
			spielVW.erhoeheKartenTauschBonus(2);
		} else if(kartenTauschBonus == 12) {
			spielVW.erhoeheKartenTauschBonus(3);
		} else {
			spielVW.erhoeheKartenTauschBonus(5);
		}
		spieler.berechneVerteilbareEinheiten(kartenTauschBonus);
		return kartenTauschBonus;
	}
	
}
