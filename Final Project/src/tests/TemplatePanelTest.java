/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import view.TemplatePanel;

/**
 * @author FRANCIS
 *
 */
public class TemplatePanelTest {
	
	/**
     * represents one  TemplatePanel object.
     */
    private  TemplatePanel  myTemplatePanel;

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
		 myTemplatePanel = new  TemplatePanel();
		 myTemplatePanel.getPanel().setVisible(true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myTemplatePanel.getPanel().updateUI();;
		myTemplatePanel.hasChanged();
	}

	/**
	 * Test method for {@link view.TemplatePanel#TemplatePanel()}.
	 */
	@Test
	public void testTemplatePanel() {
		System.out.println("testTemplatePanel");
	}

	/**
	 * Test method for {@link view.TemplatePanel#getPanel()}.
	 */
	@Test
	public void testGetPanel() {
		System.out.println("testGetPanel");
	}

	/**
	 * Test method for {@link java.util.Observable#setChanged()}.
	 */
	@Test
	public void testSetChanged() {
		System.out.println("testSetChanged");
		
	}

	/**
	 * Test method for {@link java.util.Observable#hasChanged()}.
	 */
	@Test
	public void testHasChanged() {
		System.out.println("testHasChanged");
	}

}
