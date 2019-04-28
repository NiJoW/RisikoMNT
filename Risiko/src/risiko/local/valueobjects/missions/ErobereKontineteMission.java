package risiko.local.valueobjects.missions;

import java.util.Vector;

import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Spieler;

public class ErobereKontineteMission extends Mission {
	Kontinent kontinent1;
	Kontinent kontinent2;
	int kontinent3 = 6;
	String beschreibung;
	Vector<Kontinent>kontinentListe;

	public ErobereKontineteMission(Vector<Kontinent>kontinentListe, int kontinent1, int kontinent2, int kontinent3) {
		this.kontinentListe = kontinentListe;
		this.kontinent1 = kontinentListe.get(kontinent1);
		this.kontinent2 = kontinentListe.get(kontinent2);
		this.kontinent3 = kontinent3;
		setBeschreibung();
	}
	
	
	
	public ErobereKontineteMission(Vector<Kontinent>kontinentListe, int kontinent1, int kontinent2) {
		this.kontinentListe = kontinentListe;
		this.kontinent1 = kontinentListe.get(kontinent1);
		this.kontinent2 = kontinentListe.get(kontinent2);
		setBeschreibung();
	}
	
	
	
	@Override
	public boolean isErfuellt(Spieler spieler) {
		
		//Spieler beherrst nicht beide Kontinente 1 und 2
		if(!(kontinent1.isHerrscher(spieler) && kontinent2.isHerrscher(spieler))) {
			return false;
		}
		if(kontinent3==6) { //Spieler braucht nur 2 Kontinente und beherrst diese
			return true;
		}
		// Spieler beherrst vorgegebenen dritten Kontinent
		if(kontinent3 != -1 && kontinentListe.get(kontinent3).isHerrscher(spieler)) {
			return true;
		}
		//Spieler beherrst einen selbst gewählten dritten Kontinent
		if(kontinent3 == -1) {
			for(Kontinent kontinent: kontinentListe) {
				if(!(kontinent.equals(kontinent1) || kontinent.equals(kontinent2))) {
					if(kontinentListe.get(kontinent3).isHerrscher(spieler)) {
						return true;
					}
				}
			}
		}
		//Spieler beherrst nicht einen selbst gewählten dritten Kontinent
		return false;
	}

	
	
	public void setBeschreibung() {
		beschreibung = "Erobere diese Kontinente: "+kontinent1.getName()+", "+kontinent2.getName();
		if(kontinent3 == -1) {
			beschreibung += " und einen Kontinent deiner Wahl";
		} else if(kontinent3 != 6) {
			beschreibung += " und "+kontinentListe.get(kontinent3).getName();
		}
	}

	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

}
