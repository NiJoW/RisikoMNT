package risiko.local.valueobjects.missions;

import risiko.local.valueobjects.Spieler;

public class EliminiereEinenSpielerMission extends Mission {

	private Spieler target;
	private String beschreibung;
	
	public EliminiereEinenSpielerMission() {
		
	}
	
	public void setTarget(Spieler target) {
		this.target = target;
		setBeschreibung(target.getName());
	}
	
	private void setBeschreibung(String target) {
		beschreibung = "Eliminiere den Spieler " + target + "!";
	}

	@Override
	public boolean isErfuellt(Spieler spieler) {
		if(target.getAnzahlAktuelleLaender() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public String getBeschreibung()  {
		return beschreibung;
	}
	
}
