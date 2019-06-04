package risiko.local.ui.gui.swing.game;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.TauschenNichtMoeglichException;
import risiko.local.ui.gui.swing.RisikoGameGUI.KartenEintauschenListener;
import risiko.local.valueobjects.Einheitenkarte;

public class EinheitenKartenPanel extends JPanel {
	Risiko risiko;
	JLabel nachricht;
	private int aktuellerSpieler;
	JButton willTauschenButton;
	KartenEintauschenListener kartenEintauschenListener;
	
	public EinheitenKartenPanel(Risiko risiko, int aktuellerSpieler, int parentWidth, int parentHeight, KartenEintauschenListener kartenEintauschenListener) {
		this.kartenEintauschenListener = kartenEintauschenListener;
		this.risiko = risiko;
		this.aktuellerSpieler = aktuellerSpieler;
		setUpUI(parentWidth,parentHeight);
		setUpEvents();
		pruefeTauschen();
	}
	public void pruefeTauschen() {
		try {
			risiko.kannEintauschen(aktuellerSpieler);
			willTauschenButton.setEnabled(true);
		} catch (TauschenNichtMoeglichException e) {
			// nichts passieren
		}
	}
	private void setUpUI(int parentWidth, int parentHeight) {
		
		nachricht = new JLabel("");
		this.add(nachricht);
		willTauschenButton = new JButton("Karten eintauschen");
		willTauschenButton.setEnabled(false);
		this.add(willTauschenButton);
		
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
	
	private void setUpEvents() {
		willTauschenButton.addActionListener(kartenEintauschenListener);
	}
	
	public void setAktuellerSpieler(int spielerID) {
		this.aktuellerSpieler = spielerID;
	}
}
