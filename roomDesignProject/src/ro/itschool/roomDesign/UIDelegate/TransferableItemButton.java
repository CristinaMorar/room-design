package ro.itschool.roomDesign.UIDelegate;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	 * 
	 * Adds listeners so that on a click the element comes on top layer and on
	 * double-click it gives the user the option to delete it.
	 */
	@SuppressWarnings("unused")
	public void configure() {
		this.setToolTipText("double-click to delete");
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);

		MyDropTargetListener mtl = new MyDropTargetListener(CanvasPanel.getInstance());
		DragSource ds = new DragSource();
		ItemButtonDragGestureListener dragListener = new ItemButtonDragGestureListener();
		ds.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, dragListener);

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CanvasPanel.getInstance().setLayer(TransferableItemButton.this,
						CanvasPanel.getInstance().highestLayer() + 1);
			}
		});

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
					int result = JOptionPane.showConfirmDialog(null, "Delete this item?", "Delete",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						CanvasPanel.getInstance().remove(TransferableItemButton.this);
						CanvasPanel.getInstance().revalidate();
						CanvasPanel.getInstance().repaint();
					}
				}
			}
		});
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
