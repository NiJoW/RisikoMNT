package risiko.local.ui.gui.swing;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowCloser extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		Window closingWindow = e.getWindow();
		closingWindow.setVisible(false);
		closingWindow.dispose();
		
		System.exit(0);
	}
}

