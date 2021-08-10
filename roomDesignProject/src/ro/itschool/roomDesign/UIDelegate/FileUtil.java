package ro.itschool.roomDesign.UIDelegate;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.ImageIcon;

/**
 * Created to facilitate the use of files.
 * 
 * @author Cristina
 *
 */
public class FileUtil {

	public static String imageExtension = ".png";
	public static File designElementsDirectory = new File("./designElements");

	/**
	 * Filters by extension the files from the given directory.
	 * 
	 * @param directory the files' directory address
	 * @param extension of the desired files
	 * @return a {@code FilenameFilter} for the given data
	 */
	public static FilenameFilter filterFilesFromDirectory(File directory, String extension) {
		FilenameFilter fileFilter = new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				if (name.endsWith(extension)) {
					return (true);
				}
				return (false);
			}
		};
		return fileFilter;
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
}
