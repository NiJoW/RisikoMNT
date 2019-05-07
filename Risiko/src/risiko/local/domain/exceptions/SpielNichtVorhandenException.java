package risiko.local.domain.exceptions;

public class SpielNichtVorhandenException extends Exception {
	
	public  SpielNichtVorhandenException() {
		super("Dieser Spielstand existiert nicht. \nBitte waehle eine der folgenden Optionen:");
	}
}
