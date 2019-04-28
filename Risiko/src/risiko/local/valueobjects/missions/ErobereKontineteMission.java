package risiko.local.valueobjects.missions;

import java.util.Vector;

import risiko.local.valueobjects.Kontinent;

public class ErobereKontineteMission extends Mission {
	int kontinent1;
	int kontinent2;
	int kontinent3 = 6;
	String beschreibung;
	Vector<Kontinent>kontinentListe;

	public ErobereKontineteMission(Vector<Kontinent>kontinentListe, int kontinent1, int kontinent2, int kontinent3) {
		this.kontinent1 = kontinent1;
		this.kontinent2 = kontinent2;
		this.kontinent3 = kontinent3;
		this.kontinentListe = kontinentListe;
		setBeschreibung();
	}
	
	
	
	public ErobereKontineteMission(Vector<Kontinent>kontinentListe, int kontinent1, int kontinent2) {
		this.kontinent1 = kontinent1;
		this.kontinent2 = kontinent2;
		this.kontinentListe = kontinentListe;
		setBeschreibung();
	}
	
	
	
	@Override
	public boolean isErfuellt() {
		// TODO Auto-generated method stub
		return false;
	}


	private void setBeschreibung() {
		beschreibung = "Erobere diese Kontinente: "+kontinentListe.get(kontinent1).getName()+", "+kontinentListe.get(kontinent2).getName();
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
