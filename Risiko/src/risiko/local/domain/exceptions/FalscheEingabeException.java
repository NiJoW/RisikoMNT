package risiko.local.domain.exceptions;

public class FalscheEingabeException extends Exception {
	
	
	public  FalscheEingabeException() {
		super("\nEingabe fehlerhaft! \nBitte waehle eine der folgenden Optionen:");
	}
}
