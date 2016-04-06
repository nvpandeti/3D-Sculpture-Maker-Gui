/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
   A component that holds the viewer buttons
*/
public class ViewerButtonComponent extends JComponent
{ 
	private JButton viewerButton,saveButton, helpButton;
	/**
	 * A constructor for ViewerButtonComponent
	 * @param viewerButton viewer button
	 * @param saveButton save button
	 * @param helpButton help button
	 */
	public ViewerButtonComponent(JButton viewerButton, JButton saveButton, JButton helpButton)
	{
		this.viewerButton = viewerButton;
		this.saveButton = saveButton;
		this.helpButton = helpButton;
		
		setLayout(new GridLayout(3,1,0,5));
		//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    add(this.viewerButton); 
		add(this.saveButton);
		add(this.helpButton);
	  	
	  	setVisible(true);
	  	//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
   
}