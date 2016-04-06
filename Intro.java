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
   A welcome frame
*/
public class Intro extends JFrame implements ActionListener
{
	JButton newFile, loadFile;
	BackgroundPainter bkg;
	IntroButtons buttons;
	/**
	 * A constructor for Intro
	 */
	public Intro()
	{
		setTitle("Sculpture Maker");
		try{
			setIconImage(ImageIO.read( getClass().getResourceAsStream("icon3.png")));
		}
		catch(IOException e){}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		
		setLayout(new BorderLayout());
		
		bkg = new BackgroundPainter();
		add(bkg, BorderLayout.CENTER);
		
		newFile = new JButton("Create a new Sculpture!");
		loadFile = new JButton("Load a previous Sculpture!");
		
		newFile.addActionListener(this);
		loadFile.addActionListener(this);
		
		buttons = new IntroButtons(newFile, loadFile);
		add(buttons, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	/**
	 * Processes button presses
	 */
	public void actionPerformed(ActionEvent e)
   	{
   		if(((JButton)e.getSource()).getText().equals("Create a new Sculpture!"))
   		{
   			new SculptureDrawer(null);
   			dispose();
   			JOptionPane.showMessageDialog(null, "Welcome to Sculpture Maker! Here are some quick tips. You can click 'Help' whenever you need to look back at these.\n\n" +
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
   		if(((JButton)e.getSource()).getText().equals("Load a previous Sculpture!"))
   		{
   			JFileChooser chooser = new JFileChooser();
			if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
			{
				new SculptureDrawer(chooser.getSelectedFile());
   				dispose();
			}
   			
   		}
   	}
   	
   	
	/**
	 * A component that paint the background image
	 */
	private class BackgroundPainter extends JComponent
	{
		BufferedImage bkg;
		/**
		 * A construcor for BackgroundPainter
		 */
		public BackgroundPainter()
		{
			try
			{
				bkg = ImageIO.read( getClass().getResourceAsStream("intro_image.png"));
			}
			catch(IOException e)
			{
				bkg = new BufferedImage(1,1,BufferedImage.TYPE_3BYTE_BGR);
			}
		}
		/**
		 * Paints the component
		 * @param g the graphics
		 */
		public void paintComponent(Graphics g)
		{
			//System.out.println ((bkg.getWidth()/2 - getWidth()/2) +" "+ (bkg.getHeight()/2 - getHeight()/2));
			g.drawImage(bkg, getWidth()/2 - bkg.getWidth()/2, getHeight()/2 - bkg.getHeight()/2, this);
			g.setFont(new Font("Kunstler Script", Font.BOLD, 120));
			g.setColor(Color.BLUE);
			g.drawString("Welcome to Sculpture Maker", 50, 150);
			g.setFont(new Font("Kunstler Script", Font.BOLD, 60));
			g.drawString("By Nikhil Pandeti", 300, 250);
			
		}
	}
	/**
	 * Holds the buttons for the Intro frame
	 */
	private class IntroButtons extends JComponent
	{  
		private JButton newButton, loadButton;
		/**
		 * A constructor for IntroButtons
		 * @param newButton new button
		 * @param loadButton load button
		 */
		public IntroButtons(JButton newButton,JButton loadButton)
		{
			setLayout(new FlowLayout());
			
			this.newButton = newButton;
			this.loadButton = loadButton;
			
			add(newButton);
			add(loadButton);
			
			setVisible(true);
		}
	}
}
	