/*
 * PixelDrawer.java
 */

package csci.hw9.example1;

import java.awt.image.BufferedImage;

/**
 * A runnable implementation to draw pixels concurrently.
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 */
public class PixelDrawer implements Runnable {
	private final int MAX;
	private final int LENGTH;
	private final double ZOOM;
	private BufferedImage theImage;
	private final int[] colors;
	private int x,y;

	/**
	 * A constructor that takes variables to draw pixels.
	 * 
	 * @param max
	 * @param length
	 * @param height
	 * @param width
	 * @param zoom
	 * @param theImage
	 * @param colors
	 * @param threadNumber
	 */
	public PixelDrawer(int max, int length, double zoom, BufferedImage theImage, int[] colors, int x, int y) {
		this.MAX = max;
		this.LENGTH = length;
		this.ZOOM = zoom;
		this.theImage = theImage;
		this.colors = colors;
		this.x = x;
		this.y = y;
	}

	/**
	 * Draw a segment of the Mandelbrot set.
	 */
	public void run() {
		double zx, zy, cX, cY;
		zx = zy = 0;
		cX = (x - LENGTH) / ZOOM;
		cY = (y - LENGTH) / ZOOM;
		int iter = 0;
		double tmp;
		while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) {
			tmp = zx * zx - zy * zy + cX;
			zy = 2.0 * zx * zy + cY;
			zx = tmp;
			iter++;
		}
		if (iter > 0)
			theImage.setRGB(x, y, colors[iter]);
		else
			theImage.setRGB(x, y, iter | (iter << 8));		
	}
}