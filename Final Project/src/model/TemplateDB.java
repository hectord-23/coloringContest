package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TemplateDB {
	
	private ArrayList<Image> templates;
	
	public TemplateDB() {
		templates = new ArrayList<Image>();
		addTemplates();
	}
	
	/** Method to add the template images to the templates list. */
	private void addTemplates() {
		try{
			// The templates
            Image template0 = ImageIO.read(new File("/extras/Templates/Adult-Coloring-Pages-24.jpg"));
            Image template1 = ImageIO.read(new File("/extras/Templates/elephant-adult-coloring-page.jpg"));
            Image template2 = ImageIO.read(new File("/extras/Templates/Free-Coloring-Pages-9.jpg"));
            Image template3 = ImageIO.read(new File("/extras/Templates/Lion_Head_Adult_Coloring_Pages_01.jpg"));
            Image template4 = ImageIO.read(new File("/extras/Templates/Mario.jpg"));
            Image template5 = ImageIO.read(new File("/extras/Templates/Start.jpg"));
            
            // Adding them to the templates list.
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
	
	
}
