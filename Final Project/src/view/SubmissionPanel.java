package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class represents the submission panel where the user can enter in their information,
 * upload an image, and submit an entry for the coloring contest.
 * 
 * @author Jonah Howard
 * @version 3 March 2016
 */
public class SubmissionPanel extends Observable {
	
	/** The font for this panel. */
	public static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	
	/** Represents the length of all of the text boxes. */
	public static final int DEFAULT_CHARACTERS = 20;
	
	/** Text field for the first name. */
	private final JTextField myFirstName;
	
	/** The text field for the last name. */
	private final JTextField myLastName;
	
	/** The text field for the age. */
	private final JTextField myAge;
	
	/** The text field for the phone. */
	private final JTextField myPhone;
	
	/** The text field for the phone. */
	private final JTextField myEmail;
	
	/** The text field for the library ID number. */
	private final JTextField myID;
	
	/** The file chooser to upload an image. */
	private final JFileChooser myFileChooser;
	
	/** The go home button. */
	private final JButton myGoHome;
	
	/** Check box indicating whether the user has read the terms and conditions. */
	private final JCheckBox myCheckBox;
	
	/** A list of all of the text fields. */
	private final List<JTextField> myTextFields;
	
	/** Holds and displays a small preview of the currently uploaded image. */
	private final JLabel myIconLabel;
	
	/** The file for the current image. */
	private File myFile;
	
	/** The currently selected image, null if no image has been selected. */
	private ImageIcon myImage = null;
	
	/** The current panel. */
	private final JPanel myPanel;
	
	/**
	 * Initialize a new submissions panel.
	 */
	public SubmissionPanel() {
		myPanel = new JPanel();
		myFirstName = new JTextField(DEFAULT_CHARACTERS);
		myLastName = new JTextField(DEFAULT_CHARACTERS);
		myAge = new JTextField(DEFAULT_CHARACTERS);
		myPhone = new JTextField(DEFAULT_CHARACTERS);
		myEmail = new JTextField(DEFAULT_CHARACTERS);
		myID = new JTextField(DEFAULT_CHARACTERS);
		myFileChooser = new JFileChooser(".\\extras\\templates");
		myTextFields = new ArrayList<JTextField>();
		myCheckBox = new JCheckBox();
		myIconLabel = new JLabel();
		myGoHome = new JButton("Go Home");
		addTextFields();
		addComponents();
	}
	
	/**
	 * Adds all of the components to this panel.
	 */
	private void addComponents() {
		final JPanel panel = contactInfo();

		myCheckBox.setBackground(Color.WHITE);
		myFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setBackground(Color.WHITE);
		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(10));
		myPanel.add(panel);
		myPanel.add(Box.createVerticalStrut(10));
		myPanel.add(createUpload());
	}
	
	/**
	 * Create and return the panel that  holds the contact information for a submission. 
	 * 
	 * @return panel for entering contact information
	 */
	private JPanel contactInfo() {
		final JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.setBackground(Color.WHITE);
		panel.add(addPanel("First Name: "));
		panel.add(addPanel("Last Name: "));
		panel.add(addPanel("          Age: "));
		panel.add(addPanel("       Phone: "));
		panel.add(addPanel("        Email: "));
		panel.add(addPanel("Library ID#: "));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}
	
	/**
	 * Creates and returns the panel allowing the user to select and upload an image.
	 * 
	 * @return the panel containing the interface to allow the user to upload an image
	 */
	private JPanel createUpload() {
		final JPanel panel = new JPanel();
		final JPanel filePanel = new JPanel(new FlowLayout());
		final JPanel bottom = new JPanel(new FlowLayout());
		final JPanel termsConditions = new JPanel();
		
		final JLabel filler = new JLabel();
		final JLabel top = new JLabel("Upload an Image");
		final JLabel terms = new JLabel("I have read and understand the");
		
		final JButton browse = new JButton("Browse");
		final JButton conditions = new JButton("<html><u>Terms and Conditions<html>");
		final JButton submit = new JButton("Submit");
		final JButton cancel = new JButton("Cancel");
		
		// Format all components
		addBrowseListener(browse);
		assignSubmit(submit);
		assignGoHome(cancel);
		
		bottom.add(submit);
		bottom.add(cancel);
		bottom.setBackground(Color.WHITE);
		
		filePanel.setSize(new Dimension(600, 200));
		filePanel.add(top);
		filePanel.add(myIconLabel);
		filePanel.add(browse);
		filePanel.setBackground(Color.WHITE);
		
		top.setFont(LABEL_FONT);
		terms.setFont(LABEL_FONT);
		
		conditions.setFont(LABEL_FONT);
		conditions.setBackground(Color.WHITE);
		conditions.setBorder(BorderFactory.createEmptyBorder());
		conditions.setSize(new Dimension(100, 25));
		conditions.setForeground(Color.BLUE);
		
		termsConditions.setBackground(Color.WHITE);
		termsConditions.add(myCheckBox);
		termsConditions.add(terms);
		termsConditions.add(filler);
		termsConditions.add(conditions);
		
		// Add all components to the main panel for this section
//		panel.add(top);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(filePanel);
		panel.add(termsConditions);
		panel.add(bottom);
		
		return panel;
	}
	
	/**
	 * Sets the action listener for the submit button.
	 * 
	 * @param button the button being assigned the action listener
	 */
	private void assignSubmit(final JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				boolean emptyField = false;
				// Check for empty fields and set cell to red if one is found
				for (final JTextField current : myTextFields) {
					if (current.getText().equals("")) {
						emptyField = true;
						current.setBackground(Color.RED);
					}
				}
				if (emptyField || !myCheckBox.isSelected() || myImage == null) { // empty field
					JOptionPane.showMessageDialog(null, 
                            "<html>Missing information!<br>Please enter all fields,<br>read the"
                            + " terms and conditions,<br>and upload an image<html>",
                            "Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					final Object[] contestant = {myFirstName.getText(), myLastName.getText(), 
							myAge.getText(), myEmail.getText(), myPhone.getText(), 
							myID.getText(), myFile.toString()};
					setChanged();
					notifyObservers(contestant);
					clearChanged();
					URI temp  = myFile.toURI();
					JOptionPane.showMessageDialog(myPanel, "Submission successful, "
							+ "going back to the home page.", "Submission Successful!", 
							JOptionPane.INFORMATION_MESSAGE);
					// Revert red cells and make the text fields empty
					for (final JTextField current : myTextFields) {
						current.setBackground(Color.WHITE);
						current.setText("");
					}
					// Reset all fields
					myImage = null;
					myCheckBox.setSelected(false);
					myIconLabel.setIcon(null);
					myGoHome.doClick();
				}
			}
		});
	}
	
	/**
	 * Sets the action listener for the Browse button. 
	 * 
	 * @param browse the button being assigned the action listener
	 */
	private void addBrowseListener(final JButton browse) {
		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						myFile = myFileChooser.getSelectedFile();
						myImage = new ImageIcon(ImageIO.read(myFile));
						Image img = ImageIO.read(myFile);
						myIconLabel.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
					} catch (final IOException e) {
		                JOptionPane.showMessageDialog(null, 
                                "The selected file did not contain an image!",
                                "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	/**
	 * Adds all of the text fields to the list of text fields.
	 */
	private void addTextFields() {
		myTextFields.add(myFirstName);
		myTextFields.add(myLastName);
		myTextFields.add(myAge);
		myTextFields.add(myEmail);
		myTextFields.add(myPhone);
		myTextFields.add(myID);
	}
	
	/**
	 * Returns a panel containing the passed label and a text field.
	 * 
	 * @param theLabel the label being assigned
	 * @return the panel with its label and text field
	 */
	private JPanel addPanel(final String theLabel) {
		final JPanel panel = new JPanel();
		final JLabel label = new JLabel(theLabel);
		label.setFont(LABEL_FONT);
		panel.setBackground(Color.WHITE);
		panel.add(label);
		switch (theLabel) {
			case "       Phone: ": {
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
			} case "          Age: ": {
				panel.add(myAge);
				break;
			}
		}
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
				// Reset all fields
				for (final JTextField current : myTextFields) {
					current.setText("");
					current.setBackground(Color.WHITE);
				}
				myImage = null;
				myCheckBox.setSelected(false);
				myIconLabel.setIcon(null);
			}
		}); 
	}
	
	/**
	 * Create the header panel.
	 * 
	 * @return the header panel.
	 */
	private JPanel createHeader() {
		final JPanel panel = new JPanel();
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		
		assignGoHome(myGoHome);

		left.setFont(font);
		center.setFont(font);
		
		panel.setMaximumSize(new Dimension(600, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		panel.add(left);
		panel.add(center);
		panel.add(myGoHome);
		return panel;
	}
	
	/**
	 * Get the submission panel.
	 * 
	 * @return the submission panel
	 */
	public JPanel getPanel() {
		return myPanel;
	}
	
}
