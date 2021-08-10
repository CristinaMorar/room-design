package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ro.itschool.roomDesign.Start;

/**
 * This implementation of {@code ItemListener} receives events when the check
 * box's state from "Show design elements" is changed.
 * 
 * @author Cristina
 *
 */
public class LeftToolbarCheckBoxListener implements ItemListener {

	/**
	 * The design elements' scroll pane is visible according to the check box's
	 * state.
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (Start.mainFrame.isLeftPanelVisible()) {
			Start.mainFrame.setLeftToolbarVisible(true);
			Start.mainFrame.setSize(Start.mainFrame.getWidth() + Start.mainFrame.getLeftPanel().getWidth(),
					Start.mainFrame.getHeight());
		} else {
			Start.mainFrame.setLeftToolbarVisible(false);
			Start.mainFrame.setSize(Start.mainFrame.getWidth() - Start.mainFrame.getLeftPanel().getWidth(),
					Start.mainFrame.getHeight());
		}
		Start.mainFrame.revalidate();
	}

}
