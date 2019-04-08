package risiko.local.valueobjects;

import java.util.List;

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
	
	
}

