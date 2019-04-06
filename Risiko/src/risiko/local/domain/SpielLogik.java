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

	
	
	private boolean kannAngreifen(Provinz proFrom, Provinz proTo) {
		if(!(proFrom.getBesitzer().equals(proTo.getBesitzer()))   ) { // && getBeziehungen
			return true;
		}
		return false;
	}
	
	public void angreifen() {
		// TODO Auto-generated method stub
		
	}

	
	public boolean kannVerschieben(int from, int to, Vector<Provinz> pListe, Welt welt) {
		//kann nicht verschieben wenn Besitzer der Provinzen verschieden
		if(!(pListe.get(from).getBesitzer().equals(pListe.get(to).getBesitzer()))) {
			return false;
		} else if(welt.getBeziehung(from, to)) {
			//wenn direkte Nachbarn und gleiche Besitzer -> kann verschieben 
			return true;
		}
		for(int dTo = 0; dTo < welt.getBeziehungsMatrix().length; dTo++) {
			if(welt.getBeziehung(from, dTo) && kannVerschieben(dTo, to, pListe, welt) {
				return true;
			}
		}
	}

	public void verschiebe(int anzahlEinheiten, Provinz fromProvinz, Provinz toProvinz) {
		fromProvinz.berechneArmeeGroesse(-anzahlEinheiten);
		toProvinz.berechneArmeeGroesse(anzahlEinheiten);
	}
	
}
