package risiko.local.valueobjects;

public class Provinz {

	private String name;
	private Armee armee;
	//K�rzel?
	
	
	public Provinz(String name) {
		this.name = name;
	}
	
	public void setBesitzer(Spieler besitzer) {
		armee.setBesitzer(besitzer);
	}
	
}
