package risiko.local.valueobjects;

import java.util.Vector;

public class Provinz {

	private String name;
	private Vector<Einheit> armee;
	private int id; //ID statt Kürzel gewählt
	
	
	public Provinz(String name, int id) {
		this.name = name;
		this.id = id;
		armee = new Vector<Einheit>();
	}
	
	public void erstelleEinheit(Spieler spieler) {
		armee.add(new Einheit(this, spieler));
	}
	
	public void addEinheit(Einheit einheit) {
		armee.add(einheit);
	}
	
	public Spieler getBesitzer() {
		return armee.get(0).getBesitzer();
	}
	
	public int getArmeeGroesse() {
		return armee.size();
	}
	
	public int getAnzahlVerschiebbareEinheiten() {
		int anzahl = 0;
		for(Einheit einheit : armee) {
			if(!einheit.isInvolviert()) {
				anzahl++;
			}
		}
		return anzahl;
	}
	
	public String toString() {
		return id+") "+name+ " -> Einheiten: "+armee.size();
	}

	public void verkleinereArmee(int anzahl) {
		armee.remove(0);
	}

	public void verschiebeEinheitenNach(int anzahl, Provinz to) {
		Einheit einheit;
		for(int a = 0; a < anzahl; a++) {
			einheit = armee.get(a);
			einheit.setInvolviert(true);
			to.addEinheit(einheit);
			armee.remove(einheit);
		}
	}
	
}
