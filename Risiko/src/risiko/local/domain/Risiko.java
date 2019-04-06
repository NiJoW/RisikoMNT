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
	
	public void gibSpielerlisteAus() {
		//spielerVW.gibSpielerlisteAus();
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
		spiellogik.spielStarten(); //provinzenListe, spielerListe
		spiellogik.angreifen();
		
	}

	public void einheitenVerteilen(int id, int anzahl) {
		spielVW.einheitenVerteilen();
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

	public void berechneVerteilbareEinheiten(int aenderungsWert, int spielerID) {
		spielerVW.berechneVerteilbareEinheiten(aenderungsWert, spielerID);
	}
	
	
}

