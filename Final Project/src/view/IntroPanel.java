package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends JPanel {
	
	public IntroPanel() {
		super();
		setUpComponents();
	}
	
	private void setUpComponents() {
		final JPanel northPanel = new JPanel();
		JLabel label = new JLabel("Clark County Library Coloring Contest");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		northPanel.add(label);
		add(northPanel, BorderLayout.NORTH);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		final JPanel details = getDetails();
		add(details, BorderLayout.CENTER);
		final JPanel buttons = getButtonsPanel();
	}

	private JPanel getButtonsPanel() {
		return null;
	}
	private JPanel getDetails() {
		final JPanel panel = new JPanel();
		try {
			Scanner input = new Scanner(new File("./extras/Coloring_Contest_Details"));
			JLabel label = new JLabel();
			StringBuilder s = new StringBuilder();
			while (input.hasNextLine()) {
				s.append(input.nextLine() + "\n");
			}
			panel.add(new JLabel(s.toString()));
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
		return panel;
	}
}
