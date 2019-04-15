package risiko.local.domain;

import java.util.Random;
import java.util.Vector;

import risiko.local.domain.exceptions.NichtProvinzDesSpielersExceptions;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spiel;
import risiko.local.valueobjects.Spieler;

public class SpielVerwaltung {
	
	private WeltVerwaltung weltVW;
	private SpielerVerwaltung spielerVW;
	
	public SpielVerwaltung(WeltVerwaltung weltVW, SpielerVerwaltung spielerVW) {
		this.spielerVW = spielerVW;
		this.weltVW = weltVW;
	}
		
	public void erstelleNeuesSpiel() {
		Spiel spiel = new Spiel();
	}
	
	public int spielVorbereiten( Vector<Spieler> spielerListe) {
		Vector<Provinz> provinzListe = weltVW.getProvinzListe();
		Random rand = new Random();
		int anzahlSpieler = spielerListe.size();
		int provinzenProSpieler = 42 / anzahlSpieler;
		int bonus;
		
		
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
				spielerListe.get(i).berechneAktuelleLaender(1);
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
			spielerListe.get(i).berechneAktuelleLaender(1);
			hilfsVector.remove(zufall);
			i++;
		}
		
		//Index der Spielerliste, ab dem Spieler Bonuseinheiten bekommen(anderen haben 1 Provinz mehr)	
		bonus = 42 % anzahlSpieler;
		if(bonus == 0) {
			bonus = spielerListe.size();
		}
		return bonus;
	}

	public void neueEinheitenSetzen(int toProvinz, int anzahlEinheiten, int spielerID) throws ProvinzIDExistiertNichtException, NichtProvinzDesSpielersExceptions {
		Provinz provinz = weltVW.getProvinz(toProvinz);
		validiereProvinzBesitzer(provinz, spielerID);
		for(int i = 0; i < anzahlEinheiten; i++) {
			provinz.erstelleEinheit(provinz.getBesitzer());
		}
	}	
	
	public boolean validiereProvinzBesitzer(Provinz provinz, int spielerID) throws NichtProvinzDesSpielersExceptions {
		Spieler spieler = spielerVW.getSpieler(spielerID);
		
		 if(!(provinz).getBesitzer().equals((spieler))){
			throw new NichtProvinzDesSpielersExceptions();
		}
		return true;		
	}
	
	public boolean validiereAnzahlEinheiten(int anzahl, int spielerID) {
		int verteilbareEinheiten = spielerVW.getVerteilbareEinheiten(spielerID);
		
		if((anzahl > 0) && verteilbareEinheiten >= anzahl) {
			return true;
		}
		return false;
	}
}
