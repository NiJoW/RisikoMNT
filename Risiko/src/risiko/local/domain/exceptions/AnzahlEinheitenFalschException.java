package risiko.local.domain.exceptions;

public class AnzahlEinheitenFalschException extends Exception{

	public AnzahlEinheitenFalschException(int einheiten) {
		super("Du musst mehr als 0 und maximal " + einheiten + " Einheiten nutzen.");
	}
	public AnzahlEinheitenFalschException(String message) {
		super(message);
	}
}
