package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.DialogsUtil;
import ro.itschool.roomDesign.UIDelegate.FileUtil;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;
import ro.itschool.roomDesign.model.DesignElement;

/**
 * Implements an {@code ActionListener} to save in a file the design elements
 * existing on the {@link CanvasPanel}.
 * 
 * @author Cristina
 *
 */
public class SaveActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = DialogsUtil.showDialogFileName();
		if (fileName != null) {
			saveComponentsToFile(fileName);
		}
	}

	/**
	 * Saves the design elements' details in a file with the name given as parameter
	 * if the file doesn't already exists.
	 * 
	 * @param fileName
	 */
	private void saveComponentsToFile(String fileName) {
		File fileToSave = new File("./saved/" + fileName);
		System.out.println(fileToSave.getPath());
		try {
			if (!fileToSave.createNewFile()) {
				DialogsUtil.showErrorMessage("File already exists.");
			} else {
				FileWriter fileWriter = new FileWriter("./saved/" + fileName);

				List<Component> canvasItems = Arrays.asList(CanvasPanel.getInstance().getComponents());

				for (Component item : canvasItems) {
					TransferableItemButton designElement = (TransferableItemButton) item;

					String elementImageAddress = FileUtil.designElementsDirectory + "\\" + designElement.getName()
							+ FileUtil.imageExtension;

					DesignElement elementToSave = new DesignElement(designElement.getX(), designElement.getY(),
							designElement.getWidth(), designElement.getHeight());
					elementToSave.setLayer(CanvasPanel.getLayer(designElement));
					elementToSave.setImageAddress(elementImageAddress);
					try {
						fileWriter.write(elementToSave.toString() + "\n");
					} catch (IOException e) {
						DialogsUtil.showErrorMessage("Something went wrong.");
						e.printStackTrace();
					}
				}
				fileWriter.close();
				DialogsUtil.showMessage("Success!");
			}
		} catch (IOException e) {
			DialogsUtil.showErrorMessage("Something went wrong.");
			e.printStackTrace();
		}

	}
}
