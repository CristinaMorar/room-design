package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.DialogsUtil;

/**
 * Implements an {@code ActionListener} to create a new canvas surface by
 * clearing the old one.
 * 
 * @author Cristina
 *
 */
public class NewCanvasActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = DialogsUtil.showWarningConfirmationMessage("If you continue, you will lose all the current data.");
		if (result == JOptionPane.OK_OPTION) {
			CanvasPanel.getInstance().clearCanvas();
		}

	}

}
