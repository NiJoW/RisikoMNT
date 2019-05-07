package risiko.local.ui.gui.awt;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import risiko.local.domain.Risiko;
import risiko.local.ui.cui.RisikoClientCUI;

public class RisikoClientGUI extends Frame implements ActionListener {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoClientGUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
		initialize();
	}
	
	public static void main(String[] args) {
		RisikoClientGUI gui;
		try {
			gui = new RisikoClientGUI();
			gui.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() throws IOException {
		//spielerRegistrierung();
		//spielMenue();
	}
	
	private void initialize() {
		this.setLayout(new GridBagLayout());
		Panel welcomePanel = new Panel();
		Panel anmeldePanel = new Panel();
		
		
		GridBagLayout anmeldeLayout = new GridBagLayout();
		Label bitteAnmelden = new Label("Bitte Spieler anmelden:");
		//anmeldeLayout.setConstraints(comp, constraints);
		anmeldePanel.setLayout(anmeldeLayout);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
