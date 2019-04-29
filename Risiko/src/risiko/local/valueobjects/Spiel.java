package risiko.local.valueobjects;

import java.io.Serializable;
import java.util.Vector;

public class Spiel implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Vector <Spieler> spielerliste;
	private Vector <Provinz> provinzenListe;
	private int spielerID;
	
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
}
