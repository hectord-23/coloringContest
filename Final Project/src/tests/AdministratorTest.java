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

import controller.Administrator;

/**
 * @author francisdadie
 *
 */
public class AdministratorTest {
	
	
	 /**
     * represents one Administrator object.
     */
    private Administrator  myAdministrator;

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
		
		myAdministrator = new Administrator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link controller.Administrator#downloadSelected()}.
	 */
	@Test
	public void testDownloadSelected() {
		myAdministrator.downloadSelected();
	}

	/**
	 * Test method for {@link controller.Administrator#selectAgeRange()}.
	 */
	@Test
	public void testSelectAgeRange() {
		myAdministrator.selectAgeRange();
	}

	/**
	 * Test method for {@link controller.Administrator#deleteSelected()}.
	 */
	@Test
	public void testDeleteSelected() {
		myAdministrator.deleteSelected();
	}

}
