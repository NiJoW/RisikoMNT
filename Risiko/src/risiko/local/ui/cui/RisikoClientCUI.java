package risiko.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import risiko.local.domain.Risiko;

public class RisikoClientCUI {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientCUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));		
	}
	
	private void run() {//gegebenenfalls l�schen
		spielerRegistrierung();
		
	}
	
	private void spielerRegistrierung() {
		String input = "";
		do {
			gibSpielerRegistrierungAus();
			//try-catch
			//input = liesEingabe();
			//verarbeiteSpielerRegistrierung(input);
	
		}while(!input.equals("q"));
		spielMenueAufrufen();
	}
	
	public void gibSpielerRegistrierungAus() {
		int anzahl = 0;
		
		System.out.println("Gib die Anzahl der Spieler an:");
		anzahl = 2; //Eingabe einlesen
		try {
			anzahl = Integer.parseInt(liesEingabe());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < anzahl; i++) {
		    System.out.println("Geben sie ihren Namen ein:");
		    try {
		    	String name = liesEingabe();
				risiko.spielerHinzufuegen(name);
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		    }
		   
		}
		
	}
	
	private void spielMenueAufrufen() {
		String input = "";
		do {

			try {
				input = liesEingabe();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			verarbeiteSpielmenue(input);
		}while(!input.equals("q"));
	}
	
	private String liesEingabe()  throws IOException{
		// einlesen von Konsole
		return in.readLine();
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
		//case 
		}
	}
	
	public static void main(String[] args) {
		RisikoClientCUI cui;
		//try-catch exception
		cui = new RisikoClientCUI();
		cui.run();
	}
}
