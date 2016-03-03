package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroPanel extends Observable {
	private final JPanel myPanel;
	
	public IntroPanel() {
		myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		setUpComponents();
	}
	
	private void setUpComponents() {
		final JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		JLabel label = new JLabel("Clark County Library Coloring Contest");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		northPanel.add(label);
		myPanel.add(northPanel, BorderLayout.NORTH);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		final JPanel details = getDetails();
		final JPanel buttons = getButtonsPanel();
		centerPanel.add(details);
		centerPanel.add(buttons);
		buttons.setBackground(Color.WHITE);
		myPanel.add(centerPanel);
	}

	private JPanel getButtonsPanel() {
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JButton downloads = new JButton("Download Template");
		downloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers("TEMPLATES");
				clearChanged();
			}
		});
		
		final JButton submit = new JButton("Submit Entry");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers("SUBMIT");
				clearChanged();
			}
		});
		
		final JButton admin = new JButton("Administrator Login");
		admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers("ADMIN");
				clearChanged();
			}
		});
		panel.add(submit);
		panel.add(downloads);
		panel.add(admin);
		return panel;
	}
	
	private JPanel getDetails() {
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		try {
			Scanner input = new Scanner(new File("./extras/Coloring_Contest_Details"));
			JLabel label = new JLabel();
			StringBuilder s = new StringBuilder();
			while (input.hasNextLine()) {
				s.append(input.nextLine() + "\n\r");
			}
			panel.add(new JLabel(s.toString()));
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
		return panel;
	}
	
	protected JPanel getPanel() {
		return myPanel;
	}
}
