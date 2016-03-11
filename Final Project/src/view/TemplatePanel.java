package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.TemplateDB;

/**
 * This class represents the template panel. Contestants can choose from a list of images
 * and download one or many to their system. Images must be in .jpg format.
 * 
 * @author Jonah Howard
 * @version 8 March 2016
 */
public class TemplatePanel extends Observable {
	
	/** The current panel holding all contents for this page/card. */
	private final JPanel myPanel;
	
	/** The list of templates. */
	private final List<ImageIcon> myTemplates;
	
	/** Scroll pane for the list of templates, allows it to be scrollable. */
	private final JScrollPane myScrollPane;
	
	/** List of all buttons that display an image. */
	private final List<JButton> myButtons;
	
	/** Maps a button to its full sized image. */
	private final Map<JButton, ImageIcon> myIcons;
	
	/** The current file chooser. */
	private final JFileChooser myFileChooser;
	
	/**
	 * Initialize a new Template Panel.
	 */
	public TemplatePanel() {
		myFileChooser = new JFileChooser(new File("./extras/Templates"));
		myIcons = new HashMap<JButton, ImageIcon>();
		myPanel = new JPanel();
		myTemplates = TemplateDB.getImageIconTemplates();
		myButtons = getButtons();
		myScrollPane = new JScrollPane(getImages());
		addComponents();
	}
	
	/**
	 * Set up this panel and add all of its components.
	 */
	private void addComponents() {
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setBackground(Color.WHITE);

		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(30));
		myPanel.add(myScrollPane);
		
		myScrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myScrollPane.setPreferredSize(new Dimension(600, 200));

		
	}
	
	/**
	 * Get the list of buttons that are associated with the images. Each image is a button
	 * and when pressed prompts the user to save it to their system.
	 * 
	 * @return list of the templates/buttons
	 */
	private List<JButton> getButtons() {
		final List<JButton> list = new ArrayList<JButton>();
		for (final ImageIcon current : myTemplates) {
			final Image img = current.getImage();
			// shrink the image to fit on a button
			final ImageIcon smallImg = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			final JButton button = new JButton(smallImg);
			list.add(button);
			myIcons.put(button, current);

		}
		return list;
	}
	
	/**
	 * Returns the panel that contains all of the templates. 
	 * 
	 * @return The panel containing all of the images
	 */
	private JPanel getImages() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout((int) Math.ceil((double) myTemplates.size() / 3), 3, 5, 20));
		panel.setBackground(Color.WHITE);
		for (final JButton button : myButtons) {
			button.setBackground(Color.WHITE);
			button.setBorder(BorderFactory.createEmptyBorder());
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent theEvent) {
					int result = myFileChooser.showSaveDialog(myPanel);
					if (result == JFileChooser.APPROVE_OPTION) {
						File f = myFileChooser.getSelectedFile();
						Image img = myIcons.get(button).getImage();
						BufferedImage bi = (BufferedImage) img;
						Graphics g = bi.getGraphics();
						g.drawImage(bi, 0, 0, null);
						try {
								ImageIO.write(bi, "jpg", f);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			});
			panel.add(button);
		}
		return panel;
	}
	
	/**
	 * Get the current panel.
	 * 
	 * @return the current panel
	 */
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