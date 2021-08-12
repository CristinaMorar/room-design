package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.ImageUtil;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;

/**
 * Receives drop target events from {@link TransferableItemButton}, extending
 * {@code DropTargetAdapter}
 */
public class MyDropTargetListener extends DropTargetAdapter {

	private final DropTarget dropTarget;

	public MyDropTargetListener(CanvasPanel canvasPanel) {
		dropTarget = new DropTarget(canvasPanel, DnDConstants.ACTION_COPY, this, true, null);
	}

	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			TransferableItemButton button = (TransferableItemButton) tr
					.getTransferData(TransferableItemButton.buttonFlavor);

			//The Image from Icon is an instance of ToolkitImage which cannot be converted to BufferedImage
			ImageIcon i = (ImageIcon) button.getIcon();
			Image image = (Image) i.getImage();
			int pixels[] = ImageUtil.imagePixelsToArray(image);

			BufferedImage bufferedImage = ImageUtil.pixelsToBufferedImage(pixels, image.getWidth(null),
					image.getHeight(null));
			button.setIcon(new ImageIcon(bufferedImage));

			button.configure();

			if (event.isDataFlavorSupported(TransferableItemButton.buttonFlavor)) {
				event.acceptDrop(DnDConstants.ACTION_COPY);
				Point point = MouseInfo.getPointerInfo().getLocation();
				SwingUtilities.convertPointFromScreen(point, CanvasPanel.getInstance());
				button.setBounds(point.x, point.y, button.getIcon().getIconWidth(), button.getIcon().getIconHeight());

				button.setDoubleBuffered(true);
				CanvasPanel.getInstance().add(button);
				CanvasPanel.getInstance().revalidate();
				CanvasPanel.getInstance().repaint();

				event.dropComplete(true);
				return;
			}

			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}

	public DropTarget getDropTarget() {
		return dropTarget;
	}
}
