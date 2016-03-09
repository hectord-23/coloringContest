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
			File directory = new File("./extras/Templates");
			for (final File file : directory.listFiles()) {
				templates.add(ImageIO.read(file));
			}            
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
