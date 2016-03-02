package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contest {
	
	/** The list of templates for this contest. */
	// This is the image class in java.awt. I'm not sure if it's what we should use or not
	private final List<Image> myTemplates;
	
	/** The cut off date for this contest. */
	private final Date myCutOffDate;
	
	/** The start date for this contest. */
	private final Date myStartDate;
	
	/** Stores what the administrator username log in credentials are. */
	private final String myAdminUsername;
	
	/** Stores what the administrator password log in credentials are. */
	private final String myAdminPassword;
	
	/** True if the administrator is logged in, false otherwise. */
	private boolean isLoggedIn;
	
	/** The list of submissions for this contest. */
	private final List<Contestant> mySubmissions;
	
	/**
	 * Initialize a new Contest.
	 * 
	 * @param theCutOff the cutoff date for this contest
	 * @param theStart the start date for this contest
	 * @param theUsername the administrator username for this contest
	 * @param thePassword the administrator password for this contest
	 */
	public Contest(final Date theCutOff, final Date theStart, final String theUsername, 
			final String thePassword) {
		myTemplates = new ArrayList<Image>();
		myCutOffDate = theCutOff;
		myStartDate = theStart;
		myAdminUsername = theUsername;
		myAdminPassword = thePassword;
		isLoggedIn = false;
		mySubmissions = new ArrayList<Contestant>();
	}
	
	/**
	 * Ends this contest.
	 */
	public void endContest() {
		
	}
	
	/**
	 * Starts this contest.
	 */
	public void startContest() {
		
	}
	
	/**
	 * Allows the administrator to log in if the credentials match the current admin username
	 * and password.
	 * 
	 * @param theUsername the username being used to log in
	 * @param thePassword the password being used to log in
	 */
	public void login(final String theUsername, final String thePassword) {
		
	}
	
	/**
	 * Logs the administrator out.
	 */
	public void logout() {
		
	}
	
	/**
	 * Returns the current list of submissions.
	 * 
	 * @return the current list of submissions
	 */
	public List<Contestant> getSubmissions() {
		return mySubmissions;
	}
}
