package risiko.local.domain;

import java.util.Vector;

import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.valueobjects.Spieler;

public class SpielerVerwaltung {
	private Vector<Spieler> spielerliste;
	
	public SpielerVerwaltung() {
		spielerliste = new Vector<Spieler>();
	}

	
	
	//---------------------- SPIELERLISTE ------------------------	
	
	
	public Vector<Spieler> getSpielerListe() {
		return (Vector<Spieler>) spielerliste;
	}
	
	public int getSpielerAnzahl() {
		return spielerliste.size();
	}
	
	
	
	//---------------------- SPIELER ------------------------	
	
	
	
	public void spielerHinzufuegen(String name) {
		spielerliste.add(new Spieler(name));
	}
	
	public boolean spielerBereitsVorhanden(String name) throws SpielerBereitsVorhandenException {
		for (Spieler spieler : spielerliste) {
			if(spieler.getName().equals(name)) {
				throw new SpielerBereitsVorhandenException("Es gibt bereits einen Spieler mit diesem Namen.");
			}
				
		}
		return false;
	}

	public Spieler getSpieler(int id) {
		return spielerliste.get(id);
	}

	public String getSpielerName(int id) {
		return spielerliste.get(id).getName();
	}
	
	
	
	//---------------------- EINHEITEN DES SPIELERS ------------------------	

	
	
	public int getVerteilbareEinheiten(int id) {
		return spielerliste.get(id).getVerteilbareEinheiten();
	}

	public void weiseEinheitenZu(int bonusAbSpieler) {
		for(int s = 0; s < spielerliste.size(); s++) {
			if(s >= bonusAbSpieler) {
				spielerliste.get(s).setVerteilbareEinheiten(6);
			} else {
				spielerliste.get(s).setVerteilbareEinheiten(5);
			}
		}
	}

	public void berechneVerteilbareEinheiten(int aenderungsWert, int id) {
		spielerliste.get(id).berechneVerteilbareEinheiten(aenderungsWert);
	}



	public String getMission(int spielerID) {
		return spielerliste.get(spielerID).getMissionsBeschreibung();
	}

	//---------------------- LADEN ------------------------	

	public void ladeSpielerliste(Vector<Spieler> spielerliste) {
		this.spielerliste = spielerliste;
	}



	public void resetProvinzErobert(Spieler spieler) {
		spieler.setProvinzErobert(false);
	}

}
