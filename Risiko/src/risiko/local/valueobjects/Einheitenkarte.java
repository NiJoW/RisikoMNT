package risiko.local.valueobjects;

public class Einheitenkarte {
	
	private String typ;
	
	public Einheitenkarte(String typ) {
		this.typ = typ;
	}

	public String getTyp() {
		return typ;
	}
	
	public String toString( ) {
		return "Einheitenkarte: Typ -> " + typ;
	}
	
}
