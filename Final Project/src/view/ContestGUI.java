package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
public class ContestGUI extends JFrame implements Observer {
	private final JPanel myIntroPanel;
	private final JPanel mySubmitPanel;
	private final JPanel myAdminPanel;
	private final JPanel myTemplatePanel;
	private final CardLayout myLayout;
	
	public ContestGUI() {
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
		myLayout = new CardLayout();
		
		setUp();
		setUpLayoutManager();
	}

	private void setUp() {
		setLayout(myLayout);
		setBackground(Color.WHITE);
		final Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((int) (kit.getScreenSize().getWidth() / 2 - getWidth() / 2),
				(int) (kit.getScreenSize().getHeight() / 2 - getHeight() / 2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		add(myIntroPanel);
		setVisible(true);
	}
	
	private void setUpLayoutManager() {
		myLayout.addLayoutComponent(myIntroPanel, "INTRO");
		myLayout.addLayoutComponent(mySubmitPanel, "SUBMIT");
		myLayout.addLayoutComponent(myTemplatePanel, "TEMPLATES");
		myLayout.addLayoutComponent(myAdminPanel, "ADMIN");
	}
	
	
	
	public static void main(String... theArgs) {
		ContestGUI gui = new ContestGUI();
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
					System.out.println(input);
					System.out.println(myLayout.toString());
					myLayout.show(getContentPane(), input);
				}
			}
		}
		
	}
}
