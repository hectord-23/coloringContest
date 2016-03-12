/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Contestant;

/**
 * @author francisdadie
 *
 */
public class ContestantTest {
	
	 /**
     * represents one Contest object.
     */
    private Contestant  myContestant;

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
		
		myContestant = new Contestant(12, null, "AAJJ", "gg@yahho.fr", "4245526");
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link controller.Contestant#Contestant(int, java.awt.Image, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testContestant() {
		myContestant = new Contestant(0, null, null, null, null);
	}

	/**
	 * Test method for {@link controller.Contestant#submit()}.
	 */
	@Test
	public void testSubmit() {
		myContestant.submit();
	}

	/**
	 * Test method for {@link controller.Contestant#getImage()}.
	 */
	@Test
	public void testGetImage() {
		myContestant.getImage();
	}

	/**
	 * Test method for {@link controller.Contestant#setImage(java.awt.Image)}.
	 */
	@Test
	public void testSetImage() {
		myContestant.setImage(null);
	}

	/**
	 * Test method for {@link controller.Contestant#getName()}.
	 */
	@Test
	public void testGetName() {
		myContestant.getName();
	}

	/**
	 * Test method for {@link controller.Contestant#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		myContestant.setName("Colan");
	}

	/**
	 * Test method for {@link controller.Contestant#getId()}.
	 */
	@Test
	public void testGetId() {
		myContestant.getId();
	}

	/**
	 * Test method for {@link controller.Contestant#setId(int)}.
	 */
	@Test
	public void testSetId() {
		myContestant.setId(2);
	}

	/**
	 * Test method for {@link controller.Contestant#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		myContestant.getEmail();
	}

	/**
	 * Test method for {@link controller.Contestant#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		myContestant.setEmail("ffrff@yahoo.fr");
	}

	/**
	 * Test method for {@link controller.Contestant#getPhone()}.
	 */
	@Test
	public void testGetPhone() {
		myContestant.getPhone();
	}

	/**
	 * Test method for {@link controller.Contestant#setPhone(java.lang.String)}.
	 */
	@Test
	public void testSetPhone() {
		myContestant.setPhone("gggfgf");
	}

}
