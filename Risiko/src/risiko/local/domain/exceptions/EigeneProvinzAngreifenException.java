package risiko.local.domain.exceptions;

public class EigeneProvinzAngreifenException extends Exception {

	public EigeneProvinzAngreifenException() {
		super("Du kannst deine eigene Provinz nicht angreifen.");
	}
}
