package risiko.local.valueobjects;

import java.util.List;
import java.util.Vector;

public class Welt {

	private List<Kontinent> kontinentListe;
	private List<Provinz> provinzListe;
		
	
	
	public Welt() {
		kontinentListe = new Vector<Kontinent>(6);
		erstelleKontinente();
		provinzListe = new Vector<Provinz>(42);
		erstelleProvinzen();
		//kontinentiere = Verteilung der Provinzen auf die zugehörigen Kontinente
		kontinentiereProvinzen();
		
	}
	
	public Vector<Provinz> getProvinzListe() {
		return provinzListe;
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
		
		//>ToDo  Weitere Provinzen
		//>ToDo   Kontinente weg
	}
	
	private void kontinentiereProvinzen() {
		
		List<Provinz> afrikasProvinzListe = null;
		List<Provinz> asiensProvinzListe = null;
		List<Provinz> australiensProvinzListe = null;
		List<Provinz> europasProvinzListe = null;
		List<Provinz> nordamerikasProvinzListe = null;
		List<Provinz> südamerikasProvinzListe = null;
		
		for(int i = 0; i <=5; i++) {
			afrikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(0).setProvinzenListe(afrikasProvinzListe);
		for(int i = 6; i <=17; i++) {
			asiensProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(1).setProvinzenListe(asiensProvinzListe);
		for(int i = 18; i <=21; i++) {
			australiensProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(2).setProvinzenListe(australiensProvinzListe);
		for(int i = 22; i <=28; i++) {
			europasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(3).setProvinzenListe(europasProvinzListe);
		for(int i = 29; i <=37; i++) {
			nordamerikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(4).setProvinzenListe(nordamerikasProvinzListe);
		for(int i = 38; i <=41; i++) {
			südamerikasProvinzListe.add(provinzListe.get(i));
		}
		kontinentListe.get(5).setProvinzenListe(südamerikasProvinzListe);
	}
	
}
