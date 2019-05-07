package risiko.local.valueobjects;

import java.io.Serializable;

public class Einheitenkarte implements Serializable {
	
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
