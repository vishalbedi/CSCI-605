/*
 * Mandelbrot.java
 */

package csci.hw8.example2;
//modified for color

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


/**
 * A class to draw the Mandelbrot set. The canvas is divided horizontally
 * into segments and each segment is drawn concurrently. The number of
 * segments is equal to the number of processors/cores.
 * original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 *
 */
public class Mandelbrot extends JFrame {
	private final int MAX 	= 5000;
	private final int LENGTH   	= 800;
	private final double ZOOM  	= 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];

	public Mandelbrot() {
		super("Mandelbrot Set");
		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createSet()	{
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		int processors = Runtime.getRuntime().availableProcessors(); // the number of processors/cores
		Thread[] pixelThreads = new Thread[processors];

		// Create threads as many as processors/cores
		for(int i = 0; i < pixelThreads.length; i++ ) {
			pixelThreads[i] = new Thread(new PixelDrawer(MAX, LENGTH, getHeight(), 
					getWidth(), ZOOM, theImage, colors, i));
			pixelThreads[i].start();
			// System.out.println("Thread " + i + " started");
		}
		
		// Let all threads finish drawing pixels
		for(int i = 0; i < pixelThreads.length; i++ ) {	
		       try {
		            pixelThreads[i].join();
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				// System.out.println("Thread " + i + " finished");
		}
		repaint();
	}
	
	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index/256f, 1, index/(index+8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		Mandelbrot aMandelbrot = new Mandelbrot();
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();
	}
}