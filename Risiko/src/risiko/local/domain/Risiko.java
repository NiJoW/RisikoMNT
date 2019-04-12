package risiko.local.domain;

import java.util.Vector;

import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;

public class Risiko {
	private SpielerVerwaltung spielerVW;
	private WeltVerwaltung weltVW;
	private SpielLogik spiellogik;
	private SpielVerwaltung spielVW;
	
	
	public Risiko() {
		spielerVW = new SpielerVerwaltung();
		weltVW = new WeltVerwaltung();
		spielVW = new SpielVerwaltung(weltVW);
		spiellogik = new SpielLogik(spielerVW, weltVW);
	}
	
	public boolean spielerNameVorhanden(String name) throws SpielerBereitsVorhandenException {
		return spielerVW.spielerBereitsVorhanden(name);
	}

	public void spielerHinzufuegen(String name) {
		spielerVW.spielerHinzufuegen(name);
	}
	
	
	public void spielVorbereiten() {
		spielVW.erstelleNeuesSpiel();
		weltVW.erstelleWelt();
		Vector<Spieler> spielerListe = spielerVW.getSpielerListe();
		int bonusAbSpieler = spielVW.spielVorbereiten(spielerListe);
		spielerVW.weiseEinheitenZu(bonusAbSpieler);
	}
	
	
	
	
	
	
//	-------------GETTER---------------------

	public int getSpielerAnzahl() {
		return spielerVW.getSpielerAnzahl();
	}
	
	public String getSpielerName(int id) {
		return spielerVW.getSpielerName(id);
	}
	
	public int getVerteilbareEinheiten(int id) {
		return spielerVW.getVerteilbareEinheiten(id);
	}
	
	public Vector<Provinz> getProvinzenVonSpieler(int id){
		return weltVW.getProvinzenVonSpieler(spielerVW.getSpielerListe().get(id));
	}

	public Provinz getProvinz(int provinzID) {
		return weltVW.getProvinzListe().get(provinzID);
	}
	
	
	
	
	
	
//	-----------------------VALIDIERE------------------------
	
	
	
	public boolean validiereProvinzID(int provinzID, int spielerID) {
		return spiellogik.validiereProvinzID(provinzID, spielerID);
	}
	
	public boolean validiereAnzahlEinheiten(int anzahlEinheiten, int spielerID) {
		return spiellogik.validiereAnzahlEinheiten(anzahlEinheiten, spielerID);
	}
	
	public boolean validiereAnzahlAngreifendeEinheiten(int fromProvinz, int spielerIndex, int anzahlEinheiten) {
		return spiellogik.validiereAnzahlAngreifendeEinheiten(fromProvinz, spielerIndex, anzahlEinheiten);
	}
	

	public boolean validiereZielProvinz(int fromProvinz, int toProvinz, int spielerIndex) {
		return spiellogik.validiereZielProvinz(fromProvinz, toProvinz, spielerIndex);
	}
	
	
	
	
//	----------------------VERTEILEN----------------------
	
	
	public void berechneVerteilbareEinheiten(int aenderungsWert, int spielerID) {
		//Ändern der Variable verteilbareEinheiten
		spielerVW.berechneVerteilbareEinheiten(aenderungsWert, spielerID);
	}

	public int berechneNeueEinheiten(int spielerID) {
		//Beginn jeder Runde (anz. Provinzen/3)
		return spiellogik.berechneNeueEinheiten(spielerID);
	}
	
	public void setzeNeueEinheiten(int toProvinz, int anzahlEinheiten) {
		//Erstellt neue Einheiten (Spielbeginn)
		spielVW.neueEinheitenSetzen(toProvinz, anzahlEinheiten);
	}	
	
	
	
	
	
	
	
	
//	-------------------ANGRIFF-----------------------
	
	
	
	public int[] wuerfeln(int anzahlEinheiten, int toProvinz) {
		int[] wuerfelErgebnisse = spiellogik.wuerfeln(anzahlEinheiten, toProvinz);
		return wuerfelErgebnisse;
	}
	
	public String[][] angreifen(int fromProvinz, int toProvinz, int anzahlEinheiten, int[] wuerfelErgebnisse) {
		String[][] ergebnis = null;
		if(spiellogik.kannAngreifen(fromProvinz, toProvinz)) {
//			Vector<Provinz> verlierer = spiellogik.angreifen(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
			ergebnis = spiellogik.angriffAuswerten(wuerfelErgebnisse, fromProvinz, toProvinz, anzahlEinheiten);
		}
		return ergebnis;
	}
	
	
	
	
	
	
	
//----------------------VERSCHIEBEN------------------------	

	

	public void einheitenVerschieben(int fromProvinz, int toProvinz, int anzahlEinheiten) {
		if(spiellogik.kannVerschieben(fromProvinz, toProvinz, anzahlEinheiten)) {
			spiellogik.verschiebe(anzahlEinheiten, fromProvinz, toProvinz);
		}
	}

	
	

	
	
}

