package risiko.local.persistence;

import java.io.IOException;
import java.util.List;

import risiko.local.domain.SpielVerwaltung;
import risiko.local.valueobjects.*;


public interface PersistenceManager {
	
	public void speichereSpiel(int spielerID, SpielVerwaltung spielVW);
	
	public int spielLaden(String name, SpielVerwaltung spielVW);
	
	public List<String> spielnamenAusgeben();
	
}