package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.KeineEinheitenKartenInBesitzException;
import risiko.local.valueobjects.Einheitenkarte;
import risiko.local.valueobjects.Kontinent;

public class EinheitenKartenPanel extends JPanel {
	JLabel nachricht;
	private int aktuellerSpieler;
	
	public EinheitenKartenPanel(int parentWidth, int parentHeight) {
		setUpUI(parentWidth,parentHeight);
	}
	private void setUpUI(int parentWidth, int parentHeight) {
		
		nachricht = new JLabel("");
		this.add(nachricht);
		this.setSize(parentWidth/4,parentHeight);
		this.setPreferredSize(new Dimension(parentWidth/4,parentHeight));
	}
	public void setNachricht(int aktuellerSpieler, Risiko risiko) {
		int soldatCount = 0;
		int reiterCount = 0;
		int kanoneCount = 0;
		Vector<Einheitenkarte> karten = risiko.getKartenVonSpielerGUI(aktuellerSpieler);
		for(Einheitenkarte karte: karten) {
			switch(karte.getTyp()) {
			case "Soldat":
				soldatCount++;
				break;
			case "Reiter":
				reiterCount++;
				break;
			case "Kanone":
				kanoneCount++;
				break;
			}
		}
		String ausgabe = "<html>";
		ausgabe += "Soldaten: " + soldatCount + "<p/>";
		ausgabe += "Reiter: " + reiterCount + "<p/>";
		ausgabe += "Kanonen: " + kanoneCount + "<p/>";
		ausgabe += "</html>";
		nachricht.setText(ausgabe);
		
	}
	public void setAktuellerSpieler(int spielerID) {
		this.aktuellerSpieler = spielerID;
	}
}
