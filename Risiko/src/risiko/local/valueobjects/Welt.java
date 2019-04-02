package risiko.local.valueobjects;

import java.util.List;
import java.util.Vector;

public class Welt {

	private List<Kontinent> kontinentListe;
	private List<Provinz> provinzListe;
	
	private List<Provinz> afrikasProvinzListe;
	private List<Provinz> asiensProvinzListe;
	private List<Provinz> australiensProvinzListe;
	private List<Provinz> europasProvinzListe;
	private List<Provinz> nordamerikasProvinzListe;
	private List<Provinz> südamerikasProvinzListe;
	
	
	
	
	public Welt() {
		kontinentListe = new Vector<Kontinent>(6);
		erstelleKontinente();
		provinzListe = new Vector<Provinz>(42);
		erstelleProvinzen();
		//kontinentiere = Verteilung der Provinzen auf die zugehörigen Kontinente
		kontinentiereProvinzen();
		
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
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		provinzListe.add(new Provinz("A",kontinentListe.get(0)));
		//Weitere Provinzen
	}
	
	private void kontinentiereProvinzen() {
		for(int i = 0; i <=5; i++) {
			afrikasProvinzListe.add(provinzListe.get(i));
		}
		for(int i = 6; i <=17; i++) {
			asiensProvinzListe.add(provinzListe.get(i));
		}
		for(int i = 18; i <=21; i++) {
			australiensProvinzListe.add(provinzListe.get(i));
		}
		for(int i = 22; i <=28; i++) {
			europasProvinzListe.add(provinzListe.get(i));
		}
		for(int i = 29; i <=37; i++) {
			nordamerikasProvinzListe.add(provinzListe.get(i));
		}
		for(int i = 38; i <=41; i++) {
			südamerikasProvinzListe.add(provinzListe.get(i));
		}
	}
	
}
