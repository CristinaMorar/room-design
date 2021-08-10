package ro.itschool.roomDesign.UIDelegate;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Helps creating fast dialogs.
 * 
 * @author Cristina
 *
 */
public class DialogsUtil {

	/**
	 * Creates a confirm dialog for the user to provide dimensions for the
	 * wanted object.
	 * 
	 * @param message stating what the dimensions are for
	 * @return the inputed dimensions as a {@code Dimension} object or {@code null} if canceled or exited.
	 */
	public static Dimension showDialogDimension(String message) {
		JTextField widthField = new JTextField();
		JTextField heightField = new JTextField();
		JPanel panel = new JPanel(new GridLayout(5, 1));

		panel.add(new JLabel(message));
		panel.add(new JLabel("Width: "));
		panel.add(widthField);
		panel.add(new JLabel("Length:"));
		panel.add(heightField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Dimension", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			if (widthField.getText().trim().isEmpty() || heightField.getText().trim().isEmpty()) {
				showErrorMessage("Please add values in both fields.");
				return showDialogDimension(message);
			} else {
				int widthInPX = Integer.parseInt(widthField.getText()) * CanvasPanel.CELL_SIZE_PX
						/ CanvasPanel.CELL_SIZE_CM;
				int heightInPX = Integer.parseInt(heightField.getText()) * CanvasPanel.CELL_SIZE_PX
						/ CanvasPanel.CELL_SIZE_CM;
				return new Dimension(widthInPX, heightInPX);
			}
		} else {
			return null;
		}
	}

	
	/**
	 * Creates a confirm dialog asking for the name of the file to be saved.
	 * 
	 * @return the given file name  or {@code null} if canceled or exited.
	 */
	public static String showDialogFileName() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		JTextField fileNameField = new JTextField();

		panel.add(new JLabel("File name: "));
		panel.add(fileNameField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Save", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			return fileNameField.getText() + ".txt";
		} else {
			return null;
		}
	}

	public static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Failure", JOptionPane.ERROR_MESSAGE);
	}

	public static int showWarningConfirmationMessage(String message) {
		return JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
	}

}
