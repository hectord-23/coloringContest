package model;

public class Administrator {
	
	/** The username for this administrator. */
	private String myUsername;
	
	/** The password for this administrator. */
	private String myPassword;
	
	public Administrator(final String theUsername, final String thePassword) {
		myPassword = thePassword;
		myUsername = theUsername;
	}
	
	/**
	 * Downloads the selected contest entries.
	 */
	public void downloadSelected() {
		
	}
	
	/**
	 * Selects a range of contest entries based on the passed age range.
	 * 
	 * @param minAge the minimum age to be selected
	 * @param maxAge the maximum age to be selected
	 */
	public void selectRange(final int minAge, final int maxAge) {
		
	}
}
