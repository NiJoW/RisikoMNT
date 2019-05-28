package risiko.local.ui.gui.swing.game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import risiko.local.ui.gui.swing.FaceLayout;

public class Labor extends JFrame {

	   public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable(){
	            public void run() {
	                new FaceLayout();
	            }
	        });
	    }
}

