package risiko.local.domain.exceptions;

public class SpielerNichtTeilDesSpielsException extends Exception {

	public  SpielerNichtTeilDesSpielsException() {
		super("Einer der angemeldeten Spieler hat nicht bei dem gespeichertem Spiel mitgespielt. \nBitte einen anderen Spielstand waehlen:\n");
	}
}
