package risiko.local.domain;
import java.util.Random;
import java.util.Vector;

import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;

public class SpielLogik {
	
	
	public SpielLogik() {
	}

	public void spielStarten(Vector<Provinz> provinzListe, Vector<Spieler> spielerListe) {
		spielVorbereiten(provinzListe, spielerListe);
		spielBeginnen();
	}

	private void spielVorbereiten(Vector<Provinz> provinzListe, Vector<Spieler> spielerListe) {
		Random rand = new Random();
		int anzahlSpieler = spielerListe.size();
		int provinzenProSpieler = 42 / anzahlSpieler;
		int resteProvinzen = 42 % anzahlSpieler;
		
		Vector<Provinz> hilfsVector = (Vector<Provinz>)provinzListe.clone();
		Provinz zufallsProvinz;
		int zufall;
		int zaehler = 0;
		for(int i = 0; i < anzahlSpieler; i++) {
			for(int j = 0; j < provinzenProSpieler; j++) {
				zufall = rand.nextInt(42-zaehler);
				zufallsProvinz = hilfsVector.get(zufall);
				zufallsProvinz.setBesitzer(spielerListe.get(i));
				hilfsVector.remove(zufall);
				zaehler++;
			}
		}
		
		int i = 0;
		while(!hilfsVector.isEmpty()) {
			zufall = rand.nextInt(hilfsVector.size());
			zufallsProvinz = hilfsVector.get(zufall);
			zufallsProvinz.setBesitzer(spielerListe.get(i));
			hilfsVector.remove(zufall);
			i++;
		}
		
		//if(anzahlSpieler - restProvinzen != 0) {
			//TODO: Bonus Einheiten verteilen
		//}
		
	}
	
	private void spielBeginnen() {
		
	}

	
	
}
