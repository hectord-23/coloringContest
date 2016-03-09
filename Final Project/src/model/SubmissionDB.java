package model;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.imageio.ImageIO;

import controller.Contestant;
import view.SubmissionPanel;

/**
 * A class to represent the contest submission database.
 * Two ways of going about this:
 * 1. Read the Files in to assert they are there and reread that data whenever it is read.
 * 2. Read in the Files to the class and return deep copies of this data, saving back to File whenever changes are made.
 * 
 * @author Cody Cates
 * @date March 3, 2016
 */
public class SubmissionDB  {
	
	/** A master list to act as the back end data. */
	private ArrayList<Contestant> mySubmissions;
	
	/**
	 * Constructor to read in persistent data.
	 */
	public SubmissionDB() {
		super();
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

//	@Override
	public void addData(Object theSubmission) {
//		if(!(arg0 instanceof SubmissionPanel) && !theSubmission.equals("INTRO")) return;
//		System.out.println("WE HAVE DATA TO WRITE");
		// TODO Auto-generated method stub
		String name = (String) ((Object[]) theSubmission)[0];
		String lastName = (String) ((Object[]) theSubmission)[1];
		String age = (String) ((Object[]) theSubmission)[2];
		String email = (String) ((Object[]) theSubmission)[3];
		String phone = (String) ((Object[]) theSubmission)[4];
		String id = (String) ((Object[]) theSubmission)[5];
		Image myImage = (Image) ((Object[]) theSubmission)[6];
		addSubmission(name, lastName, age, email, phone, id, myImage);
	}

	private void addSubmission(String name, String lastName, String age, String email,
			String phone, String id, Image myImage) {
		// TODO Auto-generated method stub
		String submission = name + " " + lastName + " " + age + " " + email + " " + phone + " " + id;
//		System.out.println(submission);
		PrintStream outputFile = null;
		Scanner inputFile = null;
		final StringBuilder sb = new StringBuilder();
		try
		{
			inputFile = new Scanner(new File("extras/Contestant_Submissions/submissionsData.txt"));
		}
		catch(Exception e)
		{
			System.out.println("Difficulties opening the file! " + e);
//			System.exit(1);
		}
		while(inputFile.hasNextLine()) {
			String temp = inputFile.nextLine() + "\n";
//			System.out.println(">> : " + temp);
			sb.append(temp);
		}
//		System.out.println("DATA IN  FILE: " + sb.toString());
		inputFile.close();
		try {
			outputFile = new PrintStream(new File("extras/Contestant_Submissions/submissionsData.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Difficulties opening the file! " + e);
		}
		outputFile.print(sb.toString());
		outputFile.print(submission);
		outputFile.close();
		saveImage(myImage, id);
	}

	/**
	 * 
	 */
	public void saveImage(Image theImage, String theID) {
		try {
			ImageIO.write((RenderedImage) theImage, "jpg", new File("extras/Contestant_Submissions/" + theID + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}