package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
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

public class TemplatePanel extends Observable {
	private final JPanel myPanel;
	private final List<ImageIcon> myTemplates;
	private final JScrollPane myScrollPane;
	private final List<JButton> myButtons;
	private final Map<JButton, ImageIcon> myIcons;
	private final JFileChooser myFileChooser;
	
	public TemplatePanel() {
		myFileChooser = new JFileChooser(new File("./extras/Downloads"));
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
	
	private List<JButton> getButtons() {
		final List<JButton> list = new ArrayList<JButton>();
		for (final ImageIcon current : myTemplates) {
			final Image img = current.getImage();
			final ImageIcon smallImg = new ImageIcon(img.getScaledInstance(150, 150, Image.SCALE_DEFAULT));
			final JButton button = new JButton(smallImg);
			list.add(button);
			myIcons.put(button, current);

		}
		
		return list;
	}
	
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
					myFileChooser.showSaveDialog(myPanel);
					File f = myFileChooser.getSelectedFile();
					Image img = myIcons.get(button).getImage();
					try {
						ImageIO.write((RenderedImage) myIcons.get(button).getImage(), ".jpg", f);// new File("extras/Downloads/template.jpg"));
					} catch (IOException e) {
						e.printStackTrace();
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