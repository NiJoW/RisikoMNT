package risiko.local.persistence;

import java.io.IOException;

import risiko.local.valueobjects.*;


public interface PersistenceManager {

	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();

	public Provinz ladeProvinz() throws IOException;
	
	public boolean speichereProvinz (Provinz p) throws IOException;
	
}