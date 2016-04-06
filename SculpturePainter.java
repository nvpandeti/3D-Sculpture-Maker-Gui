/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
 
 //*** So, it turns out you don't need the setPreferredSize.  getWidth() and getHeight() are the methods.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/**
 * A component that draws the shapes
 */
public class SculpturePainter extends JComponent
{
	
	private ArrayList<Shapes> shapes;
	private Color color;
	private double[] origin;
	private double realX, realY, realZ;
	/**
	 * A default constructor for SculpturePainter
	 */
	public SculpturePainter()
	{
		//setPreferredSize(new Dimension(400,500));
		shapes = new ArrayList<Shapes>();
		color = Color.RED;
		origin = new double[3];
		realX = realY = realZ = 0;
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
	 * Adds a temporary shape
	 * @param s A shape that implements Shapes
	 */
	public void addTempShape(Shapes s)
	{
		shapes.add(s);
	}
	/**
	 * Adds a permanent shape
	 * @param s A shape that implements Shapes
	 */
	public void addShape(Shapes s)
	{
		shapes.add(shapes.size()-1, s);
	}
	/**
	 * Removes a shape
	 */
	public void removeTempShape()
	{
		if(shapes.size()>1)
			shapes.remove(shapes.size()-2);
	}
	/**
	 * Draws the shapes. It also double buffers
	 * @param g2 the graphics
	 */
	public void drawComponent(Graphics g2)
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
	        g.setColor(face.getShading());
	        if(!behind)
	        {
	        	g.fillPolygon(pointsX, pointsY, pointsX.length);
	        	//g.setColor(Color.BLACK);
	        	//g.drawPolyline(pointsX, pointsY, pointsX.length);
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
	 * Saves the shapes in a specified file.
	 */
	public File save() throws IOException
	{
		JFileChooser chooser = new JFileChooser();
		File ret = null;
		if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			ret = chooser.getSelectedFile();
			PrintWriter outFile = new PrintWriter(ret);
			for (int i = 0; i<shapes.size()-1; i++)
			{
				outFile.println(shapes.get(i).toString());
			}
			outFile.close();
		}
		return ret;
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