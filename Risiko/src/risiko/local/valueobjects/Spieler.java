package risiko.local.valueobjects;

public class Spieler {
	private String name;
	private int aktuelleLaender;
	private int verteilbareEinheiten;

	
	public Spieler(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

	public int getVerteilbareEinheiten() {
		return verteilbareEinheiten;
	}

	public void setVerteilbareEinheiten(int verteilbareEinheiten) {
		this.verteilbareEinheiten = verteilbareEinheiten;
	}

	public int getAktuelleLaender() {
		return aktuelleLaender;
	}

	public void setAktuelleLaender(int aktuelleLaender) {
		this.aktuelleLaender = aktuelleLaender;
	}

	public void berechneVerteilbareEinheiten(int aenderungsWert) {
		verteilbareEinheiten += aenderungsWert;
	}
	
}

