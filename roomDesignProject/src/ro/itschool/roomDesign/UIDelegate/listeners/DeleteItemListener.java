package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;
/**
 * implements an {@code ActionListener} to delete an design element from the {@link CanvasPanel}. 
 * @author Cristina
 *
 */
public class DeleteItemListener implements ActionListener {

	TransferableItemButton actionedButton;

	public DeleteItemListener(TransferableItemButton transferableItemButton) {
		actionedButton = transferableItemButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Delete this item?", "Delete", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			CanvasPanel.getInstance().remove(actionedButton);
			CanvasPanel.getInstance().revalidate();
			CanvasPanel.getInstance().repaint();
		}
	}
}
