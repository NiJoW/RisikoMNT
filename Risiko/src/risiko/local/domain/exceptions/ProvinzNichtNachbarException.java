package risiko.local.domain.exceptions;

public class ProvinzNichtNachbarException extends Exception {

	public ProvinzNichtNachbarException(String aktion) {
		super(aktion + ": Diese Aktion kann nur auf benachbarte Provinzen angewandt werden.");
	}
}
