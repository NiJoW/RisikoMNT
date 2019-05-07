package risiko.local.persistence;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import risiko.local.domain.SpielVerwaltung;
import risiko.local.domain.SpielerVerwaltung;
import risiko.local.domain.WeltVerwaltung;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;
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
	public void speichereSpiel(int spielerID, SpielVerwaltung spielVW) throws FileNotFoundException, IOException {
		this.spielVW = spielVW;
		spiel.setSpielerID(spielerID);
		spiel.setProvinzenListe(weltVW.getProvinzListe());
		spiel.setSpielerliste(spielerVW.getSpielerListe());
		spiel.setKontinentListe(weltVW.getKontinentListe());
		spiel.setWelt(weltVW.getWelt());
		spiel.setKartenTauschBonus(spielVW.getKartenTauschBonus());
		
		
		String spielName = "+_";
		for(Spieler spieler : spielerVW.getSpielerListe()) {
			spielName += spieler.getName() + "_+_";
		}
    	spielName = spielName.substring(0, spielName.length() - 2);
		
		DateFormat datumFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm"); // HH_mm
		Date datum = new Date();
		spielName += "---" + datumFormat.format(datum) + "_Uhr";
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("savedGames/" + spielName + ".ser"));
		oos.writeObject(spiel);
		
	}
	
	@Override
	public int spielLaden(String spielName, SpielVerwaltung spielVW) {
		Spiel spiel = null; 
		  
        // Deserialization 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGames/" + spielName + ".ser"))) {    
            spiel = (Spiel) ois.readObject();
            
            weltVW.ladeProvinzListe(spiel.getProvinzenListe());
            weltVW.ladeKontinente(spiel.getKontinentListe());
            weltVW.ladeWelt(spiel.getWelt());
            spielerVW.ladeSpielerliste(spiel.getSpielerListe());
            spielVW.ladeKartenTauschBonus(spiel.getKartenTauschBonus());
            
		} catch(ClassNotFoundException e) { 
            System.out.println("ClassNotFoundException is caught"); 
        }  catch(IOException e) { 
            System.out.println("IOException is caught"); 
        }
		return spiel.getSpielerID();
    }

	@Override
	public List<String> spielnamenAusgeben() {
		List<String> dateiNamen = new ArrayList<String>();
		
		File[] ordner = new File("savedGames").listFiles();


		for (File datei : ordner) {
		    if (datei.isFile()) {
		    	
		    	String name = datei.getName();
		    	name = name.substring(0, name.length() - 4);
		        dateiNamen.add(name);
		    }
		}
		
		return dateiNamen;

	}

	@Override
	public String validiereSpiel(List<String> spielNamen, int spielID, Vector<Spieler> spielerListe) throws SpielNichtVorhandenException, SpielerNichtTeilDesSpielsException {
		if(!(spielID >= 0 && spielID < spielNamen.size())) {
			throw new SpielNichtVorhandenException();
		}
		String aktivesSpielName = spielNamen.get(spielID);
		validiereSpieler(aktivesSpielName, spielerListe);
		return aktivesSpielName;
	}

	private void validiereSpieler(String aktivesSpielName, Vector<Spieler> spielerListe) throws SpielerNichtTeilDesSpielsException {
		
		for(Spieler spieler : spielerListe) {
			String needle = "+_" + spieler.getName() + "_";
			int index = aktivesSpielName.indexOf(needle);
			if(index == -1) {
				throw new SpielerNichtTeilDesSpielsException();
			}
		}
	}

}
