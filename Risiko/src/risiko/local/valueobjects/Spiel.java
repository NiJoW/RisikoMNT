package risiko.local.valueobjects;

import java.io.Serializable;
import java.util.Vector;

public class Spiel implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Vector <Spieler> spielerliste;
	private Welt welt;
	private Vector <Provinz> provinzenListe;
	private Vector<Kontinent> kontinentListe;
	private int spielerID;
	private int kartenTauschBonus;

	
	public Spiel() {
	}

	public void setSpielerliste(Vector <Spieler> spielerliste) {
		this.spielerliste = spielerliste;
	}

	public void setProvinzenListe(Vector <Provinz> provinzenListe) {
		this.provinzenListe = provinzenListe;
	}

	public void setSpielerID(int spielerID) {
		this.spielerID = spielerID;
	}

	public int getSpielerID() {
		// TODO Auto-generated method stub
		return spielerID;
	}

	public Vector<Provinz> getProvinzenListe() {
		return provinzenListe;
	}
	
	public Vector<Spieler> getSpielerListe() {
		return spielerliste;
	}

	public void setKontinentListe(Vector<Kontinent> kontinentListe) {
		this.kontinentListe = kontinentListe;
	}
	
	public Vector<Kontinent> getKontinentListe(){
		return kontinentListe;
	}

	public void setWelt(Welt welt) {
		this.welt = welt;
	}
	
	public Welt getWelt() {
		return welt;
	}

	public int getKartenTauschBonus() {
		return kartenTauschBonus;
	}

	public void setKartenTauschBonus(int kartenTauschBonus) {
		this.kartenTauschBonus = kartenTauschBonus;
	}

	
}
