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

import controller.Contest;

/**
 * @author francisdadie
 *
 */
public class ContestTest {
	
	 /**
     * represents one Contest object.
     */
    private Contest  myContest;

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
		
		myContest = new Contest();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link controller.Contest#Contest()}.
	 */
	@Test
	public void testContest() {
		myContest = new Contest();	}

	/**
	 * Test method for {@link controller.Contest#getTemplates()}.
	 */
	@Test
	public void testGetTemplates() {
		myContest.getTemplates();
	}

	/**
	 * Test method for {@link controller.Contest#setTemplates()}.
	 */
	@Test
	public void testSetTemplates() {
		myContest.setTemplates();
	}

	/**
	 * Test method for {@link controller.Contest#getSubmissions()}.
	 */
	@Test
	public void testGetSubmissions() {
		myContest.getSubmissions();
	}

	/**
	 * Test method for {@link controller.Contest#setSubmissions()}.
	 */
	@Test
	public void testSetSubmissions() {
		myContest.setSubmissions();
	}

	/**
	 * Test method for {@link controller.Contest#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		myContest.login("adminlogin", "adminpassword");
	}

	/**
	 * Test method for {@link controller.Contest#logout()}.
	 */
	@Test
	public void testLogout() {
		myContest.logout();
		 Contest results = new Contest();
		assertEquals("False",myContest.equals("False"));
		//expect(myContest.login(eq(un), eq(pw)).andReturn(results));
	}

}
