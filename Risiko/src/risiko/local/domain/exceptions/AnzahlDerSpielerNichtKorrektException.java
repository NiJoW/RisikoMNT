package risiko.local.domain.exceptions;


public class AnzahlDerSpielerNichtKorrektException extends Exception {

	private String message;
	
	public AnzahlDerSpielerNichtKorrektException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
