package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ro.itschool.roomDesign.Start;
import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.DialogsUtil;
import ro.itschool.roomDesign.UIDelegate.MainFrame;

/**
 * Implements an {@code ActionListener} to change the size of the frame to fit
 * the {@link CanvasPanel} dimensions asked by the user.
 * 
 * @author Cristina
 *
 */
public class ResizeActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Dimension roomDimension = DialogsUtil.showDialogDimension("Add the dimension of the room in cm.");
		if (roomDimension != null) {
			if (roomDimension.getWidth() < 200 || roomDimension.getHeight() < 200) {
				DialogsUtil.showErrorMessage("The room dimensions should not be less than 200 cm.");
				this.actionPerformed(e);
				return;
			}
			int frameWidth = roomDimension.width;
			int frameHeight = roomDimension.height + Start.mainFrame.getBottomToolbarSize().height
					+ MainFrame.TOP_OFFSET;

			if (Start.mainFrame.isLeftPanelVisible()) {
				frameWidth += Start.mainFrame.getLeftPanel().getWidth() + MainFrame.LEFT_OFFSET;
			}

			Start.mainFrame.setCanvasDimensionLabel(roomDimension.width, roomDimension.height);
			Start.mainFrame.setSize(frameWidth, frameHeight);
			Start.mainFrame.setLocationRelativeTo(null);
			Start.mainFrame.revalidate();

		}
	}

}
