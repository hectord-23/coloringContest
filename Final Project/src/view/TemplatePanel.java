package view;

import java.awt.Panel;
import java.util.Observable;

import javax.swing.JPanel;

public class TemplatePanel extends Observable {
	private final JPanel myPanel;
	
	public TemplatePanel() {
		myPanel = new JPanel();
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
}