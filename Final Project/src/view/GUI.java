package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
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
	 * @param theDB the submission database
	 */
	public GUI(final SubmissionDB theDB) {
		super("TCSS 360 - Fist Full of Java");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final IntroPanel introPanel = new IntroPanel();
		final SubmissionPanel submitPanel = new SubmissionPanel();
		final AdminPanel adminPanel = new AdminPanel();
		final TemplatePanel templatePanel = new TemplatePanel();
		
		introPanel.addObserver(this);
		submitPanel.addObserver(this);
		submitPanel.addObserver(theDB);
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
		myLayout.addLayoutComponent(myIntroPanel, "INTRO");
		myLayout.addLayoutComponent(mySubmitPanel, "SUBMIT");
		myLayout.addLayoutComponent(myTemplatePanel, "TEMPLATES");
		myLayout.addLayoutComponent(myAdminPanel, "ADMIN");
		myLayout.show(cardPanel, "INTRO");
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
		final String[] options = new String[]{"OK", "Cancel"};
		
		passPanel.add(password);
		passPanel.add(pass);
		userPanel.add(username);
		userPanel.add(user);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(userPanel);
		panel.add(passPanel);
		int option = JOptionPane.showOptionDialog(this, panel, "Login",
				JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[1]);
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
		@SuppressWarnings("unused")
		GUI gui = new GUI(new SubmissionDB());
	}
	

	@Override
	public void update(final Observable arg0, final Object theObject) {
		if (theObject instanceof String) {
			String input = (String) theObject;
			switch (input) {
				case "INTRO":
				case "SUBMIT":
				case "TEMPLATES": {
					myLayout.show(cardPanel, input);
					cardPanel.revalidate();
					break;
				} case "ADMIN": {	// dependent on correct credentials being entered
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
