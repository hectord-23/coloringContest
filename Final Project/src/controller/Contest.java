package controller;

import java.awt.Image;
import java.util.ArrayList;


public class Contest {
	
	private String adminlogin;
	private String adminpassword;
	private boolean isLoggedIn;
	
	private ArrayList<Image> templates;
	private ArrayList<Contestant> submissions;
	
	//constructor
	public Contest(){ 
		
		adminlogin = "admin";
		adminpassword = "password";
		
		templates = getTemplates();
		submissions = getSubmissions();
	}
	

	// TODO
	public ArrayList<Image> getTemplates(){
		
		return templates;
	}
	
	public void setTemplates(){
		
	}
	
	// TODO
	public ArrayList<Contestant> getSubmissions(){
		return submissions;
	}
	
	public void setSubmissions(){
		
	}
	
	
	/**
	 * Login function. Accepts two strings as the username and password
	 * returns True and sets the isLoggedIn flag if username and password match the hard coded values
	 * returns False if there is no match. 
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean login(String un, String pw){
		if ((un == adminlogin) && (pw == adminpassword)){
			isLoggedIn = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Logout function
	 * Sets the isLoggedIn flag to false
	 */
	public void logout(){
		if(isLoggedIn){
			isLoggedIn = false;
		}
	}
	
	

}
