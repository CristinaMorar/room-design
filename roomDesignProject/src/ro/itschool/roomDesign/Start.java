package ro.itschool.roomDesign;

import ro.itschool.roomDesign.UIDelegate.DialogsUtil;
import ro.itschool.roomDesign.UIDelegate.MainFrame;

/**
 * This class starts the program by creating a {@link MainFrame} object.
 *  
 * @author Cristina
 *
 */
public class Start {

	public static MainFrame mainFrame;

	public static void main(String[] args) {
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		DialogsUtil.showMessage("Welcome! We wish you a great experiance with us. To select an item, double-click it.");
	}
}
