package view;

import java.awt.Panel;
import java.util.Observable;

import javax.swing.JPanel;

public class SubmissionPanel extends Observable {
	private final JPanel myPanel;
	
	public SubmissionPanel() {
		myPanel = new JPanel();
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
}
