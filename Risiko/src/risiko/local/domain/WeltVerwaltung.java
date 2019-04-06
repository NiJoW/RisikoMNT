package risiko.local.domain;

import java.util.List;
import java.util.Vector;

import risiko.local.valueobjects.Kontinent;
import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Welt;

public class WeltVerwaltung {

	private Welt welt;
	private List<Kontinent> kontinentListe;
	private List<Provinz> provinzListe;
	
	
	
	public WeltVerwaltung() {
		
	}
	
	public void erstelleWelt() {
		welt = new Welt();
		kontinentListe = new Vector<Kontinent>(6);
		erstelleKontinente();
		provinzListe = new Vector<Provinz>(42);
		erstelleProvinzen();
		//kontinentiere = Verteilung der Provinzen auf die zugehörigen Kontinente
		kontinentiereProvinzen();
	}
	
	public Vector<Provinz> getProvinzListe() {
		return (Vector<Provinz>) provinzListe;
	}
	
	private void erstelleKontinente() {
		kontinentListe.add(new Kontinent("Afrika"));
		kontinentListe.add(new Kontinent("Asien"));
		kontinentListe.add(new Kontinent("Australien"));
		kontinentListe.add(new Kontinent("Europa"));
		kontinentListe.add(new Kontinent("Nord-Amerika"));
		kontinentListe.add(new Kontinent("Süd-Amerika"));
	}
	
	private void erstelleProvinzen() {
		provinzListe.add(new Provinz("Ägypten"));
		provinzListe.add(new Provinz("Kongo"));
		provinzListe.add(new Provinz("Madagaskar"));
		provinzListe.add(new Provinz("Nordwestafrika"));
		provinzListe.add(new Provinz("Ostafrika"));
		provinzListe.add(new Provinz("Südafrika"));
		
		provinzListe.add(new Provinz("Afghanistan"));
		provinzListe.add(new Provinz("China"));
		provinzListe.add(new Provinz("Indien"));
		provinzListe.add(new Provinz("Irkutsk"));
		provinzListe.add(new Provinz("Jakutien"));
		provinzListe.add(new Provinz("Japan"));
		provinzListe.add(new Provinz("Kamtschatka"));
		provinzListe.add(new Provinz("Mittlerer Osten"));
		provinzListe.add(new Provinz("Monglei"));
		provinzListe.add(new Provinz("Siam"));
		provinzListe.add(new Provinz("Sibirien"));
		provinzListe.add(new Provinz("Ural"));
		
		provinzListe.add(new Provinz("Indonesien"));
		provinzListe.add(new Provinz("Neu Guinera"));
		provinzListe.add(new Provinz("Ost-Australien"));
		provinzListe.add(new Provinz("West-Australien"));
		
		provinzListe.add(new Provinz("Großbritannien"));
		provinzListe.add(new Provinz("Island"));
		provinzListe.add(new Provinz("Mitteleuropa"));
		provinzListe.add(new Provinz("Skandinavien"));
		provinzListe.add(new Provinz("Süd-Europa"));
		provinzListe.add(new Provinz("Ukraine"));
		provinzListe.add(new Provinz("West-Europa"));
		
		provinzListe.add(new Provinz("Alaska"));
		provinzListe.add(new Provinz("Alberta"));
		provinzListe.add(new Provinz("Grönland"));
		provinzListe.add(new Provinz("Mittel-Amerika"));
		provinzListe.add(new Provinz("Nordwest-Territorium"));
		provinzListe.add(new Provinz("Ontario"));
		provinzListe.add(new Provinz("Oststaaten"));
		provinzListe.add(new Provinz("Quebeck"));
		provinzListe.add(new Provinz("Weststaaten"));
		
		provinzListe.add(new Provinz("Argentinien"));
		provinzListe.add(new Provinz("Brasilien"));
		provinzListe.add(new Provinz("Peru"));
		provinzListe.add(new Provinz("Venezuela"));
	}
	
	private void kontinentiereProvinzen() {
		
		Vector<Provinz> afrikasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> asiensProvinzListe = new Vector<Provinz>();
		Vector<Provinz> australiensProvinzListe = new Vector<Provinz>();
		Vector<Provinz> europasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> nordamerikasProvinzListe = new Vector<Provinz>();
		Vector<Provinz> südamerikasProvinzListe = new Vector<Provinz>();
		
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
			südamerikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(5).setProvinzen(südamerikasProvinzListe);
	}
	
}
