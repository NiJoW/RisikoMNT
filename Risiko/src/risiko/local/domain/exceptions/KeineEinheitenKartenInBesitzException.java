package risiko.local.domain.exceptions;

public class KeineEinheitenKartenInBesitzException extends Exception {

	public KeineEinheitenKartenInBesitzException() {
		super("Du besitzt keine Einheitenkarten.");
	}
	
}
