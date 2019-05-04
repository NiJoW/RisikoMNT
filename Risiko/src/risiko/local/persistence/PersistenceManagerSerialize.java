package risiko.local.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import risiko.local.domain.SpielVerwaltung;
import risiko.local.domain.SpielerVerwaltung;
import risiko.local.domain.WeltVerwaltung;
import risiko.local.valueobjects.Spiel;
import risiko.local.valueobjects.Spieler;

public class PersistenceManagerSerialize implements PersistenceManager {
	
	Spiel spiel = new Spiel();
	WeltVerwaltung weltVW; 
	SpielerVerwaltung spielerVW;
	SpielVerwaltung spielVW;
	
	public PersistenceManagerSerialize(WeltVerwaltung weltVW, SpielerVerwaltung spielerVW) {
		this.weltVW = weltVW;
		this.spielerVW = spielerVW;
	}
	
	@Override
	public void speichereSpiel(int spielerID, SpielVerwaltung spielVW) {
		this.spielVW = spielVW;
		spiel.setSpielerID(spielerID);
		spiel.setProvinzenListe(weltVW.getProvinzListe());
		spiel.setSpielerliste(spielerVW.getSpielerListe());
		spiel.setKontinentListe(weltVW.getKontinentListe());
		spiel.setWelt(weltVW.getWelt());
		spiel.setKartenTauschBonus(spielVW.getKartenTauschBonus());
		
		
		String spielName = "";
		for(Spieler spieler : spielerVW.getSpielerListe()) {
			spielName += spieler.getName() + "_+_";
		}
		DateFormat datumFormat = new SimpleDateFormat("dd_MM_yyyy"); // HH_mm
		Date datum = new Date();
		spielName += datumFormat.format(datum);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGames/" + spielName + ".ser"))) {
			oos.writeObject(spiel);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int spielLaden(String spielName) {
		Spiel spiel = null; 
		  
        // Deserialization 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGames/" + spielName + ".ser"))) {    
            spiel = (Spiel) ois.readObject();
            
            weltVW.ladeProvinzListe(spiel.getProvinzenListe());
            weltVW.ladeKontinente(spiel.getKontinentListe());
            weltVW.ladeWelt(spiel.getWelt());
            spielerVW.ladeSpielerliste(spiel.getSpielerListe());
            spielVW.ladeKartenTauschBonus(spiel.getKartenTauschBonus()); //TODO: Fehler-> KartenTauschBonus nicht seriasable
            
        } catch(IOException e) { 
            System.out.println("IOException is caught"); 
        } catch(ClassNotFoundException e) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
		return spiel.getSpielerID();
    }

	@Override
	public List<String> spielnamenAusgeben() {
		List<String> dateiNamen = new ArrayList<String>();
		//TODO: .ser nicht mit ausgeben

		File[] ordner = new File("savedGames").listFiles();

		for (File datei : ordner) {
		    if (datei.isFile()) {
		        dateiNamen.add(datei.getName());
		    }
		}
		return dateiNamen;
	}



	
	

}
