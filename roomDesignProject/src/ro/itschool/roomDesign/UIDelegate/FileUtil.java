package ro.itschool.roomDesign.UIDelegate;

import java.io.File;
import java.io.FilenameFilter;

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

}
