/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

/**
   A Frame that is the main part of Sculpture Drawer
*/
public class SculptureDrawer extends JFrame implements ActionListener, KeyListener, MouseMotionListener
{
	//private RectanglesComponent rectangle;
	private TextBoxComponent textBox;
	private ShapeSelector shapeSelector;
	private ColorPicker colorPicker;
	private SculpturePainter sculpturePainter;
	private ViewerButtonComponent viewerButtonComponent;
	private int num;
	private JButton draw, viewer, save, help;
	private JButton redButton,greenButton,blueButton,yellowButton,purpleButton,orangeButton,blackButton,grayButton,whiteButton;
	private static int WIDTH = 20;
	private static int HEIGHT = 40;
	private Color color;
	private JRadioButton cubeButton,sphereButton,prismButton,cylinderButton,torusButton;
	private boolean[] keys;
	private double posH, posZ, realX, realY, realZ, r;
	private int mX, mY, tempX, tempY;
	private double[] origin, light;
	
	private static final Color PURPLE = new Color(127, 0, 255);
	/**
	 * A constructor for SculptureDrawer
	 * @param load A specified file to load from. Null if new sculpture.
	 */
  	public SculptureDrawer(File load)
  	{
		setSize(500, 600);
		setTitle("Sculpture Maker");
		try{
		setIconImage(ImageIO.read( getClass().getResourceAsStream("icon.jpg")));
		}
		catch(IOException e){}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		setLayout(new BorderLayout());
		
		//smiley = new FaceComponent();
		
		//add(smiley,BorderLayout.CENTER);
		
		/*
		radiusText = new JLabel("radius: ");
		add(radiusText,BorderLayout.NORTH);
		radius = new JTextField(8);
		//radius.addActionListener(this);
		//radius.addMouseListener(this);
		add(radius,BorderLayout.NORTH);
		*/
		
		addKeyListener(this);
		setFocusable(true);
		
		cubeButton = new JRadioButton("Cube");
		cubeButton.setSelected(true);
		sphereButton = new JRadioButton("Sphere");
		prismButton = new JRadioButton("Prism");
		cylinderButton = new JRadioButton("Cylinder");
		torusButton = new JRadioButton("Torus");
		
		cubeButton.addKeyListener(this);
		sphereButton.addKeyListener(this);
		prismButton.addKeyListener(this);
		cylinderButton.addKeyListener(this);
		torusButton.addKeyListener(this);
		
		shapeSelector = new ShapeSelector(cubeButton,sphereButton,prismButton,cylinderButton,torusButton);
		add(shapeSelector, BorderLayout.NORTH);
		
		cubeButton.setActionCommand("Cube");
	    sphereButton.setActionCommand("Sphere");
	    prismButton.setActionCommand("Prism");
	    cylinderButton.setActionCommand("Cylinder");
	    torusButton.setActionCommand("Torus");
		
		cubeButton.addActionListener(this);
		sphereButton.addActionListener(this);
		prismButton.addActionListener(this);
		cylinderButton.addActionListener(this);
		torusButton.addActionListener(this);
		
		redButton = new JButton();
		greenButton = new JButton();
		blueButton = new JButton();
		yellowButton = new JButton();
		purpleButton = new JButton();
		orangeButton = new JButton();
		blackButton = new JButton();
		grayButton = new JButton();
		whiteButton = new JButton();
		
		redButton.setBackground(Color.red);
		greenButton.setBackground(Color.green);
		blueButton.setBackground(Color.blue);
		yellowButton.setBackground(Color.yellow);
		purpleButton.setBackground(PURPLE);
		orangeButton.setBackground(Color.orange);
		blackButton.setBackground(Color.black);
		grayButton.setBackground(Color.gray);
		whiteButton.setBackground(Color.white);
		
		redButton.setOpaque(true);
		greenButton.setOpaque(true);
		blueButton.setOpaque(true);
		yellowButton.setOpaque(true);
		purpleButton.setOpaque(true);
		orangeButton.setOpaque(true);
		blackButton.setOpaque(true);
		grayButton.setOpaque(true);
		whiteButton.setOpaque(true);
		
		colorPicker = new ColorPicker(redButton,greenButton,blueButton,yellowButton,purpleButton,
										orangeButton,blackButton,grayButton,whiteButton);
		add(colorPicker, BorderLayout.WEST);
		
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);
		yellowButton.addActionListener(this);
		purpleButton.addActionListener(this);
		orangeButton.addActionListener(this);
		blackButton.addActionListener(this);
		grayButton.addActionListener(this);
		whiteButton.addActionListener(this);
		
		redButton.addKeyListener(this);
		greenButton.addKeyListener(this);
		blueButton.addKeyListener(this);
		yellowButton.addKeyListener(this);
		purpleButton.addKeyListener(this);
		orangeButton.addKeyListener(this);
		blackButton.addKeyListener(this);
		grayButton.addKeyListener(this);
		whiteButton.addKeyListener(this);
		
		draw = new JButton("Draw");
		textBox = new TextBoxComponent(draw);
		draw.addActionListener(this);
		draw.addKeyListener(this);
		add(textBox, BorderLayout.SOUTH);
		
		viewer = new JButton("Save and Open Viewer");
		save = new JButton("Save");
		help = new JButton("Help");
		viewer.addActionListener(this);
		save.addActionListener(this);
		help.addActionListener(this);
		viewer.addKeyListener(this);
		save.addKeyListener(this);
		help.addKeyListener(this);
		viewerButtonComponent = new ViewerButtonComponent(viewer, save, help);
		add(viewerButtonComponent, BorderLayout.EAST);
		
		
		sculpturePainter = new SculpturePainter();
		sculpturePainter.setFocusable(true);
		sculpturePainter.addMouseMotionListener(this);
		mX = mY = tempX = tempY = -1;
		sculpturePainter.load(load);
		add(sculpturePainter, BorderLayout.CENTER);
		

		//rectangle = new RectanglesComponent();
		//add(rectangle, BorderLayout.CENTER);
		//rectangle.addMouseListener(this);
		setVisible(true);
		num = 0;
		color = Color.RED;
		
		keys = new boolean[10];
		posH = 50;
		posZ = -31;
		realX = -2;
		realY = -6;
		realZ = 6.5;
		r = 10;
		origin = new double[3];
		origin[0] = 0;
		origin[1] = 0;
		origin[2] = 0;
		light = new double[3];
		light[0] = 0;
		light[1] = 0;
		light[2] = 3;
		Face.setLight(light);
		
		textBox.setShape("Cube");
		textBox.setX(Double.toString(realX));
		textBox.setY(Double.toString(realY));
		textBox.setZ(Double.toString(realZ));
		sculpturePainter.setReal(realX, realY, realZ);
		sculpturePainter.setOrigin(origin);
		//sculpturePainter.addTempShape(new Sphere(Color.WHITE, light[0], light[1], light[2], .3,5));
		Face.setReal(realX, realY, realZ);
		sculpturePainter.addTempShape(getShapes());
		repaint();
      
   }
   /**
	* Processes any changes
	*/
   public void repaint()
   {  
   		try
   		{
   			origin[0] = Double.parseDouble(textBox.getx());
   			origin[1] = Double.parseDouble(textBox.gety());
   			origin[2] = Double.parseDouble(textBox.getz());
   		}
   		catch(Exception e){}
   		
   		realX = origin[0] - r * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
   		realY = origin[1] - r * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
    	realZ = origin[2] - r * Math.sin(Math.toRadians(posZ));
    	
    	textBox.checkText();
   		
   	  	if(keys[0])
   	  	{
   	  		posH -= 3;
   	  		if(posH<0)
   	  			posH = 359;
   	  	}
   	  	if(keys[1])
   	  	{
   	  		posH += 3;
   	  		if(posH>360)
   	  			posH = 0;
   	  	}
   	  	if(keys[2] && posZ>-89)
   	  	{
   	  		posZ -= 2;
   	  	}
   	  	if(keys[3] && posZ<89)
   	  	{
   	  		posZ += 2;
   	  	}
   	  	if(keys[4])
   	  	{
   	  		//System.out.println ("Blahhhhh");
   	  		realX += .5 * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
        	realY += .5 * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
        	realZ += .5 * Math.sin(Math.toRadians(posZ));
   	  	}
   	  	if(keys[5])
   	  	{
   	  		realX -= .5 * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
        	realY -= .5 * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
        	realZ -= .5 * Math.sin(Math.toRadians(posZ));
   	  	}
   	  	if(keys[6])
   	  	{
   	  		realX += .5 * Math.sin(Math.toRadians(posH));
        	realY -= .5 * Math.cos(Math.toRadians(posH));
   	  	}
   	  	if(keys[7])
   	  	{
   	  		realX -= .5 * Math.sin(Math.toRadians(posH));
        	realY += .5 * Math.cos(Math.toRadians(posH));
   	  	}
   	  	if(keys[8])
   	  	{
   	  		realX += .5 * Math.cos(Math.toRadians(posH)) * Math.sin(Math.toRadians(posZ));
        	realY += .5 * Math.sin(Math.toRadians(posH)) * Math.sin(Math.toRadians(posZ));
        	realZ -= Math.sqrt(Math.pow(.5 * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ)),2) + 
        						Math.pow(.5 * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ)), 2));
   	  	}
   	  	if(keys[9])
   	  	{
   	  		realX -= .5 * Math.cos(Math.toRadians(posH)) * Math.sin(Math.toRadians(posZ));
        	realY -= .5 * Math.sin(Math.toRadians(posH)) * Math.sin(Math.toRadians(posZ));
        	realZ += Math.sqrt(Math.pow(.5 * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ)),2) + 
        						Math.pow(.5 * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ)), 2));
   	  	}
   	  	
   	  	origin[0] = realX + r * Math.cos(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
    	origin[1] = realY + r * Math.sin(Math.toRadians(posH)) * Math.cos(Math.toRadians(posZ));
    	origin[2] = realZ + r * Math.sin(Math.toRadians(posZ));
    	
    	
    	textBox.setX(Double.toString(origin[0]));
    	textBox.setY(Double.toString(origin[1]));
    	textBox.setZ(Double.toString(origin[2]));
    	
    	sculpturePainter.setReal(realX, realY, realZ);
    	//sculpturePainter.setOrigin(origin);
		Face.setReal(realX, realY, realZ);
   	  	//sculpturePainter.setColor(color);
   	  	sculpturePainter.addTempShape(getShapes());
   	  	sculpturePainter.removeTempShape();
   	  	//paintComponents(getGraphics());
   	  	sculpturePainter.drawComponent(getGraphics());
   	  
   }
    /**
	 * Processes button clicks
	 * @param e An ActionEvent
	 */
   	public void actionPerformed(ActionEvent e)
   	{
   		//smiley.repaint();
   		//System.out.println ("Hi "+num);
   		/*num = textBox.getNum().equals("") ? 0:Integer.parseInt(textBox.getNum());
      	remove(textBox);
    	repaint();
 	 	textBox = new TextBoxComponent(draw);
  		add(textBox, BorderLayout.SOUTH);
    	setVisible(true);*/
    	//System.out.println (e.getActionCommand());
    	if(e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Save and Open Viewer"))
    	{
    		try
    		{
    			
    			new Viewer(sculpturePainter.save());
    		}
    		catch(IOException ex){}
    		
    	}
    	if(e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Save"))
    	{
    		try
    		{
    			sculpturePainter.save();
    		}
    		catch(IOException ex){}
    	}
    	if(e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Draw"))
    	{
    		//repaint();
    		try
    		{
    		
	    		//System.out.println ("draw");
	    		sculpturePainter.addShape(getShapes());
	    		
    		}
    		catch(Exception ex) {}
    	}
    	if(e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Help"))
    	{
    		JOptionPane.showMessageDialog(null, "It seem that you may be needing some help, which is probably why you clicked 'help'.\n" +
    											"Place shapes by either pressing 'Enter' or clicking 'Draw'\n" +
    											"To undo a shape press 'Backspace'\n\n" + 
    											"To look around use the arrow keys.\n" +
    											"To move forward and backwards use 'W' and 'S'.\n" +
    											"To move left and right use 'A' and 'D'\n" +
    											"To move up and down use '2' and 'Q'\n" +
    											"To look around your current object just drag your mouse across the screen\n\n" +
    											"To select a different shape, use the buttons at the top of the screen.\n" +
    											"To change the specifics of a shape use the textfields down at the bottom. If you are stuck in a textbox, just click a color button\n" +
    											"To change the color of the shape, use the buttons on the left of the screen.\n\n" +
    											"When you are ready, click 'Save and Open Viewer' to see your work!");
    	}
    	else if(!e.getActionCommand().equals(""))
    	{
    		//System.out.println ("yay");
    		if(e.getActionCommand().equals("Cube"))
	    	{
	    		textBox.setShape("Cube");
	    	}
	    	else if(e.getActionCommand().equals("Sphere"))
	    	{
	    		textBox.setShape("Sphere");
	    	}
	    	else if(e.getActionCommand().equals("Prism"))
	    	{
	    		textBox.setShape("Prism");
	    	}
	    	else if(e.getActionCommand().equals("Cylinder"))
	    	{
	    		textBox.setShape("Cylinder");
	    	}
	    	else if(e.getActionCommand().equals("Torus"))
	    	{
	    		textBox.setShape("Torus");
	    	}
    	}
    	else
    	{
    		color = ((JButton)e.getSource()).getBackground();
    	}
    	
    	repaint();
    	
   	}
   	
   	/**
	 * Updates the keys array based on the key that was pressed
	 * @param e the KeyEvent representing the pressed key
	 */
   	public void keyPressed(KeyEvent e)
	{
		//System.out.println ("blahhhhhhhh");
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[4] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[5] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[6] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[7] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q)
		{
			keys[8] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_2)
		{
			keys[9] = true;
		}
		
		repaint();
	}

	/**
	 * Updates the keys array based on the key that was released
	 * @param e the KeyEvent representing the released key
	 */
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[4] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[5] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[6] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[7] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Q)
		{
			keys[8] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_2)
		{
			keys[9] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			sculpturePainter.removeTempShape();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			try
    		{
    		
	    		//System.out.println ("draw");
	    		sculpturePainter.addShape(getShapes());
	    		
    		}
    		catch(Exception ex) {}
		}
		repaint();
	}

	/**
	 * Needed to satisfy the KeyListener
	 * @param e not used
	 */
	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}
	/**
	 * Resets the mouse position
	 * @param e not used
	 */
	public void mouseMoved(MouseEvent e)
	{
		mX = mY = -1;
		//System.out.println (e.getX() +" "+ e.getY());
	}
	/**
	 * Processes mouse drags
	 * @param e MouseEvent representing a mouse drag
	 */
	public void mouseDragged(MouseEvent e)
	{
		if(mX==-1 && mY == -1)
		{
			mX = e.getX();
			mY = e.getY();
		}
		else
		{
			tempX = e.getX();
			tempY = e.getY();
			
			posH += (tempX - mX)/60.0;
			posZ -= (tempY - mY)/60.0;
			if(posH<0)
   	  			posH = 359;
   	  		if(posH>360)
   	  			posH = 0;
   	  		if(posZ>89)
   	  			posZ = 89;
   	  		if(posZ<-89)
   	  			posZ = -89;
			
			repaint();
		}
		//System.out.println (e.getX() +" "+ e.getY());
	}
	/**
	 * Return the shape that is specified by the current state of the buttons and text fields
	 * @return a shape
	 */
	public Shapes getShapes()
	{
		if(cubeButton.isSelected())
		{
			return new Cube(color, 
							Double.parseDouble(textBox.getx()),
							Double.parseDouble(textBox.gety()),
							Double.parseDouble(textBox.getz()),
							Double.parseDouble(textBox.getlength()),
							Double.parseDouble(textBox.getwidth()),
							Double.parseDouble(textBox.getheight()),
							Double.parseDouble(textBox.getYaw()),
							Double.parseDouble(textBox.getPitch()),
							Double.parseDouble(textBox.getRoll()));
		}
		else if(sphereButton.isSelected())
		{
			return new Sphere(color, 
								Double.parseDouble(textBox.getx()),
								Double.parseDouble(textBox.gety()),
								Double.parseDouble(textBox.getz()),
								Double.parseDouble(textBox.getRadius()),
								Integer.parseInt(textBox.getQuality()));
		}
		else if(prismButton.isSelected())
		{
			return new Cylinder(color,
								Double.parseDouble(textBox.getx()),
								Double.parseDouble(textBox.gety()),
								Double.parseDouble(textBox.getz()),
								Double.parseDouble(textBox.getRadius()),
								Double.parseDouble(textBox.getheight()),
								Integer.parseInt(textBox.getSides()),
								Double.parseDouble(textBox.getYaw()),
								Double.parseDouble(textBox.getPitch()),
								Double.parseDouble(textBox.getRoll()));
													
		}
		else if(cylinderButton.isSelected())
		{
			return new Cylinder(color,
								Double.parseDouble(textBox.getx()),
								Double.parseDouble(textBox.gety()),
								Double.parseDouble(textBox.getz()),
								Double.parseDouble(textBox.getRadius()),
								Double.parseDouble(textBox.getheight()),
								30,
								0,
								Double.parseDouble(textBox.getPitch()),
								Double.parseDouble(textBox.getRoll()));
		}
		else
		{
			return new Torus(color,
							Double.parseDouble(textBox.getx()),
							Double.parseDouble(textBox.gety()),
							Double.parseDouble(textBox.getz()),
							Double.parseDouble(textBox.getRadius()),
							Double.parseDouble(textBox.getRadius2()),
							Integer.parseInt(textBox.getQuality()),
							Double.parseDouble(textBox.getYaw()),
							Double.parseDouble(textBox.getPitch()),
							Double.parseDouble(textBox.getRoll()));
		}
	}
   
   
}