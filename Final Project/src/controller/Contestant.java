package controller;

import java.awt.Image;

public class Contestant {

	private int id;
	private Image photo;
	private String name;
	private String email;
	private String phone;
	
	//constructor
	public Contestant(int contestantId, Image img, String contestantName, String contestantEmail, String contestantPhone){
		id = contestantId;
		photo = img;
		name = contestantName;
		email = contestantEmail;
		phone = contestantPhone;
	}
	
	
	// TODO
	public void submit(){
		
	}
	
	//getters/setters
	public Image getImage(){
		return photo;
	}
	
	public void setImage(Image newImg){
		photo = newImg;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){
		name = newName;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int newId){
		id = newId;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String newEmail){
		email = newEmail;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String newPhone){
		phone = newPhone;
	}
	
}
