package risiko.local.domain;
import java.util.Random;
import java.util.Vector;

import risiko.local.valueobjects.Provinz;
import risiko.local.valueobjects.Spieler;

public class SpielLogik {
	
	
	public SpielLogik() {
	}

	public void spielStarten() { //Vector<Provinz> provinzListe, Vector<Spieler> spielerListe
		
	}

	
	
	private boolean kannAngreifen(Provinz proFrom, Provinz proTo) {
		if(!(proFrom.getBesitzer().equals(proTo.getBesitzer()))   ) { // && getBeziehungen
			return true;
		}
		return false;
	}
	
	//private boolean kannVerschieben(Provinz proFrom, Provinz proTo) {
		
	//}
	
	public void angreifen() {
		// TODO Auto-generated method stub
		
	}

	public void kannVerschieben(boolean[][] beziehungsMatrix) {
				
	}
	
}
