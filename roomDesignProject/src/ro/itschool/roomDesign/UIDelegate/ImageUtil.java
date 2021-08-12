package ro.itschool.roomDesign.UIDelegate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This is a class to help modifying an image.
 * 
 * @author Cristina
 *
 */
public class ImageUtil {

	/**
	 * Rotates the image from the given icon to 90° clockwise.
	 * 
	 * @param icon which will be rotated
	 * @return the {@code Icon} turned by 90°
	 */
	public static Icon rotateImage(Icon icon) {
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();

		icon.paintIcon(null, g, 0, 0);
		g.dispose();

		ImageIcon i = (ImageIcon) icon;
		BufferedImage bufferdImage = (BufferedImage) i.getImage();
		BufferedImage newImage = new BufferedImage(bufferdImage.getWidth(), bufferdImage.getHeight(),
				bufferdImage.getType());

		Graphics2D g2 = newImage.createGraphics();
		g2.rotate(Math.toRadians(90), bufferdImage.getWidth() / 2, bufferdImage.getHeight() / 2);
		g2.drawImage(bufferdImage, null, 0, 0);
		g2.dispose();

		return new ImageIcon(newImage);

	}

	/**
	 * Scales an {@code Image} to the given dimensions.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 * @return {@code ImagIcon} created with the given image to the desired
	 *         dimension.
	 */
	public static ImageIcon getScaledImageIcon(Image image, int width, int height) {
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();

		return new ImageIcon(resizedImg);
	}

	/**
	 * Saves the pixels from an {@code Image} into an array.
	 * 
	 * @param image
	 * @return the pixels from the given image as an array
	 * @throws InterruptedException
	 */
	public static int[] imagePixelsToArray(Image image) throws InterruptedException {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int pixels[] = new int[width * height];
		PixelGrabber pg = new PixelGrabber(image, 0, 0, width, height, false);
		pg.grabPixels();
		pixels = (int[]) pg.getPixels();

		return pixels;
	}

	/**
	 * Creates a {@code BufferedImage} from an array of pixels.
	 * 
	 * @param pixels
	 * @param width of the image to be created
	 * @param height of the image to be created
	 * @return
	 */
	public static BufferedImage pixelsToBufferedImage(int[] pixels, int width, int height) {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int[] a = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
		System.arraycopy(pixels, 0, a, 0, pixels.length);

		return bufferedImage;
	}
}
