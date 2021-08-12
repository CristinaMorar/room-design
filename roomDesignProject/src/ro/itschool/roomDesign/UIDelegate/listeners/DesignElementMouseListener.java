package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.ImageUtil;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;

/**
 * Receives mouse events (right-click, double right-click and left click) from an {@link TransferableItemButton}.
 * 
 * @author Cristina
 *
 */
public class DesignElementMouseListener extends MouseAdapter {
	private TransferableItemButton button;

	public DesignElementMouseListener(TransferableItemButton button) {
		this.button = button;
	}

	public void mousePressed(MouseEvent event) {
		if (event.getButton() == MouseEvent.BUTTON1) {
			CanvasPanel.getInstance().setLayer(button, CanvasPanel.getInstance().highestLayer() + 1);
		}

		if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
			ImageIcon rotatedIcon = (ImageIcon) ImageUtil.rotateImage(button.getIcon());
			button.setIcon(rotatedIcon);
			CanvasPanel.getInstance().revalidate();
			CanvasPanel.getInstance().repaint();
		}
		if (event.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu menu = new JPopupMenu();
			JMenuItem deleteItem = new JMenuItem("delete");
			deleteItem.addActionListener(new DeleteItemListener(button));
			menu.add(deleteItem);
			menu.show(button, button.getWidth() / 2, button.getHeight() / 2);

		}
	}
}
