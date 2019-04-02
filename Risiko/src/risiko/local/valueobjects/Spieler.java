package risiko.local.valueobjects;

public class Spieler {
	private String name;
	private int aktuelleLaender;

	
	public Spieler(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
}

