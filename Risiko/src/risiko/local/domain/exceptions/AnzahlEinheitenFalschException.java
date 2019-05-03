package risiko.local.domain.exceptions;

public class AnzahlEinheitenFalschException extends Exception{

	public AnzahlEinheitenFalschException(int verschiebbareEinheiten) {
		super("Du kannst maximal " + verschiebbareEinheiten + " Einheiten aus dieser Provinz verschieben.");
	}
	public AnzahlEinheitenFalschException(String message) {
		super(message);
	}
}
