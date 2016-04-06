/*
 * Nikhil Pandeti
 * Mrs. Gallatin
 * Period 2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/**
 * An interace for shapes
 */
public interface Shapes
{
	/**
	 * Returns a list of the shape's Faces
	 * @return a list of the shape's Faces
	 */
	ArrayList<Face> getFaces();
	/**
	 * Returns a string representation of the shape
	 * @return a string representation of the shape
	 */
	String toString();
}