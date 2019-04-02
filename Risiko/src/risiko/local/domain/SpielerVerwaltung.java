package risiko.local.domain;

import risiko.local.valueobjects.Spielerliste;
import risiko.local.valueobjects.Spieler;

public class SpielerVerwaltung {
	private Spielerliste spielerliste;
	
	public SpielerVerwaltung() {
		spielerliste = new Spielerliste();
	}

	public void spielerHinzufuegen(String name) {
		spielerliste.add(new Spieler(name));
	}
	
	public void gibSpielerlisteAus() {
		for(int j=0; j<spielerliste.size(); j++) {
			System.out.println(j + ":  " + spielerliste.elementAt(j));
		}
		
	}
}
