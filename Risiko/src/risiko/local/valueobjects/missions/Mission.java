package risiko.local.valueobjects.missions;

import risiko.local.valueobjects.Spieler;

public abstract class Mission {
	
	public Mission() {
	}

	public abstract String getBeschreibung();
	
	public abstract boolean isErfuellt(Spieler spieler);
	
}
