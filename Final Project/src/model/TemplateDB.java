/* 
 * Class to get the coloring templates.
 * Author: Hector Diaz Contreras
 * Date Last Edited: 03/03/2016 
 */

package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/** 
 * A class to get the coloring templates.
 * @author Hector Diaz Contreras.
 * @version 1.0 (03/03-2016)
 *
 */
public class TemplateDB {
	
	/** A private ArrayList of the contest templates. */
	private final static ArrayList<Image> templates = new ArrayList<Image>();
	
	/** A private ArrayList of ImageIcons of the contest templates. */
	private final static ArrayList<ImageIcon> imgIconTemplates = new ArrayList<ImageIcon>();
	
	/** Constructor class. */
	public TemplateDB() {
		addTemplates();
	}
	
	/** Method to add the template images to the templates list. */
	private void addTemplates() {
		try{
			// The templates
			
            final Image template0 = ImageIO.read(new File("extras/Templates/Adult-Coloring-Pages-24.jpg"));
            final Image template1 = ImageIO.read(new File("extras/Templates/elephant-adult-coloring-page.jpg"));
            final Image template2 = ImageIO.read(new File("extras/Templates/Free-Coloring-Pages-9.jpg"));
            final Image template3 = ImageIO.read(new File("extras/Templates/Lion_Head_Adult_Coloring_Pages_01.jpg"));
            final Image template4 = ImageIO.read(new File("extras/Templates/Mario.jpg"));
            final Image template5 = ImageIO.read(new File("extras/Templates/Start.jpg"));
            
            // Adding them to the templates Arraylist.
            templates.add(template0);
            templates.add(template1);
            templates.add(template2);
            templates.add(template3);
            templates.add(template4);
            templates.add(template5);
            
        } catch (IOException e){
            e.printStackTrace();
        }
	}
	
	/** 
	 * Gets a copy of the ArrayList of the image templates.
	 * @return A copy of the ArrayList of the image templates.
	 */
	public static ArrayList<Image> getTemplates() {
		ArrayList<Image> temp = new ArrayList<Image>();
		for(int i = 0; i < templates.size(); i++) {
			temp.add(templates.get(i));
		}
		return temp;
	}
	
	/** 
	 * Gets a copy of the ArrayList of the image templates as ImageIcons.
	 * @return An ArrayList of ImageIcon templates.
	 */
	public static ArrayList<ImageIcon> getImageIconTemplates() {
		final ArrayList<Image> temp = getTemplates();
		for(int i = 0; i < temp.size(); i++) {
			imgIconTemplates.add(new ImageIcon(temp.get(i)));
		}
		return imgIconTemplates;
	}
}
