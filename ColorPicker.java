/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
   A component that holds the color buttons
*/
public class ColorPicker extends JComponent
{  
	private JButton redButton,greenButton,blueButton,yellowButton,purpleButton,orangeButton,blackButton,grayButton,whiteButton;
	/**
	 * A constructor for ColorPicker
	 * @param redButton red button
	 * @param greenButton green button
	 * @param blueButton blue button
	 * @param yellowButton yellow button
	 * @param purpleButton purple button
	 * @param orangeButton orange button
	 * @param blackButton black button
	 * @param grayButton gray button
	 * @param whiteButton white button
	 */
	public ColorPicker(JButton redButton,
						JButton greenButton,
						JButton blueButton,
						JButton yellowButton,
						JButton purpleButton,
						JButton orangeButton,
						JButton blackButton,
						JButton grayButton,
						JButton whiteButton)
	{
		this.redButton = redButton;
		this.greenButton = greenButton;
		this.blueButton = blueButton;
		this.yellowButton = yellowButton;
		this.purpleButton = purpleButton;
		this.orangeButton = orangeButton;
		this.blackButton = blackButton;
		this.grayButton = grayButton;
		this.whiteButton = whiteButton;
		
		setLayout(new GridLayout(9,1));
		//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    add(this.redButton); 
		add(this.greenButton);
		add(this.blueButton);
		add(this.yellowButton);
		add(this.purpleButton);
		add(this.orangeButton);
		add(this.blackButton);
		add(this.grayButton);
		add(this.whiteButton);
	  	
	  	setVisible(true);
	  	//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
   
}