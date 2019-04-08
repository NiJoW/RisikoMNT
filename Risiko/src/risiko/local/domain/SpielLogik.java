package risiko.local.domain;
import java.util.Random;
import java.util.Vector;

import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;
import risiko.local.valueobjects.Welt;


public class SpielLogik {
	
	
	public SpielLogik() {
	}

	public void spielStarten() { //Vector<Provinz> provinzListe, Vector<Spieler> spielerListe
		
	}

	public boolean validiereProvinzID(int provinzID, int spielerID, Provinz provinz, Spieler spieler) {
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
			if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer())) && welt.getBeziehung(from, to)) {
			return true;
		}
		return false;
	}
	
	
	
	public int[] wuerfeln(int anzahlEinheiten, Provinz from, Provinz to) {
		Random rand = new Random();
		int verteidigendeEinheiten = 1;
		if(to.getArmeeGroesse() >= 2) {
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
	
	public void angriffAuswerten(int[] wuerfelErgebnisse, Provinz from, Provinz to, int anzahlEinheiten) {
		int angreiferArray[] = new int[anzahlEinheiten];
		int verteidigerArray[] = new int[wuerfelErgebnisse.length - anzahlEinheiten];
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
		//Arrays auswerten
		if(angreiferArray[0] > verteidigerArray[0]) {
			//Bei gleichem Ergebniss gewinnt der Verteidiger
			angreiferGewonnen++;
		} else {
			verteidigerGewonnen++;
		}
		
		if(anzahlEinheiten > 1 && verteidigerArray.length > 1) {
			//2 W�rfel �berpr�fen
			if(angreiferArray[1] > verteidigerArray[1]) {
				angreiferGewonnen++;
			} else {
				verteidigerGewonnen++;
			} 	
		}
		aendereEinheiten(from, to, angreiferGewonnen, verteidigerGewonnen, anzahlEinheiten );
	}
	
	private void aendereEinheiten(Provinz from, Provinz to, int angreiferGewonnen, int verteidigerGewonnen, int anzahl) {
		while(angreiferGewonnen > 0) {
			to.verkleinereArmee(1);
			if(to.getArmeeGroesse() == 0) {
				einruecken(from, to, anzahl-verteidigerGewonnen);
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
	
	
	public boolean kannVerschieben(int from, int to, Vector<Provinz> pListe, Welt welt) {
		//kann nicht verschieben wenn Besitzer der Provinzen verschieden
		if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer()))) {
			return false;
		} else if(welt.getBeziehung(from, to)) {
			//wenn direkte Nachbarn und gleiche Besitzer -> kann verschieben 
			return true;
		} else {
			for(int dTo = 0; dTo < welt.getBeziehungsMatrix().length; dTo++) {
				if(welt.getBeziehung(from, dTo) && kannVerschieben(dTo, to, pListe, welt)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	

	public void verschiebe(int anzahlEinheiten, Provinz fromProvinz, Provinz toProvinz) {
		fromProvinz.verschiebeEinheitenNach(anzahlEinheiten, toProvinz);
	}
	
}
