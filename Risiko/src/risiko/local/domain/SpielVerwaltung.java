package risiko.local.domain;

import java.util.Random;
import java.util.Vector;

import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spiel;
import risiko.local.valueobjects.Spieler;

public class SpielVerwaltung {
	
	public SpielVerwaltung() {
		
	}
		
	public void erstelleNeuesSpiel() {
		Spiel spiel = new Spiel();
	}
	
	public int spielVorbereiten(Vector<Provinz> provinzListe, Vector<Spieler> spielerListe) {
		Random rand = new Random();
		int anzahlSpieler = spielerListe.size();
		int provinzenProSpieler = 42 / anzahlSpieler;
		int resteProvinzen = 42 % anzahlSpieler;
		
		Vector<Provinz> hilfsVector = (Vector<Provinz>)provinzListe.clone();
		Provinz zufallsProvinz;
		int zufall;
		int zaehler = 0;
		//verteilen der Karten/Provinzen, die ohne Rest verteilbar sind
		for(int i = 0; i < anzahlSpieler; i++) {
			for(int j = 0; j < provinzenProSpieler; j++) {
				zufall = rand.nextInt(42-zaehler);
				zufallsProvinz = hilfsVector.get(zufall);
				zufallsProvinz.erstelleEinheit(spielerListe.get(i));
				hilfsVector.remove(zufall);
				zaehler++;
			}
		}
		//verteilen der restlichen Karten/Provinze
		int i = 0;
		while(!hilfsVector.isEmpty()) {
			zufall = rand.nextInt(hilfsVector.size());
			zufallsProvinz = hilfsVector.get(zufall);
			zufallsProvinz.erstelleEinheit(spielerListe.get(i));
			hilfsVector.remove(zufall);
			i++;
		}
		
		//Index der Spielerliste, ab dem Spieler Bonuseinheiten bekommen(anderen haben 1 Provinz mehr)
		return resteProvinzen;
	}

	public void neueEinheitenSetzen(Provinz provinz, int anzahlEinheiten) {
		for(int i = 0; i < anzahlEinheiten; i++) {
			provinz.erstelleEinheit(provinz.getBesitzer());
		}
	}
	
}
