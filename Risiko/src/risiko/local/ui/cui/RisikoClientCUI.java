package risiko.local.ui.cui;

import risiko.local.domain.Risiko;

public class RisikoClientCUI {
	private Risiko risiko;
	
	public RisikoClientCUI() {
		risiko = new Risiko();
		
		//bufferedReader()
		
	}
	
	private void run() {//gegebenenfalls l�schen
		spielerRegistrierung();
		
	}
	
	private void spielerRegistrierung() {
		String input = "";
		do {
			gibSpielerRegistrierungAus();
			//try-catch
			input = liesEingabe();
			verarbeiteSpielerRegistrierung(input);
	
		}while(!input.equals("q"));
		spielMenueAufrufen();
	}
	
	public void gibSpielerRegistrierungAus() {
		int anzahl = 0;
		
		System.out.println("Gib die Anzahl der Spieler an:");
		anzahl = 2; //Eingabe einlesen
		//spielerliste
		for(int i = 0; i < anzahl; i++) {
			
			risiko.spielerHinzufuegen("name");
		}
		
	}
	
	private void spielMenueAufrufen() {
		String input = "";
		do {
			//try-catch
			input = liesEingabe();
			verarbeiteSpielmenue(input);
		}while(!input.equals("q"));
	}
	
	private String liesEingabe() {
		return "input";
	}
	
	/*private void verarbeiteSpielerRegistrierung(input) {
		//Abfrage nach Spieleranzahl
		int anzahl;
		for()
		Spieler s1 = new Spieler() {
			
		}
	}*/
	
	private void verarbeiteSpielmenue(String input) {
		switch(input) {
		case 
		}
	}
	
	public static void main(String[] args) {
		RisikoClientCUI cui;
		//try-catch exception
		cui = new RisikoClientCUI();
		cui.run();
	}
}
