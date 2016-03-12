/**
 * 
 */
package view;

import static org.junit.Assert.*;

import java.util.Observable;

import model.SubmissionDB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.Administrator;

/**
 * @author FRANCIS
 *
 */
public class AdminPanelTest {
	/**
     * represents one AdminPanel object.
     */
    private AdminPanel  myAdminPanel;
    
    /**
     * 
     */
  private   SubmissionDB theDB;
  
  /**
   * 
   */
  private Observable arg0;
  
  /**
   * 
   */
  private  Object theObject;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	//	System.out.println("SETUP");
		myAdminPanel = null;
		//myAdminPanel = new AdminPanel(theDB);
		//myAdminPanel.getPanel().setVisible(true);
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link view.AdminPanel#AdminPanel(model.SubmissionDB)}.
	 */
	@Test
	public void testAdminPanel() {
		//myAdminPanel = new AdminPanel(theDB);
		//myAdminPanel.equals(theDB);
		System.out.println("testAdminPanel");
	}

	/**
	 * Test method for {@link view.AdminPanel#getPanel()}.
	 */
	@Test
	public void testGetPanel() {
		//myAdminPanel.getPanel();
		System.out.println("testGetPanel");
	}

	/**
	 * Test method for {@link view.AdminPanel#update(java.util.Observable, java.lang.Object)}.
	 */
	@Test
	public void testUpdate() {
	//	myAdminPanel.update(arg0, theObject);
		System.out.println("testUpdate");
	}

}
