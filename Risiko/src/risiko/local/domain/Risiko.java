package risiko.local.domain;


public class Risiko {
	private SpielerVerwaltung spielerVW;
	private WeltVerwaltung weltVW;
	private SpielLogik spiellogik;
	private SpielVerwaltung spielVW;
	
	
	public Risiko() {
		spielerVW = new SpielerVerwaltung();
		weltVW = new WeltVerwaltung();
		spiellogik = new SpielLogik();
		spielVW = new SpielVerwaltung();
	}
	
	public void spielerHinzufuegen(String name) {
		spielerVW.spielerHinzufuegen(name);
	}
	
	public void gibSpielerlisteAus() {
		spielerVW.gibSpielerlisteAus();
	}
	
	public void spielStarten() {
		spielVW.erstelleNeuesSpiel();
		weltVW.erstelleWelt();
	}
}

