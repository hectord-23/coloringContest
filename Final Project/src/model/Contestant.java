package model;

import java.awt.Image;

public class Contestant {

	/** The current picture for this contestant. */
	private Image myPhoto;
	
	/** The name for this contestant. */
	private final String myName;
	
	/** The contest id for this contestant. */
	private final int myContestID;
	
	/** The email address for this contestant. */
	private final String myEmail;
	
	/** The phone number for this contestant. */
	private final String myPhoneNumber;
	
	/** The library card number for this contestant. */
	private final String myLibraryID;
	
	/**
	 * Initialize a new contestant. 
	 * 
	 * @param thePhoto the photo for this contestant
	 * @param theName the name for this contestant
	 * @param theContestID the contest id for this contestant
	 * @param theEmail the email address for this contestant
	 * @param thePhoneNumber the phone number for this contestant
	 * @param theLibraryID the library id for this contestant
	 */
	public Contestant(final Image thePhoto, final String theName, final int theContestID,
			final String theEmail, final String thePhoneNumber, final String theLibraryID) {
		myPhoto = thePhoto;
		myName = theName;
		myContestID = theContestID;
		myEmail = theEmail;
		myPhoneNumber = thePhoneNumber;
		myLibraryID = theLibraryID;
	}
	
	/**
	 * Sets the passed image as the current image for this contestant.
	 * 
	 * @param theImage the image to be set
	 */
	public void setImage(final Image theImage) {
		myPhoto = theImage;
	}
}
