package risiko.local.domain.exceptions;

public class ProvinzIDExistiertNichtException extends Exception {

	public ProvinzIDExistiertNichtException() {
		super("Zu dieser ID gibt es keine Provinz. ID muss eine ganze Zahl zwischen 0 und 41 sein.");
	}
	
}
