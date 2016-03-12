/**
 * 
 */
package tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import view.SubmissionPanel;

/**
 * @author FRANCIS
 *
 */
public class SubmissionPanelTest {

	/**
	 * 
	 */
	
	public SubmissionPanel mySubmissionPanel;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 mySubmissionPanel = new SubmissionPanel(null);
		 mySubmissionPanel.getPanel().setVisible(true);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		 mySubmissionPanel.getPanel().action(null, getClass());

	}

	/**
	 * Test method for {@link view.SubmissionPanel#SubmissionPanel(model.SubmissionDB)}.
	 */
	@Test
	public void testSubmissionPanel() {
		System.out.println("testSubmissionPanel");
	}

	/**
	 * Test method for {@link view.SubmissionPanel#getPanel()}.
	 */
	@Test
	public void testGetPanel() {
		System.out.println("testGetPanel");
	}

}
