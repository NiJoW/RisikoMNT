package risiko.local.domain;
import java.util.Random;
import java.util.Vector;

import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;
import risiko.local.valueobjects.Welt;


public class SpielLogik {
	
	private WeltVerwaltung weltVW;
	private SpielerVerwaltung spielerVW;
	
	public SpielLogik(WeltVerwaltung weltVW, SpielerVerwaltung spielerVW) {
		this.weltVW = weltVW;
		this.spielerVW = spielerVW;
	}
	
	public void kannAngreifen(int from, int to) throws ProvinzNichtNachbarException {	
		Welt welt = weltVW.getWelt();
		
		if(!welt.isNachbar(from, to)) {
			throw new ProvinzNichtNachbarException("Du kannst nur benachbarte Provinzen angreifen.");
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
		
		
		//Arrays trennen
		for(int i = 0; i < anzahlEinheiten; i++) {
			angreiferArray[i]  =  wuerfelErgebnisse[i];
		}
		int l = 0;
		for(int j = anzahlEinheiten; j < wuerfelErgebnisse.length; j++) {
			verteidigerArray[l]  =  wuerfelErgebnisse[j];
			l++;
		}
		//Arrays sortieren
		angreiferArray = sortiereArray(angreiferArray);
		verteidigerArray = sortiereArray(verteidigerArray);
		
		int angreiferGewonnen = 0;
		int verteidigerGewonnen = 0;
		ergebnis[0][0] = angreiferArray[0]+"";
		ergebnis[0][1] = verteidigerArray[0]+"";
		//Arrays auswerten
		if(angreiferArray[0] > verteidigerArray[0]) {
			//Bei gleichem Ergebniss gewinnt der Verteidiger
			angreiferGewonnen++;
			ergebnis[0][2] = to.getBesitzer().getName(); //Verlierer
		} else {
			verteidigerGewonnen++;
			ergebnis[0][2] = from.getBesitzer().getName();
		}
		
		if(anzahlEinheiten > 1 && verteidigerArray.length > 1) {
			//2 W�rfel �berpr�fen
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
	
	private void aendereEinheiten(Provinz from, Provinz to, int angreiferGewonnen, int verteidigerGewonnen, int anzahl) {
		while(angreiferGewonnen > 0) {
			if((to.getArmeeGroesse()-1 == 0)) {
				to.getBesitzer().berechneAktuelleLaender(-1);
				from.getBesitzer().berechneAktuelleLaender(1);
			}
			to.verkleinereArmee(1);
			if(to.getArmeeGroesse() == 0) {
				einruecken(from, to, anzahl-verteidigerGewonnen);
				//L�nderkarte verteilen
			}
			angreiferGewonnen--;
		}
		
		while(verteidigerGewonnen > 0) {
			from.verkleinereArmee(1);
			verteidigerGewonnen--;
		}
		
	}
	
	private void einruecken(Provinz from, Provinz to, int anzahlEinheiten) {
		from.verschiebeEinheitenNach(anzahlEinheiten, to);
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
	
	
	public void kannVerschieben(int from, int to, int anzahlEinheiten) throws AnzahlEinheitenFalschException, NichtProvinzDesSpielersException, ProvinzNichtNachbarException, ProvinzIDExistiertNichtException {
		Vector<Provinz> pListe = weltVW.getProvinzListe();
		Welt welt = weltVW.getWelt();
		
		
		validiereProvinzID(from);
		validiereProvinzID(to);
		
		int verschiebbareEinheiten = pListe.get(from).getAnzahlVerschiebbareEinheiten();
		if(anzahlEinheiten > verschiebbareEinheiten) {
			throw new AnzahlEinheitenFalschException("Du kannst maximal "+verschiebbareEinheiten+" Einheiten aus dieser Provinz verschieben. \nBereits in den Kampf involvierte Einheiten können nicht mehr verschoben werden.");
		}
			
		
		//kann nur in eigene Provinzen Einheiten verschieden
		if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer()))) {
			throw new NichtProvinzDesSpielersException();
		} 
		if(!(welt.isNachbar(from, to))) {
			//nicht direkte Nachbarn (wenn auch gleiche Besitzer) -> kann nicht verschieben
			throw new ProvinzNichtNachbarException("Einheiten können nur in benachbarte Provinzen verschoben werden.");	
		}
// verschieben über mehrere eigene Länder:  prüft transitive Beziehung
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
			throw new ProvinzIDExistiertNichtException();
		}
	}

	public void verschiebe(int anzahlEinheiten, int fromProvinz, int toProvinz) {
		Provinz from = weltVW.getProvinz(fromProvinz);
		Provinz to = weltVW.getProvinz(toProvinz);
		
		from.verschiebeEinheitenNach(anzahlEinheiten, to);
	}

	public int berechneNeueEinheiten(int spielerID) {
		Vector<Kontinent> kontinentListe = weltVW.getKontinentListe();
		Spieler spieler = spielerVW.getSpieler(spielerID);
		int anzahl = spieler.getAnzahlAktuelleLaender()/3;
		if(anzahl < 3) {
			anzahl = 3;
		}
		anzahl += getKontinentBonus(spieler, kontinentListe);
			
		return anzahl;
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

	public String einerHatGewonnen(int id) {
		Spieler spieler = spielerVW.getSpieler(id);
		if(spieler.getAnzahlAktuelleLaender() == 42) {
			return spieler.getName();
		}
		return "";
	}

	public boolean kannEinheitenNachruecken(int spielerID, int fromProvinz) {
		Vector<Provinz> pListe = weltVW.getProvinzListe();
		int verschiebbareEinheiten = pListe.get(fromProvinz).getAnzahlVerschiebbareEinheiten();
		
		if(verschiebbareEinheiten > 1) {
			return true;
		}
		return false;
	}

	
	
}
