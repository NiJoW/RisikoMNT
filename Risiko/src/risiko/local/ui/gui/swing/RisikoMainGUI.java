package risiko.local.ui.gui.swing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import risiko.local.domain.Risiko;
import risiko.local.domain.exceptions.SpielNichtVorhandenException;
import risiko.local.domain.exceptions.SpielerNichtTeilDesSpielsException;

//import javax.swing.*;

public class RisikoMainGUI {
	private Risiko risiko;
	private BufferedReader in;
	
	public RisikoMainGUI() {
		risiko = new Risiko();
		in = new BufferedReader(new InputStreamReader(System.in));
		RisikoClientGUI gui = new RisikoClientGUI(risiko);
	}
	
	public static void main(String[] args) {
		RisikoMainGUI main;
		//try {
			main = new RisikoMainGUI();
		//	main.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	
}
