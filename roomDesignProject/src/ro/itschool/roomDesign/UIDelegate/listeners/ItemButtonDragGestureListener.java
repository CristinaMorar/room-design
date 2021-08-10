package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;

/**
 * Receive drag gesture events from {@link TransferableItemButton}
 * 
 * @author Cristina
 *
 */
public class ItemButtonDragGestureListener implements DragGestureListener {

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = Cursor.getDefaultCursor();
		TransferableItemButton button = (TransferableItemButton) event.getComponent();

		if (event.getDragAction() == DnDConstants.ACTION_COPY) {
			cursor = DragSource.DefaultCopyDrop;
		}
		event.startDrag(cursor, button);

		CanvasPanel.getInstance().remove(button);
	}

}
