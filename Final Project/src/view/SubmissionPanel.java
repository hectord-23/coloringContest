package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubmissionPanel extends Observable {
	public static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
	public static final int DEFAULT_CHARACTERS = 15;
	
	private final JTextField myFirstName;
	private final JTextField myLastName;
	private final JTextField myAge;
	private final JTextField myPhone;
	private final JTextField myEmail;
	private final JTextField myID;
	private final JFileChooser myFileChooser;
	
	private final JPanel myPanel;
	
	public SubmissionPanel() {
		myPanel = new JPanel();
		myFirstName = new JTextField(DEFAULT_CHARACTERS);
		myLastName = new JTextField(DEFAULT_CHARACTERS);
		myAge = new JTextField(DEFAULT_CHARACTERS);
		myPhone = new JTextField(DEFAULT_CHARACTERS);
		myEmail = new JTextField(DEFAULT_CHARACTERS);
		myID = new JTextField(DEFAULT_CHARACTERS);
		myFileChooser = new JFileChooser();
		
		setUp();
	}
	
	private void setUp() {
		myPanel.setBackground(Color.WHITE);
//		myPanel.setLayout(new BorderLayout());
		myPanel.add(createHeader(), BorderLayout.NORTH);
//		((BorderLayout) myPanel.getLayout()).setVgap(15);
		// Either this way
		final JPanel panel = new JPanel(new GridLayout(3, 2));
		panel.setBackground(Color.WHITE);
		panel.add(addPanel("First Name: "));
		panel.add(addPanel("Last Name: "));
		panel.add(addPanel("           Age: "));
		panel.add(addPanel("      Phone: "));
		panel.add(addPanel("        Email: "));
		panel.add(addPanel("Library ID#: "));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myPanel.add(panel);
		// Or this way
//		myPanel.add(createCenter());
		
	}
	
	private JPanel createCenter() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JPanel names = createNamesPanel();
		final JPanel contact = createContactPanel();
		final JPanel upload = new JPanel();
		final JLabel label = new JLabel("How may we contact you?");
		
		panel.setBackground(Color.WHITE);
		panel.add(Box.createVerticalStrut(15));
		panel.add(names);
		panel.add(Box.createVerticalStrut(25));
		panel.add(contact);
		return panel;
	}
	
	private JPanel addPanel(final String theLabel) {
		final JPanel panel = new JPanel();
		final JLabel label = new JLabel(theLabel);
		label.setFont(LABEL_FONT);
		panel.setBackground(Color.WHITE);
		panel.add(label);
		switch (theLabel) {
			case "      Phone: ": {
				panel.add(myPhone);
				break;
			} case "        Email: ": {
				panel.add(myEmail);
				break;
			} case "Library ID#: ": {
				panel.add(myID);
				break;
			} case "First Name: ": {
				panel.add(myFirstName);
				break;
			} case "Last Name: ": {
				panel.add(myLastName);
				break;
			} case "           Age: ": {
				panel.add(myAge);
				break;
			}
		}
		return panel;
	}
	
	private JPanel createContactPanel() {
		final JPanel panel = new JPanel();
		final JPanel first = new JPanel(new FlowLayout());
		final JPanel second = new JPanel(new FlowLayout());
		final JPanel third = new JPanel(new FlowLayout());
		
		final JLabel phone = new JLabel("Phone: ");
		final JLabel email = new JLabel("Email: ");
		final JLabel libId = new JLabel("Library ID#: ");
		
		
		phone.setFont(LABEL_FONT);
		email.setFont(LABEL_FONT);
		libId.setFont(LABEL_FONT);
		
		first.setBackground(Color.WHITE);
		first.add(phone);
		first.add(myPhone);
		
		second.setBackground(Color.WHITE);
		second.add(email);
		second.add(myEmail);
		
		third.setBackground(Color.WHITE);
		third.add(libId);
		third.add(myID);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.add(first);
		panel.add(second);
		panel.add(third);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		return panel;
	}
	
	/**
	 * Adds all the components to the names panel. 
	 * 
	 * @return the panel that stores contestant's name and age
	 */
	private JPanel createNamesPanel() {
		final JPanel panel = new JPanel();
		final JPanel first = new JPanel(new FlowLayout());
		final JPanel second = new JPanel(new FlowLayout());
		final JPanel third = new JPanel(new FlowLayout());
		final JPanel filler = new JPanel();
		
		final JLabel firstName = new JLabel("First Name: ");
		final JLabel lastName = new JLabel("Last Name: ");
		final JLabel age = new JLabel("Age: ");

		firstName.setFont(LABEL_FONT);
		lastName.setFont(LABEL_FONT);

		first.add(firstName);
		first.add(myFirstName);
		first.setBackground(Color.WHITE);

		second.add(lastName);
		second.add(myLastName);
		second.setBackground(Color.WHITE);
		
		third.add(age);
		third.add(myAge);
		third.setBackground(Color.WHITE);

		panel.setLayout(new GridLayout(2, 2));
		panel.add(first);
		panel.add(filler);
		panel.add(second);
		panel.add(third);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		filler.setBackground(Color.WHITE);
		return panel;
	}
	
	private JPanel createHeader() {
		final JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 125, 0));
		
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
		final JButton button = new JButton("Go Home");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.INTRO);
				clearChanged();
			}
		}); 
		left.setFont(font);
		center.setFont(font);
		
		panel.add(left);
		panel.add(center);
		panel.add(button);
		return panel;
	}
	
	public JPanel getPanel() {
		return myPanel;
	}
	
}
