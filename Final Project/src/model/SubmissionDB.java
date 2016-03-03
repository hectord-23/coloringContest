package model;

import java.util.ArrayList;

import controller.Contestant;

/**
 * 
 * @author Cody Cates
 *
 */
public class SubmissionDB {
	private ArrayList<Contestant> mySubmissions;
	
	/**
	 * Constructor to read in persistent data.
	 */
	public SubmissionDB() {
		fetchContestants();
	}
	
	/**
	 * Reads in persistent data.
	 */
	private void fetchContestants() {
		
	}
	
	/**
	 * Deep copies mySubmissions and returns it.
	 * @return A deep copy of the ArrayList of contestants.
	 */
	public ArrayList<Contestant> getSubmissions() {
		
		return null;
	}
	
	/**
	 * Adds the passed contestant to the list.
	 */
	public void addSubmission(Contestant toAdd) {
		
	}
	
	/**
	 * Delete a contestant from the database.
	 */
	public void deleteContestant() {
		//TODO Param to select contestant
	}
	
	/**
	 * 
	 */
	public void save() {
		
	}
}
