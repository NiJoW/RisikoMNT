package risiko.local.domain.exceptions;

public class NichtProvinzDesSpielersExceptions extends Exception{
	
	public NichtProvinzDesSpielersExceptions() {
		super("Diese Provinz gehört dir nicht.");
	}
}
