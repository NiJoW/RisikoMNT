package risiko.local.valueobjects;

public class Spieler {
	private String name;
	private int anzahlAktuelleLaender;
	private int verteilbareEinheiten;
	private Mission mission;

	
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

	public int getAnzahlAktuelleLaender() {
		return anzahlAktuelleLaender;
	}

	public void berechneAktuelleLaender(int aktuelleLaender) {
		this.anzahlAktuelleLaender += aktuelleLaender;
	}

	public void berechneVerteilbareEinheiten(int aenderungsWert) {
		verteilbareEinheiten += aenderungsWert;
	}
	
}

