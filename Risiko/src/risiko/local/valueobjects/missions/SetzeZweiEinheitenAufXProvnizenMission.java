package risiko.local.valueobjects.missions;

import java.util.Vector;

import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;

public class SetzeZweiEinheitenAufXProvnizenMission extends Mission {

	private int zuEroberneProvinzen;
	private String beschreibung;
	private Vector<Provinz> provinzenListe;
	
	
	public SetzeZweiEinheitenAufXProvnizenMission(int zuEroberneProvinzen, Vector<Provinz>provinzenListe) {
		this.zuEroberneProvinzen = zuEroberneProvinzen;
		this.provinzenListe = provinzenListe;
		this.beschreibung = "Setze mindestens 2 Einheiten auf " + zuEroberneProvinzen + " Provinzen deiner Wahl!";
	}
	
	
	@Override
	public boolean isErfuellt(Spieler spieler) {
		int zaehler = 0;
		for(Provinz provinz: provinzenListe) {
			if(provinz.getBesitzer().equals(spieler) && provinz.getArmeeGroesse() >= 2) {
				zaehler++;
			}
		}
		if(zaehler >= zuEroberneProvinzen) {
			return true;
		}
		return false;
	}


	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

}
