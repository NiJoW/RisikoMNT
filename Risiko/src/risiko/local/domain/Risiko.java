package risiko.local.domain;

import java.util.Vector;

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
	
	public void spielerHinzufuegen(String name) {
		spielerVW.spielerHinzufuegen(name);
	}
	
	
	public void spielVorbereiten() {
		spielVW.erstelleNeuesSpiel();
		weltVW.erstelleWelt();
		Vector<Provinz> provinzenListe = weltVW.getProvinzListe();
		Vector<Spieler> spielerListe = spielerVW.getSpielerListe();
		int bonusAbSpieler = spielVW.spielVorbereiten(provinzenListe, spielerListe);
		spielerVW.weiseEinheitenZu(bonusAbSpieler);
	}
	

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

	public boolean validiereProvinzID(int provinzID, int spielerID) {
		return spiellogik.validiereProvinzID(provinzID, spielerID);
	}
	
	public Spieler getSpieler(int id) {
		return spielerVW.getSpieler(id);
	}
	
	public boolean validiereAnzahlEinheiten(int anzahlEinheiten, int spielerID) {
		return spiellogik.validiereAnzahlEinheiten(anzahlEinheiten, spielerID, getVerteilbareEinheiten(spielerID));
	}
	
	public void berechneVerteilbareEinheiten(int aenderungsWert, int spielerID) {
		spielerVW.berechneVerteilbareEinheiten(aenderungsWert, spielerID);
	}

	public int berechneNeueEinheiten(int spielerID) {
		return spiellogik.berechneNeueEinheiten(spielerVW.getSpieler(spielerID), weltVW.getKontinentListe());
	}
	
	public void setzeNeueEinheiten(int toProvinz, int anzahlEinheiten) {
		spielVW.neueEinheitenSetzen(toProvinz, anzahlEinheiten);
	}	
	
	public int[] wuerfeln(int anzahlEinheiten, int toProvinz) {
		int[] wuerfelErgebnisse = spiellogik.wuerfeln(anzahlEinheiten, toProvinz);
		return wuerfelErgebnisse;
	}
	
	public String[][] angreifen(int fromProvinz, int toProvinz, int anzahlEinheiten, int[] wuerfelErgebnisse) {
		String[][] ergebnis = null;
		Vector<Provinz> provinzenListe = weltVW.getProvinzListe();
		if(spiellogik.kannAngreifen(fromProvinz, toProvinz, provinzenListe, weltVW.getWelt())) {
//			Vector<Provinz> verlierer = spiellogik.angreifen(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
			ergebnis = spiellogik.angriffAuswerten(wuerfelErgebnisse, getProvinz(fromProvinz), getProvinz(toProvinz), anzahlEinheiten);
		}
		return ergebnis;
	}
	
	public Provinz getProvinz(int provinzID) {
		return weltVW.getProvinzListe().get(provinzID);
	}
	
	public boolean validiereZielProvinz(int fromProvinz, int toProvinz, int spielerIndex) {
		return spiellogik.validiereZielProvinz(getProvinz(fromProvinz), getProvinz(toProvinz), getSpieler(spielerIndex));
	}
	
	public boolean validiereAnzahlAngreifendeEinheiten(int fromProvinz, int spielerIndex, int anzahlEinheiten) {
		return spiellogik.validiereAnzahlAngreifendeEinheiten(getProvinz(fromProvinz), getSpieler(spielerIndex), anzahlEinheiten);
	}

	public void einheitenVerteilen(int provinzID, int anzahl) {
		spielVW.neueEinheitenSetzen(provinzID, anzahl);
	}

	public void einheitenVerschieben(int fromProvinz, int toProvinz, int anzahlEinheiten) {
		Vector<Provinz> provinzenListe = weltVW.getProvinzListe();
		if(spiellogik.kannVerschieben(fromProvinz, toProvinz, provinzenListe, weltVW.getWelt(), anzahlEinheiten)) {
			spiellogik.verschiebe(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
		}
	}

	

	
	
}

