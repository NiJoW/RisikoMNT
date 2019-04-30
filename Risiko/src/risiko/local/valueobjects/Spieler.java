package risiko.local.valueobjects;

import java.io.Serializable;

import risiko.local.valueobjects.missions.Mission;

public class Spieler implements Serializable{
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

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public String getMissionsBeschreibung() {
		return mission.getBeschreibung();
	}
	
}

