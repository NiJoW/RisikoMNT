package risiko.local.valueobjects.missions;

public class SetzeZweiEinheitenAufXProvnizenMission extends Mission {

	private int zuEroberneProvinzen;
	private int einheitenAnzahlProProvinz = 2;
	private String beschreibung;
	
	
	public SetzeZweiEinheitenAufXProvnizenMission(int zuEroberneProvinzen) {
		this.zuEroberneProvinzen = zuEroberneProvinzen;
	}
	
	
	@Override
	public boolean isErfuellt() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getBeschreibung() {
		return beschreibung;
	}

}
