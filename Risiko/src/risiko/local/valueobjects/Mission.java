package risiko.local.valueobjects;

public class Mission {

	private String beschreibung;
	private boolean erfuellt;
	
	public Mission(String beschreibung) {
		this.beschreibung = beschreibung;
		setErfuellt(false);
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public boolean isErfuellt() {
		return erfuellt;
	}

	public void setErfuellt(boolean erfuellt) {
		this.erfuellt = erfuellt;
	}
	
	
}
