package ro.itschool.roomDesign.UIDelegate.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;
import ro.itschool.roomDesign.UIDelegate.DialogsUtil;
import ro.itschool.roomDesign.UIDelegate.FileUtil;
import ro.itschool.roomDesign.UIDelegate.ImageUtil;
import ro.itschool.roomDesign.UIDelegate.TransferableItemButton;
import ro.itschool.roomDesign.model.DesignElement;

/**
 * Implements an {@code ActionListener} to open from files already made user
 * projects. The current canvas will be cleared and the chosen version will be
 * displayed on the screen (canvas).
 * 
 * @author Cristina
 *
 */
public class OpenFileActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		File directory = new File("./");
		List<String> filesList = this.loadSavedFiles(directory);
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(filesList);

		JList<String> list = new JList<String>(listModel);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setEnabled(true);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		int result = JOptionPane.showConfirmDialog(null, scrollPane, "Open file", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			int result2 = DialogsUtil
					.showWarningConfirmationMessage("If you continue, you will lose all the current data.");
			if (result2 == JOptionPane.OK_OPTION) {
				CanvasPanel.getInstance().clearCanvas();

				try {
					File file = new File("./" + list.getSelectedValue());
					Scanner fileScanner = new Scanner(file);

					while (fileScanner.hasNext()) {
						String[] itemAsArray = fileScanner.nextLine().split(" ");

						DesignElement item = new DesignElement();
						item.setX(Integer.parseInt(itemAsArray[0]));
						item.setY(Integer.parseInt(itemAsArray[1]));
						item.setWidth(Integer.parseInt(itemAsArray[2]));
						item.setHeight(Integer.parseInt(itemAsArray[3]));
						item.setLayer(Integer.parseInt(itemAsArray[4]));
						item.setImageAddress(itemAsArray[5]);

						ImageIcon imageIcon = new ImageIcon(item.getImageAddress());
						imageIcon = ImageUtil.getScaledImageIcon(imageIcon.getImage(), item.getWidth(),
								item.getHeight());

						TransferableItemButton button = new TransferableItemButton(imageIcon);
						button.setBounds(item.getX(), item.getY(), item.getWidth(), item.getHeight());

						String[] imageAddressAsArray = item.getImageAddress().split("/");
						String imageName = imageAddressAsArray[imageAddressAsArray.length - 1];

						button.setName(imageName.replace(FileUtil.imageExtension, ""));

						CanvasPanel.getInstance().setLayer(button, item.getLayer());
						CanvasPanel.getInstance().add(button);
					}
					fileScanner.close();
				} catch (Exception exception) {
					DialogsUtil.showErrorMessage("Something went wrong.");
					exception.printStackTrace();
				}
			}
		}

	}

	/**
	 * Returns a list of the names' files with the ".txt" extension from the given
	 * directory.
	 * 
	 * @param directory to search into
	 * @return {@code List<String>} of the files' names
	 * 
	 */
	private List<String> loadSavedFiles(File directory) {
		FilenameFilter fileFilter = FileUtil.filterFilesFromDirectory(directory, ".txt");
		List<String> itemList = new ArrayList<String>();
		for (File file : directory.listFiles(fileFilter)) {
			itemList.add(file.getName());
		}
		return itemList;
	}

}
