/*
 * Mandelbrot.java
 */

package csci.hw9.example1;
//modified for color

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


/**
 * A class to draw the Mandelbrot set. The canvas is divided horizontally into
 * segments and each segment is drawn concurrently. The number of segments is
 * equal to the number of processors/cores. original from:
 * http://rosettacode.org/wiki/Mandelbrot_set#Java
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 *
 */
public class Mandelbrot extends JFrame {
	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	private ThreadPool pool;

	public Mandelbrot() {
		super("Mandelbrot Set");
		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initThreadPool(int threadCount) {
		pool = new ThreadPool(threadCount);
	}

	public void createSet() {
		theImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

		// Create threads as many as processors/cores
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				pool.enqueue(new PixelDrawer(MAX, LENGTH, ZOOM, theImage, colors, x, y));
			}
		}

		// Let all threads finish drawing pixels
		repaint();
	}

	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index / (index + 8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public static void main(String[] args) {
		Mandelbrot aMandelbrot = new Mandelbrot();
		aMandelbrot.initThreadPool(Runtime.getRuntime().availableProcessors());
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();
	}
}