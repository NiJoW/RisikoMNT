package risiko.local.valueobjects;

public class Einheit {
	
	private Spieler besitzer;
	private Provinz provinz;
	private boolean involviert;
	
	public Einheit(Provinz provinz, Spieler spieler) {
		this.provinz = provinz;
		besitzer = spieler;
		setInvolviert(false);
	}
	
	public Spieler getBesitzer() {
		return besitzer;
	}
	
	public Provinz getProvinz() {
		return provinz;
	}

	public boolean isInvolviert() {
		return involviert;
	}

	public void setInvolviert(boolean involviert) {
		this.involviert = involviert;
	}

	public void setProvinz(Provinz provinz) {
		this.provinz = provinz;
	}
}
//Verbindung Spieler - Provinz