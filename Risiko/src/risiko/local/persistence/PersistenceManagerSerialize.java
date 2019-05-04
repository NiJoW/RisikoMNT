package risiko.local.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import risiko.local.domain.SpielerVerwaltung;
import risiko.local.domain.WeltVerwaltung;
import risiko.local.valueobjects.Spiel;

public class PersistenceManagerSerialize implements PersistenceManager {
	
	Spiel spiel = new Spiel();
	WeltVerwaltung weltVW; 
	SpielerVerwaltung spielerVW;
	
	public PersistenceManagerSerialize(WeltVerwaltung weltVW, SpielerVerwaltung spielerVW) {
		this.weltVW = weltVW;
		this.spielerVW = spielerVW;
	}
	
	@Override
	public void speichereSpiel(int spielerID) {
		spiel.setSpielerID(spielerID);
		spiel.setProvinzenListe(weltVW.getProvinzListe());
		spiel.setSpielerliste(spielerVW.getSpielerListe());
		spiel.setKontinentListe(weltVW.getKontinentListe());
		spiel.setWelt(weltVW.getWelt());
		//spiel.setTauschNummer()
		//+ name +
		
//		for(Spieler spieler : spielerVW.spielerListe()) {
//			
//		}
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGames/name.ser"))) {
			oos.writeObject(spiel);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int spielLaden(String name) {
		Spiel spiel = null; 
		  
        // Deserialization 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGames/" + name + ".ser"))) {    
            spiel = (Spiel) ois.readObject();
            
            weltVW.ladeProvinzListe(spiel.getProvinzenListe());
            weltVW.ladeKontinente(spiel.getKontinentListe());
            weltVW.ladeWelt(spiel.getWelt());
            spielerVW.ladeSpielerliste(spiel.getSpielerListe());
            
        } catch(IOException e) { 
            System.out.println("IOException is caught"); 
        } catch(ClassNotFoundException e) { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
		return spiel.getSpielerID();
    }



	
	

}
