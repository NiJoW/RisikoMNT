package risiko.local.domain.exceptions;

public class SpielerBereitsVorhandenException extends Exception {

	private String message;
	
	public SpielerBereitsVorhandenException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
