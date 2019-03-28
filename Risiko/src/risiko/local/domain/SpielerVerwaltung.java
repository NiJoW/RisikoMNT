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
}
