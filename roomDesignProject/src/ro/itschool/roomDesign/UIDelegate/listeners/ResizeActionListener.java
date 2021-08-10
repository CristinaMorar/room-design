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
		Dimension canvasDimension = DialogsUtil.showDialogDimension("Add the dimension of the room in cm.");
		if (canvasDimension != null) {
			if (canvasDimension.getWidth() < 200 || canvasDimension.getHeight() < 200) {
				DialogsUtil.showErrorMessage("The room dimensions should not be less than 200 cm.");
				this.actionPerformed(e);
				return;
			}
			int frameWidth = canvasDimension.width;
			int frameHeight = canvasDimension.height + Start.mainFrame.getBottomToolbarSize().height
					+ MainFrame.TOP_OFFSET;

			if (Start.mainFrame.isLeftPanelVisible()) {
				frameWidth += Start.mainFrame.getLeftPanel().getWidth() + MainFrame.LEFT_OFFSET;
			}

			Start.mainFrame.setCanvasDimensionLabel(canvasDimension.width, canvasDimension.height);
			Start.mainFrame.setSize(frameWidth, frameHeight);
			Start.mainFrame.setLocationRelativeTo(null);
			Start.mainFrame.revalidate();

		}
	}

}
