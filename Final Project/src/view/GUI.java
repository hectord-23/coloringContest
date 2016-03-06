package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
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
	 */
	public GUI() {
		super("TCSS 360 - Fist Full of Java");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final IntroPanel introPanel = new IntroPanel();
		final SubmissionPanel submitPanel = new SubmissionPanel();
		final AdminPanel adminPanel = new AdminPanel();
		final TemplatePanel templatePanel = new TemplatePanel();
		
		introPanel.addObserver(this);
		submitPanel.addObserver(this);
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
		setBackground(Color.WHITE);
		setSize(600, 600);
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((int) (kit.getScreenSize().getWidth() / 2 - getWidth() / 2),
				(int) (kit.getScreenSize().getHeight() / 2 - getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		cardPanel.setLayout(myLayout);
		add(cardPanel, BorderLayout.CENTER);
		cardPanel.add(myIntroPanel);
		cardPanel.add(mySubmitPanel);
		cardPanel.add(myAdminPanel);
		cardPanel.add(myTemplatePanel);
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
	 * Main method to be deleted. Used for interface testing purposes.
	 * 
	 * @param theArgs Command line arguments, to be ignored
	 */
	public static void main(String... theArgs) {
		GUI gui = new GUI();
	}

	@Override
	public void update(Observable arg0, Object theObject) {
		if (theObject instanceof String) {
			String input = (String) theObject;
			switch (input) {
				case "INTRO":
				case "SUBMIT":
				case "TEMPLATES":
				case "ADMIN": {
					myLayout.show(cardPanel, input);
					cardPanel.revalidate();
				}
			}
		}
		
	}
}
