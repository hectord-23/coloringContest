package tests;

import static org.junit.Assert.*;
import model.SubmissionDB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import view.GUI;
import controller.Administrator;

public class GUITest {
	
	/**
     * represents one Administrator object.
     */
    private GUI  myGUI;
    
    /**
     * 
     * @throws Exception
     */
    SubmissionDB subDB;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		myGUI = new GUI(subDB);
		myGUI.setVisible(true);
	
	}

	@After
	public void tearDown() throws Exception {
		myGUI.dispose();
		
	}

	@Test
	public void testGUI() {
		
	}

	@Test
	public void testMain() {
		assertTrue(myGUI.isShowing());
		
	}

	@Test
	public void testUpdateObservableObject() {
		fail("Not yet implemented");
	}

}
