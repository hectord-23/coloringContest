package view;

import java.util.Observable;

import javax.swing.JPanel;

public class AdminPanel extends Observable {
	
	private JPanel myPanel;
	
	public AdminPanel() {
		myPanel = new JPanel();
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
}
