package model;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.Contestant;

/**
 * A class to represent the contest submission database.
 * Two ways of going about this:
 * 1. Read the Files in to assert they are there and reread that data whenever it is read.
 * 2. Read in the Files to the class and return deep copies of this data, saving back to File whenever changes are made.
 * 
 * @author Cody Cates
 * @date March 3, 2016
 */
public class SubmissionDB {
	
	/** A master list to act as the back end data. */
	private ArrayList<Contestant> mySubmissions;
	
	/**
	 * Constructor to read in persistent data.
	 */
	public SubmissionDB() {
		mySubmissions = new ArrayList<Contestant>();
		if ( recallData() ) {
			
		}
	}
	
	/**
	 * Reads in persistent data from csv file /extras/Archive.csv
	 * @return false on successful read, true on failed read
	 */
	private boolean recallData() {
		String line;
		int noEntries;
		File csvFile;
		Scanner scan;
		
//		try {
			csvFile = new File("/extras/Archive.csv");
//			while(scan.hasNextLine()) {
//				c
//		} catch (IOException e){
//            e.printStackTrace();
//            
//            //exit fail
//            return true;
//        }
		
		// exit success
		return false;
	}
	
	/**
	 * Deep copies mySubmissions and returns it.
	 * @return A deep copy of the ArrayList of contestants.
	 */
	public ArrayList<Contestant> getSubmissions() {
		
		return null;
	}
	
	/**
	 * Adds the passed Contestant to the persistent data stored.
	 * The Image in Contestant strictly needs to to be in the image 
	 * as an Object that implements RenderedImage such as a BufferedImage.
	 * @param Contestant the contestant being added to the database
	 */
	public void addSubmission(Contestant toAdd) {
		try {
			ImageIO.write((RenderedImage) toAdd.getImage(), "jpg", new File("extras/Submitted/number.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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