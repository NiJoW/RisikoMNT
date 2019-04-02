package risiko.local.domain;

import java.util.List;
import java.util.Vector;
import risiko.local.valueobjects.Spieler;

public class SpielerVerwaltung {
	private List<Spieler> spielerliste;
	
	public SpielerVerwaltung() {
		spielerliste = new Vector<Spieler>();
	}

	public void spielerHinzufuegen(String name) {
		spielerliste.add(new Spieler(name));
	}
	
	public void gibSpielerlisteAus() {
		for(int j=0; j<spielerliste.size(); j++) {
			System.out.println(j + ":  " + ((Vector<Spieler>) spielerliste).elementAt(j));
		}
		
	}
}
