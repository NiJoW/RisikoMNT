package risiko.local.domain.exceptions;

public class NichtGenugKartenFuerAktionException extends Exception {
	
	public NichtGenugKartenFuerAktionException() {
		super("Du hast nicht ausreichend Karten dieser Art, um den Tausch durchzufuehren. \nBitte andere Option wählen. \n");
	}
}
