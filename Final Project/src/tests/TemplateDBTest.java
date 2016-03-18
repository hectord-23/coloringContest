/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import model.TemplateDB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author FRANCIS
 *
 */
public class TemplateDBTest {
	
	/**
     * represents one TemplateDB object.
     */
    private TemplateDB  myTemplateDB;

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
		myTemplateDB = new TemplateDB();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.TemplateDB#TemplateDB()}.
	 */
	@Test
	public void testTemplateDB() {
		myTemplateDB.equals(myTemplateDB);
	}

	/**
	 * Test method for {@link model.TemplateDB#getTemplates()}.
	 */
	@Test
	public void testGetTemplates() {
		myTemplateDB.getTemplates();
	}

	/**
	 * Test method for {@link model.TemplateDB#getImageIconTemplates()}.
	 */
	@Test
	public void testGetImageIconTemplates() {
		myTemplateDB.getImageIconTemplates();
	}

}
