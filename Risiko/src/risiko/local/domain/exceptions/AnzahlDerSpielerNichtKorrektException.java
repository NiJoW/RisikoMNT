package risiko.local.domain.exceptions;


public class AnzahlDerSpielerNichtKorrektException extends Exception {

	private String message;
	
	public AnzahlDerSpielerNichtKorrektException() {
		super("Anzahl der Spieler muss zwischen 2 und 6 liegen");
	}
}
