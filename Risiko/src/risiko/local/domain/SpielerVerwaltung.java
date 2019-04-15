package risiko.local.domain;

import java.util.List;
import java.util.Vector;

import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.valueobjects.Spieler;

public class SpielerVerwaltung {
	private List<Spieler> spielerliste;
	
	public SpielerVerwaltung() {
		spielerliste = new Vector<Spieler>();
	}

	public void spielerHinzufuegen(String name) {
		spielerliste.add(new Spieler(name));
	}

	public int getSpielerAnzahl() {
		return spielerliste.size();
	}

	public Vector<Spieler> getSpielerListe() {
		return (Vector<Spieler>) spielerliste;
	}
	
	public String getSpielerName(int id) {
		return spielerliste.get(id).getName();
	}

	public int getVerteilbareEinheiten(int id) {
		return spielerliste.get(id).getVerteilbareEinheiten();
	}

	public void weiseEinheitenZu(int bonusAbSpieler) {
		for(int s = 0; s < spielerliste.size(); s++) {
			if(s >= bonusAbSpieler) {
				spielerliste.get(s).setVerteilbareEinheiten(11);
			} else {
				spielerliste.get(s).setVerteilbareEinheiten(10);
			}
		}
	}

	public Spieler getSpieler(int id) {
		return spielerliste.get(id);
	}

	public void berechneVerteilbareEinheiten(int aenderungsWert, int id) {
		spielerliste.get(id).berechneVerteilbareEinheiten(aenderungsWert);
	}

	public boolean spielerBereitsVorhanden(String name) throws SpielerBereitsVorhandenException {
		for (Spieler spieler : spielerliste) {
			if(spieler.getName().equals(name)) {
				throw new SpielerBereitsVorhandenException("Es gibt bereits einen Spieler mit diesem Namen.");
			}
				
		}
		return false;
	}
}
