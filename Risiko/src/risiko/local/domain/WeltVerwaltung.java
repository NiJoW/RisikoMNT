package risiko.local.domain;

import java.util.Vector;

import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Welt;

public class WeltVerwaltung {

	private Welt welt;
	
	public WeltVerwaltung() {
		
	}
	
	public void erstelleWelt() {
		welt = new Welt();
	}
	
	public Vector<Provinz> getProvinzListe() {
		return welt.getProvinzListe();
	}
	
}
