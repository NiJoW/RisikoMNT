package risiko.local.persistence;

import java.io.IOException;

import risiko.local.valueobjects.*;


public interface PersistenceManager {
	
	public void speichereSpiel(int spielerID);
	
	public int spielLaden(String name);
	
}