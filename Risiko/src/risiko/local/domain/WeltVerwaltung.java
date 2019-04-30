package risiko.local.domain;

import java.util.List;
import java.util.Vector;

import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;
import risiko.local.valueobjects.Welt;

public class WeltVerwaltung {

	private Welt welt;
	private List<Kontinent> kontinentListe;
	private List<Provinz> provinzListe;
	
	
	
	public WeltVerwaltung() {
		
	}
	

	
	//---------------------- SPIELVORBEREITUNG: ERSTELLUNG DER WELT ------------------------		
	
	
	
	public void erstelleWelt() {
		welt = new Welt();
		//Erstellung der Vectoren fuer Kontinente und Provinzen
		kontinentListe = new Vector<Kontinent>(6);
		erstelleKontinente();
		provinzListe = new Vector<Provinz>(42);
		erstelleProvinzen();
		//kontinentiere = Verteilung der Provinzen auf die zugehoerigen Kontinente
		kontinentiereProvinzen();
	}
	
	private void erstelleKontinente() {
		kontinentListe.add(new Kontinent("Afrika"));
		kontinentListe.add(new Kontinent("Asien"));
		kontinentListe.add(new Kontinent("Australien"));
		kontinentListe.add(new Kontinent("Europa"));
		kontinentListe.add(new Kontinent("Nord-Amerika"));
		kontinentListe.add(new Kontinent("Sued-Amerika"));
	}
	
	private void erstelleProvinzen() {
		provinzListe.add(new Provinz("Aegypten",0));
		provinzListe.add(new Provinz("Kongo", 1));
		provinzListe.add(new Provinz("Madagaskar", 2));
		provinzListe.add(new Provinz("Nordwestafrika", 3));
		provinzListe.add(new Provinz("Ostafrika", 4));
		provinzListe.add(new Provinz("Suedafrika", 5));
		
		provinzListe.add(new Provinz("Afghanistan", 6));
		provinzListe.add(new Provinz("China", 7));
		provinzListe.add(new Provinz("Indien", 8));
		provinzListe.add(new Provinz("Irkutsk", 9));
		provinzListe.add(new Provinz("Jakutien", 10));
		provinzListe.add(new Provinz("Japan", 11));
		provinzListe.add(new Provinz("Kamtschatka", 12));
		provinzListe.add(new Provinz("Mittlerer Osten", 13));
		provinzListe.add(new Provinz("Monglei", 14));
		provinzListe.add(new Provinz("Siam", 15));
		provinzListe.add(new Provinz("Sibirien", 16));
		provinzListe.add(new Provinz("Ural", 17));
		
		provinzListe.add(new Provinz("Indonesien", 18));
		provinzListe.add(new Provinz("Neu Guinera", 19));
		provinzListe.add(new Provinz("Ost-Australien", 20));
		provinzListe.add(new Provinz("West-Australien", 21));
		
		provinzListe.add(new Provinz("Grossbritannien", 22));
		provinzListe.add(new Provinz("Island", 23));
		provinzListe.add(new Provinz("Mitteleuropa", 24));
		provinzListe.add(new Provinz("Skandinavien", 25));
		provinzListe.add(new Provinz("Sued-Europa", 26));
		provinzListe.add(new Provinz("Ukraine", 27));
		provinzListe.add(new Provinz("West-Europa", 28));
		
		provinzListe.add(new Provinz("Alaska", 29));
		provinzListe.add(new Provinz("Alberta", 30));
		provinzListe.add(new Provinz("Groenland", 31));
		provinzListe.add(new Provinz("Mittel-Amerika", 32));
		provinzListe.add(new Provinz("Nordwest-Territorium", 33));
		provinzListe.add(new Provinz("Ontario", 34));
		provinzListe.add(new Provinz("Oststaaten", 35));
		provinzListe.add(new Provinz("Quebeck", 36));
		provinzListe.add(new Provinz("Weststaaten", 37));
		
		provinzListe.add(new Provinz("Argentinien", 38));
		provinzListe.add(new Provinz("Brasilien", 39));
		provinzListe.add(new Provinz("Peru", 40));
		provinzListe.add(new Provinz("Venezuela", 41));
	}
	
	private void kontinentiereProvinzen() {
		
		Vector<Provinz> afrikasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> asiensProvinzListe = new Vector<Provinz>();
		Vector<Provinz> australiensProvinzListe = new Vector<Provinz>();
		Vector<Provinz> europasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> nordamerikasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> suedamerikasProvinzListe = new Vector<Provinz>();
		
		for(int i = 0; i <=5; i++) {
			afrikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(0).setProvinzen(afrikasProvinzListe);
		for(int i = 6; i <=17; i++) {
			asiensProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(1).setProvinzen(asiensProvinzListe);
		for(int i = 18; i <=21; i++) {
			australiensProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(2).setProvinzen(australiensProvinzListe);
		for(int i = 22; i <=28; i++) {
			europasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(3).setProvinzen(europasProvinzListe);
		for(int i = 29; i <=37; i++) {
			nordamerikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(4).setProvinzen(nordamerikasProvinzListe);
		for(int i = 38; i <=41; i++) {
			suedamerikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(5).setProvinzen(suedamerikasProvinzListe);
	}

	
	
	//---------------------- GETTER ------------------------	
	
	
	
	public Welt getWelt() {
		return welt;
	}
	
	public Vector<Provinz> getProvinzListe() {
		return (Vector<Provinz>) provinzListe;
	}
	
	public Provinz getProvinz (int provinzID) {
		return provinzListe.get(provinzID);
	}
	
	public Vector<Provinz> getProvinzenVonSpieler(Spieler spieler) {
		Vector<Provinz> eigeneProvinzen = new Vector<Provinz>();
		for(Provinz provinz : provinzListe) {
			if(provinz.getBesitzer().equals(spieler)) {
				eigeneProvinzen.add(provinz);
			}
		}
		return eigeneProvinzen;
	}

	public Vector <Kontinent> getKontinentListe(){
		return (Vector<Kontinent>) kontinentListe;
	}
	
	
	
	//----------------------RUNDEN-NACHBEREITUNG------------------------	
	
	
	
	public void resetInvolvierteEinheiten(Spieler spieler) {
		Vector <Provinz> provinzen = getProvinzenVonSpieler(spieler);
		for(Provinz provinz : provinzen) {
			provinz.resetInvolvierteEinheiten();
		}
	}

	
	//---------------------- LADEN ------------------------	

	public void ladeProvinzListe(Vector<Provinz> provinzenListe) {
		this.provinzListe = provinzenListe;
	}



	public void ladeKontinente(Vector<Kontinent> kontinentListe) {
		this.kontinentListe = kontinentListe;
	}
	
	public void ladeWelt(Welt welt) {
		this.welt = welt;
	}
	
}
