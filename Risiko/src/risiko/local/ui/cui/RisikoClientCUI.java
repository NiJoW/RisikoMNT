package risiko.local.ui.cui;

import risiko.local.domain.Risiko;

public class RisikoClientCUI {
	private Risiko risiko;
	
	public RisikoClientCUI() {
		risiko = new Risiko();
		
		//bufferedReader()
		
	}
	
	private void run() {//gegebenenfalls löschen
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
