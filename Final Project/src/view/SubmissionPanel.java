package view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SubmissionPanel extends Observable {
	private final JPanel myPanel;
	
	public SubmissionPanel() {
		myPanel = new JPanel();
		myPanel.add(new JLabel("Hello"));
		myPanel.setBackground(Color.RED);
		myPanel.setSize(500,500);
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
}
