package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TemplateDB;

public class TemplatePanel extends Observable {
	private final JPanel myPanel;
	private final List<ImageIcon> myTemplates;
	private final List<JLabel> myLabels;
	
	public TemplatePanel() {
		myPanel = new JPanel();
		myTemplates = TemplateDB.getImageIconTemplates();
		myLabels = new ArrayList<JLabel>();
		addLabels();
		addComponents();
	}
	
	/**
	 * Take all of the images from the templates and add them to the label list as smaller images.
	 */
	private void addLabels() {
		for (final ImageIcon current : myTemplates) {
			final Image img = current.getImage();
			myLabels.add(new JLabel(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT))));
		}
	}
	
	/**
	 * Set up this panel and add all of its components.
	 */
	private void addComponents() {
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.add(createHeader());
		myPanel.setBackground(Color.WHITE);
		
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
	/**
	 * Create the header panel.
	 * 
	 * @return the header panel.
	 */
	private JPanel createHeader() {
		final JPanel panel = new JPanel();
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
		final JButton button = new JButton("Go Home");
		
		assignGoHome(button);

		left.setFont(font);
		center.setFont(font);
		
		panel.setMaximumSize(new Dimension(600, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		panel.add(left);
		panel.add(center);
		panel.add(button);
		
		return panel;
	}
	
	/**
	 * Assign an action listener for the Go Home and Cancel buttons
	 * 
	 * @param button the button being assigned
	 */
	private void assignGoHome(final JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.INTRO);
				clearChanged();
			}
		}); 
	}
}