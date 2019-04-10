package risiko.local.domain.exceptions;

public class ProvinzIDExistiertNichtException extends Exception {

	public ProvinzIDExistiertNichtException() {
		super("Zu dieser ID gibt es keine Provinz.");
	}
	
}
