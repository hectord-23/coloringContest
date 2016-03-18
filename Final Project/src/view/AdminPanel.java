package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.SubmissionDB;

/**
 * This class represents the administrator panel. The administrator can select contestants
 * manually or select a range and then either delete or download the selected contestants.
 * 
 * @author Jonah Howard
 * @version 6 March 2016
 */
public class AdminPanel extends Observable implements Observer {
	
	/** The username for the administrator. */
	protected static final String USERNAME = "admin";
	
	/** The password for the administrator. */
	protected static final String PASSWORD = "password";
	
	/** Panel that holds all components for this page. */
	private JPanel myPanel;
	
	/** The table that lists all of the submissions. */
	private final JTable myTable;
	
	/** The elements for the table. */
	private Object[][] myTableElements;
	
	/** The column names for this table. */
	private Object[] myColumnNames;
	
	private List<Object[]> myContestants;
	
	/** The current submissions database. */
	private final SubmissionDB myDataBase;
	
//	private final List<Contestant> myContestants;
	
	/**
	 * Initialize a new Administrator panel.
	 */
	public AdminPanel(final SubmissionDB theDB) {
		myPanel = new JPanel();
		myContestants = theDB.recallSubmissions();
		myTableElements = initializeTable();
		myColumnNames = new Object[]{"", "First Name", "Last Name", "Contact", "Age", "ID",
				"Submission", ""};
		myDataBase = theDB;
		myTable = new JTable(new DefaultTableModel(myTableElements, myColumnNames)) {
			/** A generated serial version UID. */
			private static final long serialVersionUID = 3368531531283619989L;
			@Override
			public Class<?> getColumnClass(final int index) {
				// allows image and checkbox to be displayed in table
				switch (index) {
					case 7: {
						return Boolean.class;
					} case 6: {
						return ImageIcon.class;
					} case 4: {
						return Integer.class;
					} default: {
						return String.class;
					}
				}
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 7;
			}
		};
		myTable.setMinimumSize(new Dimension(560, 100));
		addComponents();
	}
	
	/**
	 * Initializes the table and loads the submissions from the data base.
	 * 
	 * @return a 2D array containing submission data
	 */
	private Object[][] initializeTable() {
		final Object[][] result = new Object[myContestants.size()][8];
		int i = 1;
		for (final Object[] current : myContestants) {
			result[i - 1][0] = i;
			result[i - 1][1] = current[0];
			result[i - 1][2] = current[1];
			result[i - 1][3] = current[3];
			result[i - 1][4] = Integer.parseInt((String) current[2]);
			result[i - 1][5] = current[5];
			try {
				File f = new File("extras/Contestant_Submissions/" + current[5] + ".jpg");
				Image img = ImageIO.read(f);
				ImageIcon icon = new ImageIcon(
						img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				result[i - 1][6] = icon;
			} catch (IOException e) {
				e.printStackTrace();
			}
		    result[i - 1][7] = false;
			i++;
		}
		return result;
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
		myTable.setRowHeight(100);
		myTable.getColumnModel().getColumn(0).setMaxWidth(30);
		myTable.getColumnModel().getColumn(7).setMaxWidth(30);
		myTable.getColumnModel().getColumn(4).setMaxWidth(30);
		myTable.setBackground(Color.WHITE);
		myTable.setFont(SubmissionPanel.LABEL_FONT);
		
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(10));
		myPanel.setBackground(Color.WHITE);
		myPanel.add(new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		myPanel.add(getBottomPanel());
	}
	
	/**
	 * Get the panel with the remove selected button. Allows the administrator to remove 
	 * selected submissions.
	 * 
	 * @return panel containing remove selected button
	 */
	private JPanel getBottomPanel() {
		final JPanel panel = new JPanel();
		final JButton button = new JButton("Delete Selected");
		final JButton clear = new JButton("Clear Selected");
		final JLabel label = new JLabel("Select Age Range");
		final String[] items = new String[]{"--", "5-7", "8-10", "11-13", 
				"14-16", "17-19", "19+"};
		final JComboBox<String> box = new JComboBox<String>(items);
		box.setBackground(Color.WHITE);
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				int age = 3 * box.getSelectedIndex() + 2;
				for (int i = 0; i < myTable.getRowCount(); i++) {
					if ((int) myTable.getValueAt(i, 4) >= age 
							&& ((int) myTable.getValueAt(i, 4) <= age + 2 || age == 20)) {
						((DefaultTableModel) myTable.getModel()).setValueAt(true, i, 7);
					} else {
						// Otherwise uncheck
						((DefaultTableModel) myTable.getModel()).setValueAt(false, i, 7);
					}
				}
				
			}
		});
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				for (int i = 0; i < myTable.getRowCount(); i++) {
					((DefaultTableModel) myTable.getModel()).setValueAt(false, i, 7);
				}
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				// Search for and remove selected contestants
				for (int i = 0; i < myTable.getRowCount(); i++) {
					myTable.setValueAt(i + 1, i, 0);	// Update the first column
					if ((boolean) myTable.getValueAt(i, 7) == true) {
						((DefaultTableModel)myTable.getModel()).removeRow(i);
						myDataBase.deleteContestant(i + 1);
						i--;
					}
				}
			}
		});
		
		panel.add(label);
		panel.add(box);
		panel.add(button);
		panel.add(clear);
		panel.setBackground(Color.WHITE);
		return panel;
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
		final JLabel left = new JLabel("<html><center>Clark<br>County<br>Library"
				+ "</center><html>");
		final JLabel center = new JLabel("<html><center>Coloring<br>Contest<br>Submission"
				+ "</center><html>");
		final JButton button = new JButton("Logout");
		final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 15);

		assignGoHome(button);

		left.setFont(font);
		center.setFont(font);
		// Format and add components
		panel.setMaximumSize(new Dimension(600, 150));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		panel.add(left);
		panel.add(center);
		panel.add(button);
		
		return panel;
	}

	@Override
	public void update(final Observable arg0, final Object theObject) {
		if (theObject instanceof Object[]) {
			// Add a new submission
			final Object[] current = (Object[]) theObject;
			final Object[] submission = new Object[8];
			// Fill columns of the table
			submission[0] = myContestants.size() + 1;
			for (int i = 0; i < current.length - 1; i++) {
				if (i == 2) {
					submission[i + 1] = (String) current[i + 1];
					submission[i + 2] = (String) current[i];
					i++;
				} else if(i == 4){
					try {
						submission[i + 1] = Integer.parseInt((String) current[i + 1]);
					} catch (NumberFormatException e) { // in case is not an integer
						submission[i + 1] = (String) current[i + 1];
					}
				} else {
					submission[i + 1] = (String) current[i];
				}
			}
			submission[6] = new ImageIcon(((Image) current[6]).getScaledInstance(
					100,  100, Image.SCALE_DEFAULT));
			submission[7] = false;
			((DefaultTableModel) myTable.getModel()).addRow(submission);
			myContestants.add(submission);
		}
	}
	
}
