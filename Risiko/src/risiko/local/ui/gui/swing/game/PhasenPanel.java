package risiko.local.ui.gui.swing.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import risiko.local.domain.Risiko;
import risiko.local.ui.gui.swing.RisikoGameGUI.PhaseBeendenListener;

public class PhasenPanel extends JPanel {

    Risiko risiko;
    InformationsPanel informationsPanel;

    GridBagConstraints c;
    PhaseEinheitenVerteilen phaseEins;
    PhaseAngriff phaseZwei;
    PhaseEinheitenVerschieben phaseDrei;
    KartenTauschPanel phaseTauschen;
    int aktuellerSpieler;

    int phasenID;
    boolean neuesSpiel;
    PhaseBeendenListener phaseBeendenListener;
    KartenPanel kartenPanel;

    int width = 280;
    int height = 525;


    public PhasenPanel(Risiko risiko, PhaseBeendenListener phaseBeendenListener, InformationsPanel anweisungsPanel, KartenPanel kartenPanel, boolean neuesSpiel) {

        this.risiko = risiko;
        this.informationsPanel = anweisungsPanel;
        this.kartenPanel = kartenPanel;
        this.phaseBeendenListener = phaseBeendenListener;
        this.neuesSpiel = neuesSpiel;
        setUpUI();

        ereignisErzeugt();
    }

    private void setUpUI() {

        this.setSize(width,height);
        this.setPreferredSize(new Dimension(width,height));

        phaseEins = new PhaseEinheitenVerteilen(risiko, informationsPanel, aktuellerSpieler, new InitialeRundeBeendet());
        this.add(phaseEins);
        phaseZwei = new PhaseAngriff(risiko, informationsPanel, aktuellerSpieler);
        this.add(phaseZwei);
        phaseDrei = new PhaseEinheitenVerschieben(risiko, informationsPanel, aktuellerSpieler);
        this.add(phaseDrei);
        phaseTauschen = new KartenTauschPanel(risiko, aktuellerSpieler, phaseEins);
        this.add(phaseTauschen);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        if(!neuesSpiel) {
            aktuellerSpieler++; //TODO wurde ggf schon erhoeht? bitte nachgucken
            risiko.berechneNeueEinheiten(aktuellerSpieler); //Nicht huebsch, aber functioniert
            phaseEins.beginneSpiel(aktuellerSpieler);
        }else {
            intitialeEinheitenVerteilen(0);
        }
    }


    private void intitialeEinheitenVerteilen(int spieler) {
//        System.out.println("Spieler "+spieler + " hat Farbe "+ risiko.getSpieler(spieler).getFarbe() );
        int spielerAnzahl = risiko.getSpielerAnzahl();
        if(spieler<spielerAnzahl) {
            phaseEins.initialesVerteilen(spieler);
            kartenPanel.setAktuellerSpieler(spieler);
        }else {
            risiko.berechneNeueEinheiten(0);
            kartenPanel.setAktuellerSpieler(0);
            phaseEins.beginneSpiel(0);
        }

    }

    public class InitialeRundeBeendet implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("InitialeListener wurde aktiviert");
            int nextSpieler = Integer.parseInt(e.getActionCommand());
            intitialeEinheitenVerteilen(nextSpieler);
        }
    }

    public void ereignisErzeugt() {
        phaseEins.setUpEvents(phaseBeendenListener, phaseZwei);
        phaseZwei.setUpEvents(phaseBeendenListener, phaseDrei);
        phaseDrei.setUpEvents(phaseBeendenListener, phaseEins);
    }

    public void setPhase(int phasenID) {
        this.phasenID = phasenID;
    }

    public void setClickedProvinz(int provinzIDByColor) {
        if(provinzIDByColor == 42) {
            return;
        }
        System.out.println("Provinz: " + risiko.getProvinz(provinzIDByColor).getName());
        System.out.println("phase: "+ phasenID);
        System.out.println("Anzahl: " + risiko.getProvinzenVonSpieler(aktuellerSpieler).size());
        switch(this.phasenID) {
            case 0:
            case 1:
                phaseEins.setProvinz(provinzIDByColor);
                break;
            case 2:
                phaseZwei.setProvinz(provinzIDByColor);
                break;
            case 3:
                phaseDrei.setProvinz(provinzIDByColor);
                break;
        }
    }

    public void setAktuellerSpieler(int spielerID) {
        aktuellerSpieler = spielerID;
        phaseEins.setAktuellerSpieler(spielerID);
        phaseZwei.setAktuellerSpieler(spielerID);
        phaseDrei.setAktuellerSpieler(spielerID);        
    }

    public void setUpKartenTausch() {
        System.out.println("Karten tauschen enrichten");
        phaseEins.setVisible(false);
        phaseTauschen.setVisible(true);
    }

}
