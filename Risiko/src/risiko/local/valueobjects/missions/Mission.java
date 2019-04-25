package risiko.local.valueobjects.missions;

public abstract class Mission {

	private String beschreibung;
	
	public Mission() {
		
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public abstract boolean isErfuellt();
	
}
