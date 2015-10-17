package csci.hw8.example2;

import java.awt.image.BufferedImage;

public class PixelDrawer implements Runnable {
	private final int MAX;
	private final int LENGTH;
	private final int HEIGHT;
	private final int WIDTH;
	private final double ZOOM;
	private BufferedImage theImage;
	private final int[] colors;
	private final int threadNumber;
	
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
	
	public void run(){
		double zx, zy, cX, cY;
		int offset = threadNumber;
		int step = Runtime.getRuntime().availableProcessors();
		for (int y = offset; y < HEIGHT; y = y + step ) {
			for (int x = offset; x < WIDTH; x = x + step) {
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