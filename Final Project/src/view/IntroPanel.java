package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class represents the introduction panel. The introductin panel is the home page for
 * the coloring contest and contains the contest details and allows the user to navigate 
 * between the submit page, download templates page, and the administrator page (if correct
 * credentials are added.
 * 
 * @author Jonah Howard
 * @version 5 March 2016
 */
public class IntroPanel extends Observable {
	
	/** Details panel width */
	private final int DETAILS_WIDTH = 375;
	
	/** Details panel height */
	private final int DETAILS_HEIGHT = 400;
	
	/** The current panel. */
	private final JPanel myPanel;
	
	/** Scroll pane for the moves table. */
    private JScrollPane scrollPane;
	
	/**
	 * Initialize a new Intro Panel.
	 */
	public IntroPanel() {
		myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		setUpComponents();
	}
	
	/**
	 * Set up the components for this panel.
	 */
	private void setUpComponents() {
		final JPanel northPanel = new JPanel();
		final JPanel centerPanel = new JPanel();
//		final JPanel details = getDetails();
		scrollPane = new JScrollPane(getDetails());
		scrollPane.setPreferredSize(new Dimension(DETAILS_WIDTH, DETAILS_HEIGHT));
		final JPanel buttons = getButtonsPanel();
		final JLabel label = new JLabel("Clark County Library Coloring Contest");


		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		
		northPanel.setBackground(Color.WHITE);
		northPanel.add(label);

		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 0));//BoxLayout(centerPanel, BoxLayout.X_AXIS));
		centerPanel.add(scrollPane);
		centerPanel.add(buttons);

		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		buttons.setBackground(Color.WHITE);
		myPanel.add(northPanel, BorderLayout.NORTH);
		myPanel.add(centerPanel);
		myPanel.setOpaque(false);
	}

	/**
	 * Return the panel with the buttons to submit, download, and administrator login.
	 * 
	 * @return the panel with  the buttons
	 */
	private JPanel getButtonsPanel() {
		final JPanel panel = new JPanel();
		final JButton downloads = new JButton("<html>Download<br><center>Template<center><html>");
		final JButton submit = new JButton("<html>Submit<br><center>Entry<center><html>");
		final JButton admin = new JButton("<html>Administrator<br><center>Login<center><html>");

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		downloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.TEMPLATES);
				clearChanged();
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.SUBMIT);
				clearChanged();
			}
		});
		
		admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.ADMIN);
				clearChanged();
			}
		});
		
		panel.add(submit);
		panel.add(Box.createVerticalStrut(20));
		panel.add(downloads);
		panel.add(Box.createVerticalStrut(20));
		panel.add(admin);
		return panel;
	}
	
	/**
	 * Returns the contest details panel. contestant.
	 * 
	 * @return the contest details panel
	 */
	private JPanel getDetails() {
		final JPanel panel = new JPanel();
		final JLabel label = new JLabel();

		final StringBuilder result = new StringBuilder();
		panel.setBackground(Color.WHITE);
		result.append("<html>");
		
        try {
            final Scanner input = new Scanner(new File("./extras/Coloring_Contest_Details"));
            Scanner line;
            final String space = " ";
            final String newLine = "<br>";
            while (input.hasNextLine()) {
                line = new Scanner(input.nextLine());
                while (line.hasNext()) {
                    result.append(line.next());
                    result.append(space);
                }
                result.append(newLine);
            }
            result.append("<html>");
            input.close();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        
        label.setText(result.toString());
        panel.add(label);
		return panel;
	}
	
	/**
	 * Gets the introduction panel.
	 * 
	 * @return the intro panel
	 */
	protected JPanel getPanel() {
		return myPanel;
	}
}
