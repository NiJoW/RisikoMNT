package risiko.local.domain.exceptions;

public class NichtProvinzDesSpielersException extends Exception{
	
	public NichtProvinzDesSpielersException() {
		super("Diese Provinz gehört dir nicht.");
	}
}
