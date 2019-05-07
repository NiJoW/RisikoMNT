package risiko.local.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import risiko.local.domain.SpielVerwaltung;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;
import risiko.local.valueobjects.*;


public interface PersistenceManager {
	
	public void speichereSpiel(int spielerID, SpielVerwaltung spielVW) throws FileNotFoundException, IOException;
	
	public int spielLaden(String name, SpielVerwaltung spielVW);
	
	public List<String> spielnamenAusgeben();

	public String validiereSpiel(List<String> spielNamen, int spielID, Vector<Spieler> spielerListe)  throws SpielNichtVorhandenException, SpielerNichtTeilDesSpielsException;
	
}