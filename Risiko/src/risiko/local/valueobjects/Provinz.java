package risiko.local.valueobjects;

public class Provinz {

	private String name;
	private Armee armee;
	//Kürzel?
	
	
	public Provinz(String name) {
		this.name = name;
		armee = new Armee(this); 
	}
	
	public void setBesitzer(Spieler besitzer) {
		armee.setBesitzer(besitzer);
	}
	
}
