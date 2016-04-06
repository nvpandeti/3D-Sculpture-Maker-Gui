/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;

/**
   A Frame for viewing the shapes sans the ability to change them
*/
public class Viewer extends JFrame implements ActionListener, KeyListener, MouseMotionListener
{
	private JCheckBox colorButton, shadingButton, wireButton;
	private ViewerButtons buttons;
	private ViewerPainter viewerPainter;
	private boolean[] keys;
	private double posH, posZ, realX, realY, realZ, r;
	private int mX, mY, tempX, tempY;
	double[] origin, light;
	/**
	 * A constructor for Viewer
	 * @param load The file you wish to view from
	 */
	public Viewer(File load)
	{
		setTitle("Sculpture Maker");
		try{
		setIconImage(ImageIO.read( getClass().getResourceAsStream("icon2.png")));
		}
		catch(IOException e){}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		//setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		setLayout(new BorderLayout());
		addKeyListener(this);
		
		viewerPainter = new ViewerPainter();
		viewerPainter.setFocusable(true);
		viewerPainter.addMouseMotionListener(this);
		viewerPainter.load(load);
		add(viewerPainter, BorderLayout.CENTER);
		
		colorButton = new JCheckBox("Color");
		shadingButton = new JCheckBox("Shading");
		wireButton = new JCheckBox("Wire");
		
		colorButton.addActionListener(this);
		shadingButton.addActionListener(this);
		wireButton.addActionListener(this);
		
		colorButton.addKeyListener(this);
		shadingButton.addKeyListener(this);
		wireButton.addKeyListener(this);
		
		buttons = new ViewerButtons(colorButton, shadingButton, wireButton);
		add(buttons, BorderLayout.EAST);
		
		colorButton.setSelected(true);
		shadingButton.setSelected(true);
		wireButton.setSelected(true);
		
		setVisible(true);
		
		mX = mY = tempX = tempY = -1;
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
		
		viewerPainter.setReal(realX, realY, realZ);
		viewerPainter.setOrigin(origin);
		Face.setReal(realX, realY, realZ);

		
	}
	/**
	 * Processes any changes
	 */
	public void repaint()
	{
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
    	
    	viewerPainter.setReal(realX, realY, realZ);
		Face.setReal(realX, realY, realZ);

   	  	viewerPainter.drawComponent(getGraphics(), colorButton.isSelected(), shadingButton.isSelected(), wireButton.isSelected());
	}
	/**
	 * Processes button clicks
	 * @param e An ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
   	{
   		if(!colorButton.isSelected())
   		{
   			shadingButton.setSelected(false);
   		}
   		if(shadingButton.isSelected())
   		{
   			colorButton.setSelected(true);
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
	 * A class that holds the Viewer's checkboxes
	 */
	private class ViewerButtons extends JComponent
	{  
		private JCheckBox colorButton, shadingButton, wireButton;
		/**
		 * A constructor for ShapeSelector
		 * @param colorButton Color button
		 * @param shadingButton Shading button
		 * @param wireButton WireFrame button
		 */
		public ViewerButtons(JCheckBox colorButton, JCheckBox shadingButton, JCheckBox wireButton)
		{
			setLayout(new GridLayout(3,1,5,5));
			
			this.colorButton = colorButton;
			this.shadingButton = shadingButton;
			this.wireButton = wireButton;
			
			add(colorButton);
			add(shadingButton);
			add(wireButton);
			
			setVisible(true);
		}
	}
	/**
 	 * A component that draws the shapes
 	 */
	private class ViewerPainter extends JComponent
	{
		
		private ArrayList<Shapes> shapes;
		private Color color;
		private double[] origin;
		private double realX, realY, realZ;
		/**
		 * A default constructor for ViewerPainter
		 */
		public ViewerPainter()
		{
			//setPreferredSize(new Dimension(400,500));
			shapes = new ArrayList<Shapes>();
			color = Color.RED;
			origin = new double[3];
			realX = -2;
			realY = -6;
			realZ = 6.5;
		}
		/**
		 * Sets the origin coordinates
		 * @param origin an array of coordinates (x,y,z)
		 */
		public void setOrigin(double[] origin)
		{
			this.origin = origin;
		}
		/**
		 * Sets the real position coordinates
		 * @param x X
		 * @param y Y
		 * @param z Z
		 */
		public void setReal(double x,double y, double z)
		{
			realX = x;
			realY = y;
			realZ = z;
		}
		/**
		 * Draws the shapes. It also double buffers
		 * @param g2 the graphics
		 * @param colorB true if you want to draw colors
		 * @param shadingB true if you want to shade colors
		 * @param wireB true if you want to draw wire frame
		 */
		public void drawComponent(Graphics g2, boolean colorB, boolean shadingB, boolean wireB)
		{
			//super.paintComponent(g);
			Image img = createImage(getWidth(), getHeight());
			
			Graphics g = img.getGraphics();
			
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(), getHeight());
			
			ArrayList<Face> faces = new ArrayList<Face>();
		    for(Shapes s: shapes)
		        for(Face face: s.getFaces())
		            faces.add(face);
		    Collections.sort(faces);
		    
		    HashMap<double[], double[]> calculatedCorners = new HashMap<double[], double[]>();
		    
		    for(Face face: faces)
		    {
		        ArrayList<Double> angleD = new ArrayList<Double>();
		        ArrayList<Double> angleR = new ArrayList<Double>();
		        for(double[] corner : face.getCorners())
		        {
		            if(!calculatedCorners.containsKey(corner))
		            {
		                angleD.add( Math.toDegrees( Math.acos( ( (origin[0] - realX)*(corner[0] - realX) + (origin[1] - realY)*(corner[1] - realY) + (origin[2] - realZ)*(corner[2] - realZ) ) 
		                                                        / ( Math.sqrt( Math.pow( origin[0] - realX, 2) + Math.pow( origin[1] - realY, 2) + Math.pow( origin[2] - realZ, 2) ) *  Math.sqrt( Math.pow( corner[0] - realX, 2) + Math.pow( corner[1] - realY, 2) + Math.pow( corner[2] - realZ, 2) ) ) ) ) );
		                
		                double t = - ( (origin[0] - realX)*(realX - corner[0]) + (origin[1] - realY)*(realY - corner[1]) + (origin[2] - realZ)*(realZ - corner[2]) ) / ( Math.pow(origin[0] - realX, 2) + Math.pow(origin[1] - realY, 2) + Math.pow(origin[2] - realZ, 2) );
		                double[] vertex = {realX + (origin[0] - realX)*t, realY + (origin[1] - realY)*t, realZ + (origin[2] - realZ)*t};
		                double[] perpendicular = {vertex[0] - (origin[1] - realY), vertex[1] + (origin[0] - realX), vertex[2]};
		                double R = Math.acos( ( (perpendicular[0] - vertex[0])*(corner[0] - vertex[0]) + (perpendicular[1] - vertex[1])*(corner[1] - vertex[1]) + (perpendicular[2] - vertex[2])*(corner[2] - vertex[2]) ) 
		                               / ( Math.sqrt( Math.pow( perpendicular[0] - vertex[0], 2) + Math.pow( perpendicular[1] - vertex[1], 2) + Math.pow( perpendicular[2] - vertex[2], 2) ) *  Math.sqrt( Math.pow( corner[0] - vertex[0], 2) + Math.pow( corner[1] - vertex[1], 2) + Math.pow( corner[2] - vertex[2], 2) ) ) );
		                if(corner[2]<perpendicular[2])
		                    angleR.add(2*Math.PI - R);
		                else
		                    angleR.add(R);
		                double[] temp = {angleD.get(angleD.size()-1), angleR.get(angleR.size()-1)};
		                calculatedCorners.put(corner, temp);
		            }
		            else
		            {
		                
		                angleD.add(calculatedCorners.get(corner)[0]);
		                angleR.add(calculatedCorners.get(corner)[1]);
		            }
		        }
		        int[] pointsX = new int[face.getCorners().length];
		        int[] pointsY = new int[face.getCorners().length];
		        boolean behind = false;
		        for (int i = 0; i<pointsX.length; i++)
		        {
		            if(angleD.get(i)>150)
		                behind = true;
		            pointsX[i] = (int)Math.round(getWidth()/2.0+angleD.get(i)*15*Math.cos(angleR.get(i)));
		            pointsY[i] = (int)Math.round(getHeight()/2.0-angleD.get(i)*15*Math.sin(angleR.get(i)));
		        }
		        
		        if(!behind)
		        {
		        	if(shadingB)
		        	{
		        		g.setColor(face.getShading());
		        		g.fillPolygon(pointsX, pointsY, pointsX.length);
		        	}
		        	else if(colorB)
		        	{
		        		g.setColor(face.getColor());
		        		g.fillPolygon(pointsX, pointsY, pointsX.length);
		        	}
		        	if(wireB)
		        	{
		        		g.setColor(Color.lightGray);
		        		g.drawPolyline(pointsX, pointsY, pointsX.length);
		        	}
		        	
		        }
		        
			//System.out.println (getWidth()+ " " + getHeight());
			//System.out.println(g.getClipBounds().getX() +" "+g.getClipBounds().getY());
			//g.fillRect(0,0,(int)g.getClipBounds().getX(),(int)g.getClipBounds().getY());
			
		    }
		    //g2 = g;
		    g.dispose();
		    g2.drawImage(img, getX()+8, getY()+31, this);
		}
		/**
		 * Loads the shapes from a specified file.
		 * @param f the specified file
		 */
		public void load(File f)
		{
			if(f != null)
			{
				try
				{
					Scanner in = new Scanner(f);
					while(in.hasNextLine())
					{
						String s = in.next();
						if(s.equals("Cube"))
						{
							shapes.add( new Cube(new Color(in.nextInt(), in.nextInt(), in.nextInt()), 
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble()));
						}
						else if(s.equals("Sphere"))
						{
							shapes.add( new Sphere(new Color(in.nextInt(), in.nextInt(), in.nextInt()), 
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextInt()));
						}
						else if(s.equals("Cylinder"))
						{
							shapes.add( new Cylinder(new Color(in.nextInt(), in.nextInt(), in.nextInt()),
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextInt(),
												in.nextDouble(),
												in.nextDouble(),
												in.nextDouble()));
																	
						}
						else
						{
							shapes.add( new Torus(new Color(in.nextInt(), in.nextInt(), in.nextInt()),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextInt(),
											in.nextDouble(),
											in.nextDouble(),
											in.nextDouble()));
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
		
		}
	}
}