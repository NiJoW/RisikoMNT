package risiko.local.valueobjects.missions;

import java.io.Serializable;

import risiko.local.valueobjects.Spieler;

public abstract class Mission implements Serializable {
	
	public Mission() {}

	public abstract String getBeschreibung();
	
	public abstract boolean isErfuellt(Spieler spieler);
	
}
