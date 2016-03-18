/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.File;

import model.SubmissionDB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author FRANCIS
 *
 */
public class SubmissionDBTest {
	
	 /**
     * represents one SubmissionDB object.
     */
    private SubmissionDB  mySubmissionDB;
    
 
    

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
		mySubmissionDB = new SubmissionDB();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
		
	}

	/**
	 * Test method for {@link model.SubmissionDB#deleteContestant(int)}.
	 */
	@Test
	public void testDeleteContestant() {
		int theContestant = 0;
		mySubmissionDB.deleteContestant(theContestant);
		mySubmissionDB.deleteContestant(2);
		mySubmissionDB.deleteContestant(-2);
		System.out.println("testDeleteContestant");
	}

	/**
	 * Test method for {@link model.SubmissionDB#save()}.
	 */
	@Test
	public void testSave() {
		System.out.println("testSave");
		mySubmissionDB.save();
		
	}

	/**
	 * Test method for {@link model.SubmissionDB#recallSubmissions()}.
	 */
	@Test
	public void testRecallSubmissions() {
		System.out.println("testRecallSubmissions");
		mySubmissionDB.recallSubmissions();
		mySubmissionDB.recallSubmissions().isEmpty();
		mySubmissionDB.recallSubmissions().addAll(null);
		
	}

	/**
	 * Test method for {@link model.SubmissionDB#addData(java.lang.Object[])}.
	 */
	@Test
	public void testAddData() {
		   Object[] theSubmission = null  ;
			String name = (String) theSubmission[0];
			String lastName = (String) theSubmission[1];
			String age = (String) theSubmission[2];
			String email = (String) theSubmission[3];
			String phone = (String) theSubmission[4];
			String id = (String) theSubmission[5];
			Image myImage = (Image) theSubmission[6];
			
			name = "kone";

			
assertEquals("", "");

		//System.out.println("");
		
		
	
		
		
		mySubmissionDB.addData(null);
		}

	public void testaddSubmission(){
		System.out.println("");
		//mySubmissionDB.addSubmission(null, null, null, null, null, null, null);
		//mySubmissionDB.addSubmission("name", "lastName", "12", "email", "425", "gg", null);
		
	}
	/**
	 * Test method for {@link model.SubmissionDB#saveImage(java.awt.Image, java.lang.String)}.
	 */
	@Test
	public void testSaveImage() {
		Image theImage = null;
		String theID = null;
		mySubmissionDB.saveImage( theImage,  theID );
		theImage.getHeight(null);
	}

}
