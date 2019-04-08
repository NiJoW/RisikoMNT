package risiko.local.valueobjects;

import java.util.List;
import java.util.Vector;

public class Kontinent {
	
	private List<Provinz> provinzen;
	private String name;
	
	public Kontinent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public List<Provinz> getProvinzen() {
		return provinzen;
	}

	public void setProvinzen(Vector<Provinz> provinzListe) {
		provinzen = provinzListe;
	}
	
	public boolean isHerrscher(Spieler spieler) {
		for(Provinz provinz : provinzen) {
			if(provinz.getBesitzer().equals(spieler)) {
				return false;
			}
		}
		return true;
	}
	
	
}

