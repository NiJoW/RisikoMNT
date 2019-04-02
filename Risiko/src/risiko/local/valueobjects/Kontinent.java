package risiko.local.valueobjects;

import java.util.List;

public class Kontinent {
	
	private List<Provinz> provinzen;
	
	public Kontinent(String name) {
		
	}
	
	public void setProvinzenListe(List<Provinz> provinzen) {
		this.provinzen = provinzen;
	}
}

