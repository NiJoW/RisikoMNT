package risiko.local.domain.exceptions;

public class TauschenNichtMoeglichException extends Exception{
	
	public TauschenNichtMoeglichException() {
		super("Du kannst keine Einheitenkarten eintauschen.");
	}
}
