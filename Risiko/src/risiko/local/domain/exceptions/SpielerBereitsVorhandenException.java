package risiko.local.domain.exceptions;

public class SpielerBereitsVorhandenException extends Exception {

	public SpielerBereitsVorhandenException() {
		super("Es gibt bereits einen Spieler mit diesem Namen.");
	}
}
