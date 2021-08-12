package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.DialogsUtil;
import ro.itschool.roomDesign.UIDelegate.FileUtil;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;

/**
 * Implements an {@code ActionListener} to create a design element in the middle
 * of the {@link CanvasPanel}.
 * 
 * @author Cristina
 *
 */
public class ToolbarItemsActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Dimension imageDimension = DialogsUtil.showDialogDimension("Please add the dimension in cm.");
		if (imageDimension != null) {
			JButton button = (JButton) e.getSource();

			ImageIcon imageIcon = (ImageIcon) button.getIcon();

			imageIcon = FileUtil.getScaledImageIcon(imageIcon.getImage(), imageDimension.width, imageDimension.height);

			TransferableItemButton itemButton = new TransferableItemButton(imageIcon);

			int x = CanvasPanel.getInstance().getWidth() / 2 - (imageIcon.getIconWidth() / 2);
			int y = CanvasPanel.getInstance().getHeight() / 2 - (imageIcon.getIconHeight() / 2);
			itemButton.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());

			itemButton.setName(button.getToolTipText());

			CanvasPanel.getInstance().setLayer(itemButton, CanvasPanel.getInstance().highestLayer() + 1);

			itemButton.setDoubleBuffered(true);

			CanvasPanel.getInstance().add(itemButton);
			CanvasPanel.getInstance().revalidate();
			CanvasPanel.getInstance().repaint();
		}
	}

}
