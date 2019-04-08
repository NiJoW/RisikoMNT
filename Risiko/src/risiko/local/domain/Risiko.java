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
		spiellogik = new SpielLogik();
		spielVW = new SpielVerwaltung();
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
	
	public void spielStarten() {
		spiellogik.spielStarten(); //provinzenListe, spielerList
	}

	public void einheitenVerteilen(int id, int anzahl) {
		spielVW.einheitenVerteilen(getProvinz(id), anzahl);
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

	public Provinz getProvinz(int provinzID) {
		return weltVW.getProvinzListe().get(provinzID);
	}

	public Spieler getSpieler(int id) {
		return spielerVW.getSpieler(id);
	}
	
	public boolean validiereProvinzID(int provinzID, int spielerID) {
		return spiellogik.validiereProvinzID(provinzID, spielerID, getProvinz(provinzID), getSpieler(spielerID));
	}

	public boolean validiereAnzahlEinheiten(int anzahlEinheiten, int spielerID) {
		return spiellogik.validiereAnzahlEinheiten(anzahlEinheiten, spielerID, getVerteilbareEinheiten(spielerID));
	}
	
	public void berechneVerteilbareEinheiten(int aenderungsWert, int spielerID) {
		spielerVW.berechneVerteilbareEinheiten(aenderungsWert, spielerID);
	}

	public int[] wuerfeln(int anzahlEinheiten, int fromProvinz, int toProvinz) {
		int[] wuerfelErgebnisse = spiellogik.wuerfeln(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
		return wuerfelErgebnisse;
	}
	
	public void angreifen(int fromProvinz, int toProvinz, int anzahlEinheiten, int[] wuerfelErgebnisse) {
		Vector<Provinz> provinzenListe = weltVW.getProvinzListe();
		if(spiellogik.kannAngreifen(fromProvinz, toProvinz, provinzenListe, weltVW.getWelt())) {
//			Vector<Provinz> verlierer = spiellogik.angreifen(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
			spiellogik.angriffAuswerten(wuerfelErgebnisse, getProvinz(fromProvinz), getProvinz(toProvinz), anzahlEinheiten);
		}
	}
	

	public void einheitenVerschieben(int fromProvinz, int toProvinz, int anzahlEinheiten) {
		Vector<Provinz> provinzenListe = weltVW.getProvinzListe();
		if(spiellogik.kannVerschieben(fromProvinz, toProvinz, provinzenListe, weltVW.getWelt())) {
			spiellogik.verschiebe(anzahlEinheiten, getProvinz(fromProvinz), getProvinz(toProvinz));
		}
	}
	
}

