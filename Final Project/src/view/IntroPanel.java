package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		northPanel.add(label);
		myPanel.add(northPanel, BorderLayout.NORTH);
		
		final JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));//BoxLayout(centerPanel, BoxLayout.X_AXIS));
		final JPanel details = getDetails();
		final JPanel buttons = getButtonsPanel();
		details.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centerPanel.add(details);
		centerPanel.add(buttons);
		buttons.setBackground(Color.WHITE);
		myPanel.add(centerPanel);
		myPanel.setOpaque(false);
	}

	private JPanel getButtonsPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		final JButton downloads = new JButton("<html>Download<br><center>Template<center><html>");
		downloads.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.TEMPLATES);
				clearChanged();
			}
		});
		
		final JButton submit = new JButton("<html>Submit<br><center>Entry<center><html>");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				setChanged();
				notifyObservers(GUI.SUBMIT);
				clearChanged();
			}
		});
		
		final JButton admin = new JButton("<html>Administrator<br><center>Login<center><html>");
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
	
	private JPanel getDetails() {
		final JPanel panel = new JPanel();
		final JScrollBar bar = new JScrollBar(JScrollBar.VERTICAL);
		final JLabel label = new JLabel();
		final JScrollPane pane = new JScrollPane(label);
		pane.setVerticalScrollBar(pane.createVerticalScrollBar());
//		label.add(bar);
		panel.setBackground(Color.WHITE);
		final StringBuilder result = new StringBuilder();
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
	
	protected JPanel getPanel() {
		return myPanel;
	}
}
