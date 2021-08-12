package ro.itschool.roomDesign.UIDelegate;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import ro.itschool.roomDesign.UIDelegate.listeners.DesignElementMouseListener;
import ro.itschool.roomDesign.UIDelegate.listeners.ItemButtonDragGestureListener;
import ro.itschool.roomDesign.UIDelegate.listeners.MyDropTargetListener;

/**
 * This class is an extension of {@code JButton} and implements
 * {@code Transferable} interface. It represents a design element on the canvas.
 * 
 * @author Cristina
 *
 */
public class TransferableItemButton extends JButton implements Transferable {

	private static final long serialVersionUID = -4394201643986198497L;

	private boolean wasConfigured = false;
	public static final DataFlavor buttonFlavor = new DataFlavor(TransferableItemButton.class,
			"A TransferableItemButton Object");
	protected static final DataFlavor[] supportedFlavors = { buttonFlavor, DataFlavor.stringFlavor, };

	public TransferableItemButton(ImageIcon image) {
		super(image);
		if (!wasConfigured) {
			this.configure();
		}
	}

	/**
	 * Configures the current button to be movable on the canvas.
	 */
	@SuppressWarnings("unused")
	public void configure() {
		this.setToolTipText("double-click to rotate/left-click to delete");
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);

		MyDropTargetListener mtl = new MyDropTargetListener(CanvasPanel.getInstance());
		DragSource ds = new DragSource();
		ItemButtonDragGestureListener dragListener = new ItemButtonDragGestureListener();
		ds.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, dragListener);

		this.addMouseListener(new DesignElementMouseListener(this));

		this.wasConfigured = true;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(buttonFlavor) || flavor.equals(DataFlavor.stringFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(buttonFlavor)) {
			return this;
		} else if (flavor.equals(DataFlavor.stringFlavor)) {
			return this.toString();
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}

}
