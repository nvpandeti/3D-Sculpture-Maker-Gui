/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
   A component that holds the text fields
*/
public class TextBoxComponent extends JComponent
{  
	private JTextField x, y, z, length, width, height, radius, radius2, roll, pitch, yaw, quality, sides;
	private double lengthNum, widthNum, heightNum, radiusNum, radius2Num, rollNum, pitchNum, yawNum; 
	private int qualityNum, sidesNum;
	private JLabel xText, yText, zText, lengthText, widthText, heightText, radiusText, radius2Text, rollText, pitchText, yawText, qualityText, sidesText;
	private JButton draw;
	/**
	 * A constructor for TextBoxComponent
	 * @param draw A draw button
	 */
	public TextBoxComponent(JButton draw)
	{
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		qualityNum = sidesNum = 0;
		lengthNum = widthNum = heightNum = 1;
		radiusNum = radius2Num = rollNum = pitchNum = yawNum = 0;
		
		xText = new JLabel(" X:");
		yText = new JLabel(" Y:"); 
		zText = new JLabel(" Z:"); 
		lengthText = new JLabel(" Length:"); 
		widthText = new JLabel(" Width:"); 
		heightText = new JLabel(" Height:"); 
		radiusText = new JLabel(" Radius:"); 
		radius2Text = new JLabel(" Radius 2:"); 
		rollText = new JLabel(" Roll:"); 
		pitchText = new JLabel(" Pitch:"); 
		yawText = new JLabel(" Yaw:"); 
		qualityText = new JLabel(" Quality:");
		sidesText = new JLabel(" # of Sides:");
	  	
	  	x = new JTextField(12); 
	  	y = new JTextField(12); 
	  	z = new JTextField(12); 
	  	length = new JTextField(5); 
	  	width = new JTextField(5); 
	  	height = new JTextField(5); 
	  	radius = new JTextField(5); 
	  	radius2 = new JTextField(5);
	  	roll = new JTextField(5); 
	  	pitch = new JTextField(5); 
	  	yaw = new JTextField(5); 
	  	quality = new JTextField(5); 
	  	sides = new JTextField(5);
	  	
	  	//radius.addActionListener(this);
	  	//radius.addMouseListener(this);
	  	add(xText);
		add(x);
		add(yText);
		add(y);
		add(zText);
		add(z);
		add(lengthText);
		add(length);
		add(widthText);
		add(width);
		add(heightText);
		add(height);
		add(radiusText);
		add(radius);
		add(radius2Text);
		add(radius2);
		add(qualityText);
		add(quality);
		add(sidesText);
    	add(sides);
    	add(yawText);
		add(yaw);
		add(pitchText);
		add(pitch);
		add(rollText);
		add(roll);
		
	  	setShape("Cube");
	  	
	  	add(draw);
	  	setVisible(true);
	  	
	  	
	  	//setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	}
	/**
	 * Returns the X textfield's text
	 * @return the X textfield's text
	 */
	public String getx()
	{
		return x.getText();
	}
	/**
	 * Returns the Y textfield's text
	 * @return the Y textfield's text
	 */
	public String gety()
	{
		return y.getText();
	}
	/**
	 * Returns the Z textfield's text
	 * @return the Z textfield's text
	 */
	public String getz()
	{
		return z.getText();
	}
	/**
	 * Returns the Length textfield's text
	 * @return the Length textfield's text
	 */
	public String getlength()
	{
		return length.getText();
	}
	/**
	 * Returns the Width textfield's text
	 * @return the Width textfield's text
	 */
	public String getwidth()
	{
		return width.getText();
	}
	/**
	 * Returns the Height textfield's text
	 * @return the Height textfield's text
	 */
	public String getheight()
	{
		return height.getText();
	}
	/**
	 * Returns the Radius textfield's text
	 * @return the Radius textfield's text
	 */
	public String getRadius()
	{
		return radius.getText();
	}
	/**
	 * Returns the Radius2 textfield's text
	 * @return the Radius2 textfield's text
	 */
	public String getRadius2()
	{
		return radius2.getText();
	}
	/**
	 * Returns the Quality textfield's text
	 * @return the Quality textfield's text
	 */
	public String getQuality()
	{
		return quality.getText();
	}
	/**
	 * Returns the Sides textfield's text
	 * @return the Sides textfield's text
	 */
	public String getSides()
	{
		return sides.getText();
	}
	/**
	 * Returns the Roll textfield's text
	 * @return the Roll textfield's text
	 */
	public String getRoll()
	{
		return roll.getText();
	}
	/**
	 * Returns the Pitch textfield's text
	 * @return the Pitch textfield's text
	 */
	public String getPitch()
	{
		return pitch.getText();
	}
	/**
	 * Returns the Yaw textfield's text
	 * @return the Yaw textfield's text
	 */
	public String getYaw()
	{
		return yaw.getText();
	}
	/**
	 * Sets the X textfield's text
	 * @param s X textfield's text
	 */
	public void setX(String s)
	{
		x.setText(s);
	}
	/**
	 * Sets the Y textfield's text
	 * @param s Y textfield's text
	 */
	public void setY(String s)
	{
		y.setText(s);
	}
	/**
	 * Sets the Z textfield's text
	 * @param s Z textfield's text
	 */
	public void setZ(String s)
	{
		z.setText(s);
	}
	/**
	 * Sets the required textfields to be visible
	 * @param shape the specified shape
	 */
	public void setShape(String shape)
	{
    	 
    	length.setVisible(false);  
    	width.setVisible(false);  
    	height.setVisible(false);  
    	radius.setVisible(false);  
    	radius2.setVisible(false);  
    	roll.setVisible(false);  
    	pitch.setVisible(false);  
    	yaw.setVisible(false);  
    	quality.setVisible(false);  
    	sides.setVisible(false);
    	
    	lengthText.setVisible(false);  
    	widthText.setVisible(false);  
    	heightText.setVisible(false);  
    	radiusText.setVisible(false);  
    	radius2Text.setVisible(false);  
    	rollText.setVisible(false);  
    	pitchText.setVisible(false); 
    	yawText.setVisible(false);  
    	qualityText.setVisible(false); 
    	sidesText.setVisible(false);
    	
		if(shape.equals("Cube"))
    	{
    		lengthText.setVisible(true);
    		length.setVisible(true);
    		widthText.setVisible(true);
    		width.setVisible(true);
    		heightText.setVisible(true);
    		height.setVisible(true);
    		rollText.setVisible(true);
    		roll.setVisible(true);
    		pitchText.setVisible(true);
    		pitch.setVisible(true);
    		yawText.setVisible(true);
    		yaw.setVisible(true);
    		
    		length.setText("1");
    		width.setText("1");
    		height.setText("1");
    		roll.setText("0");
    		pitch.setText("0");
    		yaw.setText("0");
    	}
    	else if(shape.equals("Sphere"))
    	{
    		radiusText.setVisible(true);
    		radius.setVisible(true);
    		qualityText.setVisible(true);
    		quality.setVisible(true);
    		
    		radius.setText("1");
    		quality.setText("15");
    	}
    	else if(shape.equals("Prism"))
    	{
    		heightText.setVisible(true);
    		height.setVisible(true);
    		radiusText.setVisible(true);
    		radius.setVisible(true);
    		sidesText.setVisible(true);
    		sides.setVisible(true);
    		rollText.setVisible(true);
    		roll.setVisible(true);
    		pitchText.setVisible(true);
    		pitch.setVisible(true);
    		yawText.setVisible(true);
    		yaw.setVisible(true);
    		
    		height.setText("1");
    		radius.setText("1");
    		sides.setText("4");
    		roll.setText("0");
    		pitch.setText("0");
    		yaw.setText("0");
    		
    	}
    	else if(shape.equals("Cylinder"))
    	{
    		heightText.setVisible(true);
    		height.setVisible(true);
    		radiusText.setVisible(true);
    		radius.setVisible(true);
    		rollText.setVisible(true);
    		roll.setVisible(true);
    		pitchText.setVisible(true);
    		pitch.setVisible(true);
    		//yawText.setVisible(true);
    		//yaw.setVisible(true);
    		
    		height.setText("1");
    		radius.setText("1");
    		roll.setText("0");
    		pitch.setText("0");
    		//yaw.setText("0");
    	}
    	else if(shape.equals("Torus"))
    	{
    		radiusText.setVisible(true);
    		radius.setVisible(true);
    		radius2Text.setVisible(true);
    		radius2.setVisible(true);
    		qualityText.setVisible(true);
    		quality.setVisible(true);
    		rollText.setVisible(true);
    		roll.setVisible(true);
    		pitchText.setVisible(true);
    		pitch.setVisible(true);
    		yawText.setVisible(true);
    		yaw.setVisible(true);
    		
    		radius.setText("1");
    		radius2.setText("2");
    		quality.setText("15");
    		roll.setText("0");
    		pitch.setText("0");
    		yaw.setText("0");
    	}
    	
	}
	/**
	 * Corrects any user errors
	 */
	public void checkText()
	{
		try
		{
			qualityNum = Integer.parseInt(quality.getText());
			sidesNum = Integer.parseInt(sides.getText());
			lengthNum = Double.parseDouble(length.getText());
			widthNum = Double.parseDouble(width.getText());
			heightNum = Double.parseDouble(height.getText());
			radiusNum = Double.parseDouble(radius.getText());
			radius2Num = Double.parseDouble(radius2.getText());
			rollNum = Double.parseDouble(roll.getText());
			pitchNum = Double.parseDouble(pitch.getText());
			yawNum = Double.parseDouble(yaw.getText());
		}
		catch(Exception ex)
		{
		}
		
		quality.setText(Integer.toString(qualityNum));
		sides.setText(Integer.toString(sidesNum));
		length.setText(Double.toString(lengthNum));
		width.setText(Double.toString(widthNum));
		height.setText(Double.toString(heightNum));
		radius.setText(Double.toString(radiusNum));
		radius2.setText(Double.toString(radius2Num));
		roll.setText(Double.toString(rollNum));
		pitch.setText(Double.toString(pitchNum));
		yaw.setText(Double.toString(yawNum));
	}
   
}