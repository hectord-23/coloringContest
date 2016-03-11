package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.SubmissionDB;
import model.TemplateDB;
	
/**
 * This class is the main driver for the coloring contest and displays the user interface.
 * 
 * @author Jonah Howard
 * @version 10 March 2016
 */
public class GUI extends JFrame implements Observer {
	
	/** A generated Serial Version UID. */
	private static final long serialVersionUID = 1L;
	
	/** The font for the page headers. */
	public static final Font HEADER_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
	/** Represents the introduction page card. */
	public static final String INTRO = "INTRO";
	
	/** Represents the submissions page card. */
	public static final String SUBMIT = "SUBMIT";
	
	/** Represents the templates page card. */
	public static final String TEMPLATES = "TEMPLATES";
	
	/** Represents the administrator page card. */
	public static final String ADMIN = "ADMIN";
	
	/** The introduction page. */
	private final JPanel myIntroPanel;
	
	/** The submission page. */
	private final JPanel mySubmitPanel;
	
	/** The administrator page. */
	private final JPanel myAdminPanel;
	
	/** The template page. */
	private final JPanel myTemplatePanel;
	
	/** The current layout for this frame. */
	private final CardLayout myLayout;
	
	/** Holds the layout for this frame. */
	private final JPanel cardPanel;

	/**
	 * Initializes a new contest GUI.
	 * 
	 * @param subDB the submission database 
	 */
	public GUI(SubmissionDB subDB) {
		super("TCSS 360 - Fist Full of Java");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final IntroPanel introPanel = new IntroPanel();
		final SubmissionPanel submitPanel = new SubmissionPanel(subDB);
		final AdminPanel adminPanel = new AdminPanel(subDB);
		final TemplatePanel templatePanel = new TemplatePanel();
		
		introPanel.addObserver(this);
		submitPanel.addObserver(this);
		submitPanel.addObserver(adminPanel);
		adminPanel.addObserver(this);
		templatePanel.addObserver(this);
		
		myIntroPanel = introPanel.getPanel();
		mySubmitPanel = submitPanel.getPanel();
		myAdminPanel = adminPanel.getPanel();
		myTemplatePanel = templatePanel.getPanel();
		myLayout = new CardLayout(5, 5);
		cardPanel = new JPanel();
		cardPanel.setBackground(Color.WHITE);
		
		
		setUp();
		setUpLayoutManager();
		setVisible(true);
	}

	/**
	 * Sets up the interface.
	 */
	private void setUp() {
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setBackground(Color.WHITE);
		setSize(600, 600);
		setLocation((int) (kit.getScreenSize().getWidth() / 2 - getWidth() / 2),
				(int) (kit.getScreenSize().getHeight() / 2 - getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		cardPanel.setLayout(myLayout);
		cardPanel.add(myIntroPanel);
		cardPanel.add(mySubmitPanel);
		cardPanel.add(myAdminPanel);
		cardPanel.add(myTemplatePanel);
		add(cardPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Sets up the layout manager.
	 */
	private void setUpLayoutManager() {
		myLayout.addLayoutComponent(myIntroPanel, GUI.INTRO);
		myLayout.addLayoutComponent(mySubmitPanel, GUI.SUBMIT);
		myLayout.addLayoutComponent(myTemplatePanel, GUI.TEMPLATES);
		myLayout.addLayoutComponent(myAdminPanel, GUI.ADMIN);
		myLayout.show(cardPanel, GUI.INTRO); // Set which panel to display upon startup
		cardPanel.revalidate();
	}
	
	/**
	 * Creates the dialog that prompts the user to enter administrator credentials.
	 * 
	 * @return true if correct login information was entered
	 */
	private boolean createLoginDialog() {
		final JPanel panel = new JPanel();
		final JPanel passPanel = new JPanel(new FlowLayout());
		final JPanel userPanel = new JPanel(new FlowLayout());
		final JLabel username = new JLabel("Username: ");
		final JLabel password = new JLabel("Passowrd: ");
		final JTextField user = new JTextField(10);
		final JPasswordField pass = new JPasswordField(10);
		final String[] options = new String[]{"Login", "Cancel"};
		
		passPanel.add(password);
		passPanel.add(pass);
		userPanel.add(username);
		userPanel.add(user);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(userPanel);
		panel.add(passPanel);
		int option = JOptionPane.showOptionDialog(this, panel, "Login",
				JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);
		final String parsedPassword = new String(pass.getPassword());
		// Verify that correct credentials were entered
		if (option == 0 && AdminPanel.PASSWORD.equals(parsedPassword) 
				&& AdminPanel.USERNAME.equals(user.getText())) {
			return true;
		} else if (option == 0){						
			JOptionPane.showMessageDialog(this, "Incorrect login information, please try again");
		}
		
		return false;
	}
	
	/**
	 * Main method to be deleted. Used for interface testing purposes.
	 * 
	 * @param theArgs Command line arguments, to be ignored
	 */
	public static void main(final String... theArgs) {
		TemplateDB db = new TemplateDB();
		SubmissionDB subDB = new SubmissionDB();
//		List<Object[]> list = subDB.recallSubmissions();
//		for(int i = 0; i < list.size(); i++) {
//			Object[] contestantData = list.get(i);
//			String name = (String) contestantData[0];
//			String lastName = (String) contestantData[1];
//			String age = (String) contestantData[2];
//			String email = (String) contestantData[3];
//			String phone = (String) contestantData[4];
//			String id = (String) contestantData[5];
//			Image myImage = (Image) contestantData[6];
////			System.out.println(name + " " + lastName + " " + age + " " + email + " " + phone + " " + id);
//		}
		@SuppressWarnings("unused")
		GUI gui = new GUI(subDB);
	}
	

	@Override
	public void update(final Observable arg0, final Object theObject) {
		if (theObject instanceof String) {
			String input = (String) theObject;
			switch (input) {
				case GUI.INTRO:
				case GUI.SUBMIT:
				case GUI.TEMPLATES: {
					myLayout.show(cardPanel, input);
					cardPanel.revalidate();
					break;
				} case GUI.ADMIN: {	// dependent on correct credentials being entered
					if (createLoginDialog()) {
						myLayout.show(cardPanel, input);
						cardPanel.revalidate();
					}
					break;
				}
			}
		}
		
	}
}
