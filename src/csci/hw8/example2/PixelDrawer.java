/*
 * PixelDrawer.java
 */

package csci.hw8.example2;

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
	private final int HEIGHT;
	private final int WIDTH;
	private final double ZOOM;
	private BufferedImage theImage;
	private final int[] colors;
	private final int threadNumber;
	
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
	public PixelDrawer(int max, int length, int height, int width,
			double zoom, BufferedImage theImage, int[] colors, int threadNumber) {
		this.MAX = max;
		this.LENGTH = length;
		this.HEIGHT = height;
		this.WIDTH = width;
		this.ZOOM = zoom;
		this.theImage = theImage;
		this.colors = colors;
		this.threadNumber = threadNumber;
	}
	
	/**
	 * Draw a segment of the Mandelbrot set. 
	 */
	public void run(){
		double zx, zy, cX, cY;
		int step = threadNumber;
		int segmentHeight = HEIGHT / Runtime.getRuntime().availableProcessors();
		int upperBound = segmentHeight * step;
		int lowerBound = upperBound + segmentHeight;
		
		for (int y = upperBound; y < lowerBound; y++ ) {
			for (int x = 0; x < WIDTH; x++) {
				zx = zy = 0;
				cX = (x - LENGTH) / ZOOM;
				cY = (y - LENGTH) / ZOOM;
				int iter = 0;
				double tmp;
				while ( (zx * zx + zy * zy < 10 ) && ( iter < MAX - 1 ) ) {	
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter++;
				}
				if ( iter > 0 )	
					theImage.setRGB(x, y, colors[iter]);
				else
					theImage.setRGB(x, y, iter | (iter << 8));
			}
		}
	}
}