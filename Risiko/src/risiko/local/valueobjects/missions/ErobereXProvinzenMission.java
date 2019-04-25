package risiko.local.valueobjects.missions;

public class ErobereXProvinzenMission extends Mission {

	private int zuEroberneProvinzen;
	private int einheitenAnzahlProProvinz;
	
//	public ErobereXProvinzenMission(int zuEroberneProvinzen) {
//		this.zuEroberneProvinzen = zuEroberneProvinzen;
//	}
//	
	
	// einheitenAnzahlProProvinz 1 oder 2 für verschiedene Missionen
	public ErobereXProvinzenMission(int zuEroberneProvinzen, int einheitenAnzahlProProvinz) {
		this.zuEroberneProvinzen = zuEroberneProvinzen;
		this.einheitenAnzahlProProvinz = einheitenAnzahlProProvinz;
	}
	
	
	@Override
	public boolean isErfuellt() {
		// TODO Auto-generated method stub
		return false;
	}

}
