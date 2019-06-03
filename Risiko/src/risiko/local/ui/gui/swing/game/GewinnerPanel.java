package risiko.local.ui.gui.swing.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GewinnerPanel extends JDialog{
	
	public static void main (String[] args) {
		 GewinnerPanel main = new GewinnerPanel("Moumita");
	}

	public GewinnerPanel(String spielerName) {
		setUpUI(spielerName);
	}

	private void setUpUI(String spielerName) {
		this.setTitle("Gewinner");
		this.setLayout(new BorderLayout());
		
		JPanel gewinnerText = new JPanel();
		JLabel gewinnerLabel = new JLabel(spielerName + " HAT GEWONNEN!!!");
		gewinnerText.setPreferredSize(new Dimension(500, 200));
		gewinnerText.add(gewinnerLabel);
		
		JPanel gewinnerBild = new JPanel();
		gewinnerBild.setBackground(Color.blue);
		
		this.add(gewinnerText, BorderLayout.NORTH);
		this.add(gewinnerBild, BorderLayout.CENTER);
		
		this.setSize(500, 700);
		this.setVisible(true);
	}
	
}
