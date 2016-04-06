/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
   A component that holds the shape selector radio buttons 
*/
public class ShapeSelector extends JComponent
{  
	private ButtonGroup group;
	private JRadioButton cubeButton,sphereButton,prismButton,cylinderButton,torusButton;
	/**
	 * A constructor for ShapeSelector
	 * @param cubeButton cube button
	 * @param sphereButton sphere button
	 * @param prismButton prism button
	 * @param cylinderButton cylinder button
	 * @param torusButton torus button
	 */
	public ShapeSelector(JRadioButton cubeButton,
						JRadioButton sphereButton,
						JRadioButton prismButton,
						JRadioButton cylinderButton,
						JRadioButton torusButton)
	{
		this.cubeButton = cubeButton;
	 	this.sphereButton = sphereButton;
	 	this.prismButton = prismButton;
		this.cylinderButton = cylinderButton;
		this.torusButton = torusButton;
		
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    add(this.cubeButton);
	    add(this.sphereButton);
	    add(this.prismButton);
	    add(this.cylinderButton);
	    add(this.torusButton);
	    //Group the radio buttons.
	    
	    group = new ButtonGroup();
	    group.add(this.cubeButton);
	    group.add(this.sphereButton);
	    group.add(this.prismButton);
	    group.add(this.cylinderButton);
	    group.add(this.torusButton);
	  	
	  	setVisible(true);
	  	//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	
   
}