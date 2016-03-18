package model;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
	
	/** The file name for the text file with all of the submission data. */
	public static final String SUBMISSIONS = "extras/Contestant_Submissions/submissionsData.txt";
	/**
	 * Delete a contestant from the database.
	 */
	public void deleteContestant(final int theContestant) {
		int rowCounter = 1;
		String row[];
		PrintStream outputFile = null;
		Scanner inputFile = null;
		final StringBuilder sb = new StringBuilder();
		
		try {
			inputFile = new Scanner(new File("extras/Contestant_Submissions/submissionsData.txt"));
			while ( rowCounter != theContestant && inputFile.hasNextLine() ) {
				sb.append(inputFile.nextLine() + "\n");
				rowCounter++;
			}
			if( rowCounter == theContestant && inputFile.hasNextLine() ) {
				row = inputFile.nextLine().split(" ");
				new File("extras/Contestant_Submissions/" + row[5] + ".jpg").delete();
			}
			while(inputFile.hasNextLine()) 
				sb.append(inputFile.nextLine() + "\n");
			inputFile.close();
			
			outputFile = new PrintStream(new File("extras/Contestant_Submissions/submissionsData.txt"));
			outputFile.print(sb.toString());
			
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException when opening file from SubmissionsDatabase.");
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * 
	 */
	public void save() {
		
	}
	
	/**
	 * Reads in the persistent submission data from extras/Contestant_Submissions
	 * !! Since we're using spaces as delimiters, I'm reading in each arg as a token., If the 
	 * @return false on successful read, true on failed read
	 * @author Cody Cates
	 */
	public List<Object[]> recallSubmissions() {
		int i;
		String[] tempArray;
		Object[] subArray;
		Scanner inputFile;
		File inputImgFile;
		List<Object[]> retList = new ArrayList<Object[]>();
		
		try {
			// Open submission file and scan it.
			inputFile = new Scanner(new File(SUBMISSIONS));
			while (inputFile.hasNextLine()) {
				
				// Read a line and split it into a temporary array by spaces
				tempArray = inputFile.nextLine().split(" ");
				
				// If the line did not have the right number of entries, print out error and skip it
				if (tempArray.length != 6) {
					System.err.println("Corrupt entry.");
					for(i = 0; i < tempArray.length; i++) 
						System.err.print(tempArray[i]);
					System.err.println();
					continue;
				}
				
				inputImgFile = new File("extras/Contestant_Submissions/" + tempArray[5] + ".jpg");
				
				// add components to new array
				subArray = new Object[7];
				for(i = 0; i < tempArray.length; i++) 
					subArray[i] = tempArray[i];
				subArray[i] = ImageIO.read(inputImgFile);
				
				// add submission array into ArrayList of submissions
				retList.add(subArray);
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("FileNotFoundException when opening file from SubmissionsDatabase.");
			e.printStackTrace(System.err);
		} catch (IOException e) {
			System.err.println("IOException reading in image from SubmissionsDataBase.");
			e.printStackTrace(System.err);
		}
		
		return retList;
	}

//	@Override
	public void addData(Object[] theSubmission) {
//		if(!(arg0 instanceof SubmissionPanel) && !theSubmission.equals("INTRO")) return;
//		System.out.println("WE HAVE DATA TO WRITE");
		// TODO Auto-generated method stub
		String name = (String) theSubmission[0];
		String lastName = (String) theSubmission[1];
		String age = (String) theSubmission[2];
		String email = (String) theSubmission[3];
		String phone = (String) theSubmission[4];
		String id = (String) theSubmission[5];
		Image myImage = (Image) theSubmission[6];
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
		try {
			inputFile = new Scanner(new File(SUBMISSIONS));
		} catch(Exception e) {
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
			outputFile = new PrintStream(new File(SUBMISSIONS));
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
