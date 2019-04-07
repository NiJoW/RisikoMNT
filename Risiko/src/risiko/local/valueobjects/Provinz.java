package risiko.local.valueobjects;

public class Provinz {

	private String name;
	private Armee armee;
	private int id;
	
	
	public Provinz(String name, int id) {
		this.name = name;
		this.id = id;
		armee = new Armee(this); 
	}
	
	public void setBesitzer(Spieler besitzer) {
		armee.setBesitzer(besitzer);
	}
	
	public Spieler getBesitzer() {
		return armee.getBesitzer();
	}
	
	public String toString() {
		return id+") "+name+ " -> Einheiten: "+armee.getArmeeGroesse();
	}

	public void berechneArmeeGroesse(int anzahl) {
		armee.berechneArmeeGroesse(anzahl);
	}
	
	public int getArmeeGroesse() {
		return armee.getArmeeGroesse();
	}
}
