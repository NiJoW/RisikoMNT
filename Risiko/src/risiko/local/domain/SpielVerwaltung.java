package risiko.local.domain;

import java.util.Random;
import java.util.Vector;

import risiko.local.domain.exceptions.EigeneProvinzAngreifenException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
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

	public void neueEinheitenSetzen(int toProvinz, int anzahlEinheiten, int spielerID) throws NichtProvinzDesSpielersException, ProvinzIDExistiertNichtException, AnzahlEinheitenFalschException {
		Provinz provinz = weltVW.getProvinz(toProvinz);
		validiereProvinz(toProvinz, spielerID);
		for(int i = 0; i < anzahlEinheiten; i++) {
			provinz.erstelleEinheit(provinz.getBesitzer());
		}
		validiereAnzahlEinheiten(anzahlEinheiten, spielerID);
	}	
	
	public void validiereProvinz(int provinzID, int spielerID) throws NichtProvinzDesSpielersException, ProvinzIDExistiertNichtException {
		Spieler spieler = spielerVW.getSpieler(spielerID);
		
		if(provinzID>41 || provinzID<0) {
			throw new  ProvinzIDExistiertNichtException();
		}
		
		Provinz provinz = weltVW.getProvinz(provinzID);
		 if(!(provinz).getBesitzer().equals((spieler))){
			throw new NichtProvinzDesSpielersException();
		}	
		 
	}
	
	public void validiereAnzahlEinheiten(int anzahl, int spielerID) throws AnzahlEinheitenFalschException {
		int verteilbareEinheiten = spielerVW.getVerteilbareEinheiten(spielerID);
		if(!((anzahl > 0) && verteilbareEinheiten >= anzahl)) {
			throw new AnzahlEinheitenFalschException("Du musst mehr als 0 und maximal " + verteilbareEinheiten + " Einheiten setzten.");
		}
	}

	public void validiereAngriffEingaben(int from, int to, int spielerID, int anzahlEinheiten) throws EigeneProvinzAngreifenException, NichtProvinzDesSpielersException, AnzahlEinheitenFalschException, ProvinzIDExistiertNichtException {
		validiereProvinz(from, spielerID); //dem Angreifer gehoert das angreifende Land
		
		if(to>41 || to<0) {
			throw new  ProvinzIDExistiertNichtException();
		}
		
		Provinz fromProvinz = weltVW.getProvinz(from);
		Provinz toProvinz = weltVW.getProvinz(to);
		Spieler spieler = spielerVW.getSpieler(spielerID);
		
			if(spieler.equals(toProvinz.getBesitzer())) { //dem Angreifer gehoert das verteidigende Land
				throw new EigeneProvinzAngreifenException();
			}else if(fromProvinz.getArmeeGroesse() - anzahlEinheiten < 1){
				throw new AnzahlEinheitenFalschException("Du hast nicht genug Einheiten auf deiner Provinz.");
			}else if(anzahlEinheiten > 3 || anzahlEinheiten < 1) {
				throw new AnzahlEinheitenFalschException("Du darfst nicht mit mehr als 3 oder weiger als 1 Einheit(en) angreifen.");
			}
	
	}

	public void einheitenInvolviert(int anzahl, int from) {
		Provinz fromProvinz = weltVW.getProvinz(from);
		fromProvinz.setInvolvierteEinheiten(anzahl);
	}
}
