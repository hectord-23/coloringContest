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
	protected static final String USERNAME = "";
	
	/** The password for the administrator. */
	protected static final String PASSWORD = "";
	
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
	
	private List<Object[]> myContestants;
	
	/** The current submissions database. */
	private final SubmissionDB myDataBase;
	
//	private final List<Contestant> myContestants;
	
	/**
	 * Initialize a new Administrator panel.
	 */
	public AdminPanel(final SubmissionDB theDB) {
		myPanel = new JPanel();
		myFile = new File("./extras/administrator_downloads");
		myTableElements = loadSubmissions();
		myContestants = theDB.recallSubmissions();
		myTableElements = initializeTable();
		myColumnNames = new Object[]{"", "First Name", "Last Name", "Contact", "Age", "ID", "Submission", ""};
		myDataBase = theDB;
		myTable = new JTable(new DefaultTableModel(myTableElements, myColumnNames)) {
			/** A generated serial version UID. */
			private static final long serialVersionUID = 3368531531283619989L;
			@Override
			public Class<?> getColumnClass(final int index) {
				if (index == 7) {
					return Boolean.class;
				} else if (index == 6) {
					return ImageIcon.class;
				} else {
					return String.class;
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
	
	private Object[][] initializeTable() {
		final Object[][] result = new Object[myContestants.size()][8];
		int i = 1;
		for (final Object[] current : myContestants) {
			result[i - 1][0] = i;
			result[i - 1][1] = current[0];
			result[i - 1][2] = current[1];
			result[i - 1][3] = current[3];
			result[i - 1][4] = current[2];
			result[i - 1][5] = current[5];
			try {
				File f = new File("extras/Contestant_Submissions/" + current[5] + ".jpg");
				System.out.println("file is: " + f.toString());
				Image img = ImageIO.read(f);
				ImageIcon icon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				result[i - 1][6] = icon;//new ImageIcon(((Image) ImageIO.read(f)).getScaledInstance(100,  100, Image.SCALE_DEFAULT));
			} catch (IOException e) {
				e.printStackTrace();
			}
		    result[i - 1][7] = false;
			i++;
		}
		return result;
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
//		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myTable.setRowHeight(100);
		myTable.getColumnModel().getColumn(0).setMaxWidth(30);
		myTable.getColumnModel().getColumn(7).setMaxWidth(30);
		myTable.getColumnModel().getColumn(4).setMaxWidth(30);
		myTable.setBackground(Color.WHITE);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		myPanel.add(createHeader());
		myPanel.add(Box.createVerticalStrut(10));
		myPanel.setBackground(Color.WHITE);
		myPanel.add(new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
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
		final JLabel left = new JLabel("<html>Clark<br>County<br>Library<html>");
		final JLabel center = new JLabel("<html>Coloring<br>Contest<br>Submission<html>");
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
			final Object[] current = (Object[]) theObject;
			final Object[] submission = new Object[8];
			submission[0] = myContestants.size() + 1;
			for (int i = 1; i < 6; i++) {
				submission[i] = (String) current[i - 1];
			}
			submission[6] = new ImageIcon(((Image) current[6]).getScaledInstance(100,  100, Image.SCALE_DEFAULT));
			submission[7] = false;
			((DefaultTableModel) myTable.getModel()).addRow(submission);
			myContestants.add(submission);
		}
	}
	
}
