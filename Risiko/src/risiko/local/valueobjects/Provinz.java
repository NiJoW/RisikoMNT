package risiko.local.valueobjects;

import java.util.Vector;

public class Provinz {

	private String name;
	private Vector<Einheit> armee;
	private int id; //ID statt K�rzel gew�hlt
	
	
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
		if(armee.size()==1) { //eine Einheit muss stehen bleiben
			return 0;
		}
		for(Einheit einheit : armee) { // involvierte Einheiten nicht verschiebbar
			if(!einheit.isInvolviert()) {
				anzahl++;
			}
		}
		if(armee.size()==anzahl) { //eine Einheit muss stehen bleiben
			anzahl--;
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
			einheit.setProvinz(to);
			to.addEinheit(einheit);
			armee.remove(einheit);
		}
	}

	public String getName() {
		return name;
	}

	public void setInvolvierteEinheiten(int anzahl) {
		for(int i=0; i<anzahl; i++) {
			armee.get(i).setInvolviert(true);
		}
	}

	public void resetInvolvierteEinheiten() {
		for(Einheit einheit : armee) {
			einheit.setInvolviert(false);
		}
	}
	
}
