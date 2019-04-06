package risiko.local.valueobjects;

public class Armee {
	
	private Spieler besitzer;
	private Provinz provinz;
	private int armeeGroesse;
	
	public Armee(Provinz provinz) {
		this.provinz = provinz;
		armeeGroesse = 1;
	}
	
	public Spieler getBesitzer() {
		return besitzer;
	}
	
	public void setBesitzer(Spieler besitzer) {
		this.besitzer = besitzer;
	}
	
	public Provinz getProvinz() {
		return provinz;
	}
	
	public int getArmeeGroesse() {
		return armeeGroesse;
	}
	
	public void setArmeeGroesse(int groesse) {
		if(groesse>0) {
			armeeGroesse = groesse;
		}
		//else Exception
	}
	
	public void vergroessereArmee(int anzahl) {
		armeeGroesse += anzahl;
	}
	
	public void verkleinereArmee(int anzahl) {
		if(anzahl < armeeGroesse) {
			armeeGroesse -= anzahl;
		}
		//else Exception
	}
	
}

//Verbindung Spiele - Provinz