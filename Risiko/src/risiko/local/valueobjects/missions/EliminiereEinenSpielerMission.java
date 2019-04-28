package risiko.local.valueobjects.missions;

import risiko.local.valueobjects.Spieler;

public class EliminiereEinenSpielerMission extends Mission {

	private Spieler target;
	
	public EliminiereEinenSpielerMission() {
		
	}
	
	public void setTarget(Spieler target) {
		this.target = target;
	}
	
	@Override
	public boolean isErfuellt() {
		// TODO Auto-generated method stub
		return false;
	}

}
