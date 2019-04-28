package risiko.local.valueobjects.missions;

import risiko.local.valueobjects.Spieler;

public class ErobereXProvinzenMission extends Mission {

	private int zuEroberneProvinzen;
	private String beschreibung;
	
	
	public ErobereXProvinzenMission(int zuEroberneProvinzen) {
		this.zuEroberneProvinzen = zuEroberneProvinzen;
		this.beschreibung = "Erobere " + zuEroberneProvinzen + " Provinzen!";	
	}
	
	
	
	@Override
	public boolean isErfuellt(Spieler spieler) {
		if(spieler.getAnzahlAktuelleLaender() >= zuEroberneProvinzen) {
			return true;
		}
		return false;
	}



	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

}
