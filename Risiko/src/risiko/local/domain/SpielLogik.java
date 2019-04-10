package risiko.local.domain;
import java.util.Random;
import java.util.Vector;

import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;
import risiko.local.valueobjects.Welt;


public class SpielLogik {
	
	private SpielerVerwaltung spielerVW;
	private WeltVerwaltung weltVW;
	
	public SpielLogik(SpielerVerwaltung spielerVW, WeltVerwaltung weltVW) {
		this.spielerVW = spielerVW;
		this.weltVW = weltVW;
	}

	public boolean validiereProvinzID(int provinzID, int spielerID) {
		Provinz provinz = weltVW.getProvinz(provinzID);
		Spieler spieler = spielerVW.getSpieler(spielerID);
		
		if(provinzID > 41 || provinzID < 0) {
			return false;
		}else if(!(provinz).getBesitzer().equals((spieler))){
			return false;
		}
		return true;		
	}
	
	public boolean validiereAnzahlEinheiten(int anzahl, int spielerID, int verteilbareEinheiten) {
		if((anzahl > 0) && verteilbareEinheiten >= anzahl) {
			return true;
		}
		return false;
	}
	
	
	
	
	public boolean kannAngreifen(int from, int to, Vector<Provinz> pListe, Welt welt) {		
			if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer())) && welt.isNachbar(from, to)) {
			return true;
		}
		return false;
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
	
	public boolean validiereZielProvinz(Provinz proFrom, Provinz proTo, Spieler spieler) {
		if(!spieler.equals(proTo.getBesitzer())) {
			
		}
		
		
		return false;
	}
	
	public boolean validiereAnzahlAngreifendeEinheiten(Provinz provinz, Spieler spieler, int anzahlEinheiten) {
		return false;
	}
	
	public String[][] angriffAuswerten(int[] wuerfelErgebnisse, Provinz from, Provinz to, int anzahlEinheiten) {
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
		for (int k = array.length-1; k > 1; k--){
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
	
	
	public boolean kannVerschieben(int from, int to, Vector<Provinz> pListe, Welt welt, int anzahlEinheiten) {
		if(anzahlEinheiten > pListe.get(from).getAnzahlVerschiebbareEinheiten()) {
			//throw excep
			return false;
		}
			
		
		//kann nicht verschieben wenn Besitzer der Provinzen verschieden
		
		if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer()))) {
			return false;
		} else if(welt.isNachbar(from, to)) {
			//wenn direkte Nachbarn und gleiche Besitzer -> kann verschieben 
			return true;
		}
//			else {
//			for(int dTo = 0; dTo < welt.getBeziehungsMatrix().length; dTo++) {
//				if(welt.getBeziehung(from, dTo) && kannVerschieben(dTo, to, pListe, welt)) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		}
		return false;
	}
	

	public void verschiebe(int anzahlEinheiten, Provinz fromProvinz, Provinz toProvinz) {
		fromProvinz.verschiebeEinheitenNach(anzahlEinheiten, toProvinz);
	}

	public int berechneNeueEinheiten(Spieler spieler, Vector<Kontinent> kontinentListe) {
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
	
	
	
}
