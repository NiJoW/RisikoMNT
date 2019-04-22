package risiko.local.domain;

import java.util.Vector;

import risiko.local.domain.exceptions.EigeneProvinzAngreifenException;
import risiko.local.domain.exceptions.NichtProvinzDesSpielersException;
import risiko.local.domain.exceptions.ProvinzIDExistiertNichtException;
import risiko.local.domain.exceptions.ProvinzNichtNachbarException;
import risiko.local.domain.exceptions.SpielerBereitsVorhandenException;
import risiko.local.domain.exceptions.AnzahlEinheitenFalschException;
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
		spielVW = new SpielVerwaltung(weltVW, spielerVW);
		spiellogik = new SpielLogik(weltVW, spielerVW);
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
	
	public void validiereAngriffEingaben(int fromProvinz, int toProvinz, int spielerID, int anzahlEinheiten) throws EigeneProvinzAngreifenException, NichtProvinzDesSpielersException, AnzahlEinheitenFalschException, ProvinzIDExistiertNichtException {
		spielVW.validiereAngriffEingaben(fromProvinz, toProvinz, spielerID, anzahlEinheiten);
			
	}
//	----------------------VERTEILEN----------------------
	
	
	public void berechneVerteilbareEinheiten(int aenderungsWert, int spielerID) {
		//�ndern der Variable verteilbareEinheiten
		spielerVW.berechneVerteilbareEinheiten(aenderungsWert, spielerID);
	}

	public int berechneNeueEinheiten(int spielerID) {
		//Beginn jeder Runde (anz. Provinzen/3)
		return spiellogik.berechneNeueEinheiten(spielerID);
	}
	
	public void setzeNeueEinheiten(int toProvinz, int anzahlEinheiten, int spielerID) throws NichtProvinzDesSpielersException, ProvinzIDExistiertNichtException, AnzahlEinheitenFalschException {
		//Erstellt neue Einheiten (Spielbeginn)
		spielVW.neueEinheitenSetzen(toProvinz, anzahlEinheiten, spielerID);
	}	
	
	

	
//	-------------------ANGRIFF-----------------------
	
	
	
	public int[] wuerfeln(int anzahlEinheiten, int toProvinz) {
		int[] wuerfelErgebnisse = spiellogik.wuerfeln(anzahlEinheiten, toProvinz);
		return wuerfelErgebnisse;
	}
	
	public String[][] angreifen(int fromProvinz, int toProvinz, int anzahlEinheiten, int[] wuerfelErgebnisse) throws ProvinzNichtNachbarException {
		String[][] ergebnis = null;
		spiellogik.kannAngreifen(fromProvinz, toProvinz);
		spielVW.einheitenInvolviert(anzahlEinheiten, fromProvinz);
		ergebnis = spiellogik.angriffAuswerten(wuerfelErgebnisse, fromProvinz, toProvinz, anzahlEinheiten);
		return ergebnis;
	}
	
	public boolean kannEinheitenNachruecken(int spielerID, int fromProvinz) {
		return spiellogik.kannEinheitenNachruecken(spielerID, fromProvinz);
	}
	
	public void einheitenNachruecken(int spielerID, int fromProvinz, int toProvinz, int anzahl) {
		
	}
	
	public String einerHatGewonnen(int spielerID) {
		return spiellogik.einerHatGewonnen(spielerID);
	}
	
	
	
	
//----------------------VERSCHIEBEN------------------------	

	

	public void einheitenVerschieben(int fromProvinz, int toProvinz, int anzahlEinheiten) throws AnzahlEinheitenFalschException, NichtProvinzDesSpielersException, ProvinzNichtNachbarException, ProvinzIDExistiertNichtException {
		spiellogik.kannVerschieben(fromProvinz, toProvinz, anzahlEinheiten);
		spiellogik.verschiebe(anzahlEinheiten, fromProvinz, toProvinz);
		
	}

	public void resetInvolvierteEinheiten(int spielerID) {
		weltVW.resetInvolvierteEinheiten(spielerVW.getSpieler(spielerID));
	}

	

	
	

	

	
	
	

	
	
}

