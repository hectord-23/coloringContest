package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

public class SubmissionPanel extends Observable {
	public static final Font LABEL_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	public static final int DEFAULT_CHARACTERS = 20;
	
	private final JTextField myFirstName;
	private final JTextField myLastName;
	private final JTextField myAge;
	private final JTextField myPhone;
	private final JTextField myEmail;
	private final JTextField myID;
	private final JTextField myFileTextField;
	private final JFileChooser myFileChooser;
	private final JCheckBox myCheckBox;
	private final List<JTextField> myTextFields;
	private ImageIcon myImage = null;
	private String myFile;
	
	private final JPanel myPanel;
	
	public SubmissionPanel() {
		myPanel = new JPanel();
		myFirstName = new JTextField(DEFAULT_CHARACTERS);
		myLastName = new JTextField(DEFAULT_CHARACTERS);
		myAge = new JTextField(DEFAULT_CHARACTERS);
		myPhone = new JTextField(DEFAULT_CHARACTERS);
		myEmail = new JTextField(DEFAULT_CHARACTERS);
		myID = new JTextField(DEFAULT_CHARACTERS);
		myFileTextField = new JTextField(DEFAULT_CHARACTERS);
		myFileChooser = new JFileChooser("./extras/templates");
		myTextFields = new ArrayList<JTextField>();
		myCheckBox = new JCheckBox();
		addTextFields();
		setUp();
	}
	
	private void setUp() {
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setBackground(Color.WHITE);
		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(10));
		myCheckBox.setBackground(Color.WHITE);
		myFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
//		((BorderLayout) myPanel.getLayout()).setVgap(15);
		// Either this way
		final JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.setBackground(Color.WHITE);
		panel.add(addPanel("First Name: "));
		panel.add(addPanel("Last Name: "));
		panel.add(addPanel("          Age: "));
		panel.add(addPanel("       Phone: "));
		panel.add(addPanel("        Email: "));
		panel.add(addPanel("Library ID#: "));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myPanel.add(panel);
		myPanel.add(Box.createVerticalStrut(10));
//		myPanel.add(new JLabel("Please enter your information below"));
		// Or this way
		myPanel.add(createUpload());
	}
	
	private JPanel createUpload() {
		final JPanel panel = new JPanel(new BorderLayout());
		final JPanel filePanel = new JPanel(new FlowLayout());
		final JPanel bottom = new JPanel(new FlowLayout());
		final JPanel termsConditions = new JPanel();
		
		final JLabel filler = new JLabel();
		final JLabel file = new JLabel("File");
		final JLabel top = new JLabel("Upload a File");
		final JLabel image = new JLabel();
		final JLabel terms = new JLabel("I have read and understand the");
		
		final JButton browse = new JButton("Browse");
		final JButton conditions = new JButton("<html><u>Terms and Conditions<html>");
		final JButton submit = new JButton("Submit");
		final JButton cancel = new JButton("Cancel");
		
		addBrowseListener(browse, image);
		assignSubmit(submit);
		assignGoHome(cancel);
		
		bottom.add(submit);
		bottom.add(cancel);
		bottom.setBackground(Color.WHITE);
		
		filePanel.setSize(new Dimension(600, 200));
		filePanel.add(file);
		filePanel.add(image);
		filePanel.add(browse);
		filePanel.setBackground(Color.WHITE);
		
		file.setFont(LABEL_FONT);
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
		
		panel.add(top);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.add(filePanel);
		panel.add(termsConditions);
		panel.add(bottom);
		
		return panel;
	}
	
	private void assignSubmit(final JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				boolean emptyField = false;
				for (final JTextField current : myTextFields) {
					if (current.getText().equals("")) {
						emptyField = true;
						current.setBackground(Color.RED);
					}
				}
				if (emptyField || !myCheckBox.isSelected() || myImage == null) {
					JOptionPane.showMessageDialog(null, 
                            "<html>Missing information!<br>Please enter all fields,<br>read the"
                            + " terms and conditions,<br>and upload an image<html>",
                            "Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					final Object[] info = {myFirstName.getText(), myLastName.getText(), myAge.getText(),
							myEmail.getText(), myPhone.getText(), myID.getText(), myImage};
					setChanged();
					notifyObservers(info);
					clearChanged();
				}
			}
		});
	}
	
	private void addBrowseListener(final JButton browse, final JLabel image) {
		browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				if (myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						myFile = myFileChooser.getSelectedFile().getPath();
						File file = myFileChooser.getSelectedFile();
						myImage = new ImageIcon(ImageIO.read(file));
						Image img = ImageIO.read(file);
						image.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
					} catch (final IOException e) {
		                JOptionPane.showMessageDialog(null, 
                                "The selected file did not contain an image!",
                                "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void addTextFields() {
		myTextFields.add(myFirstName);
		myTextFields.add(myLastName);
		myTextFields.add(myAge);
		myTextFields.add(myEmail);
		myTextFields.add(myPhone);
		myTextFields.add(myID);
	}
	
	private JPanel createCenter() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JPanel names = createNamesPanel();
		final JPanel contact = createContactPanel();
		final JPanel upload = new JPanel();
		
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
	
	private JPanel createHeader() {
		final JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(600, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
		final JButton button = new JButton("Go Home");
		assignGoHome(button);

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
