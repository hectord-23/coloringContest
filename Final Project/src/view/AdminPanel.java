package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * This class represents the administrator panel. The administrator can select contestants
 * manually or select a range and then either delete or download the selected contestants.
 * 
 * @author Jonah Howard
 * @version 6 March 2016
 */
public class AdminPanel extends Observable {
	
	/** The username for the administrator. */
	protected static final String USERNAME = "username";
	
	/** The password for the administrator. */
	protected static final String PASSWORD = "password";
	
	/** Panel that holds all components for this page. */
	private JPanel myPanel;
	
	/** References the directory that holds all of the submissions. */
	private final File myFile;
	
	/** The table that lists all of the submissions. */
	private final JTable myTable;
	
	/** The elements for the table. */
	private Object[][] myTableElements;
	
	/** The column names for this table. */
	private Object[] myColumnNames;
	
//	private final List<Contestant> myContestants;
	
	/**
	 * Initialize a new Administrator panel.
	 */
	public AdminPanel() {
		myPanel = new JPanel();
		myFile = new File("./extras/administrator_downloads");
		myTableElements = loadSubmissions();
		myTableElements = new String[6][6];
		myColumnNames = new Object[]{"", "First Name", "Last Name", "Contact", "Submission", ""};
		myTable = new JTable(new DefaultTableModel(myTableElements, myColumnNames)) {
			/** A generated serial version UID. */
			private static final long serialVersionUID = 3368531531283619989L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0;
			}
		};
//		myContestants = 
		addComponents();
		myPanel.add(new JScrollPane(myTable));
	}
	
	/**
	 * Load the submissions from the submissions directory.
	 * 
	 * @return the loaded submissions
	 */
	private Object[][] loadSubmissions() {
		
		return null;
	}
	
	/**
	 * Return this panel.
	 * 
	 * @return this panel
	 */
	public JPanel getPanel() {
		return myPanel;
	}

	/**
	 * Add all components to this panel and format the table.
	 */
	private void addComponents() {
		myTable.getTableHeader().setReorderingAllowed(false);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		myTable.getColumnModel().getColumn(5).setPreferredWidth(25);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(10));
		myPanel.setBackground(Color.WHITE);
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
		panel.setMaximumSize(new Dimension(600, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
		final JButton button = new JButton("Logout");
		assignGoHome(button);

		left.setFont(font);
		center.setFont(font);
		
		panel.add(left);
		panel.add(center);
		panel.add(button);
		return panel;
	}
}
