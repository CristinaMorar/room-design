package ro.itschool.roomDesign.UIDelegate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ro.itschool.roomDesign.UIDelegate.listeners.LeftToolbarCheckBoxListener;
import ro.itschool.roomDesign.UIDelegate.listeners.NewCanvasActionListener;
import ro.itschool.roomDesign.UIDelegate.listeners.OpenFileActionListener;
import ro.itschool.roomDesign.UIDelegate.listeners.ResizeActionListener;
import ro.itschool.roomDesign.UIDelegate.listeners.SaveActionListener;
import ro.itschool.roomDesign.UIDelegate.listeners.ToolbarItemsActionListener;

/**
 * This class extends {@code JFrame} and represents the UI of the application,
 * with a {@link BorderLayout} layout.
 * 
 * @author Cristina
 *
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -5093861367470980904L;

	public static final int LEFT_OFFSET = 16;
	public static final int TOP_OFFSET = 80;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int PANELS_BORDER = 5;

	private Dimension bottomToolbarSize;

	private JPanel leftPanel;
	private CanvasPanel canvasPanel;

	private JCheckBoxMenuItem showLeftPanelMenuItem;
	private JLabel canvasDimensionLabel;

	private boolean hasAllSet;

	public MainFrame() {
		this.configure();
		this.createMenuBar();

		this.buildLeftToolbar();
		this.buildBottomToolBar();
		this.buildCanvas();
		pack();

		this.setAllSet(true);
	}

	public void setCanvasDimensionLabel(int width, int height) {
		this.canvasDimensionLabel.setText("Room dimension: " + width + "x" + height);
	}

	/**
	 * Configures this frame's details, creates it at a certain location and dimension.
	 */
	private void configure() {
		this.setTitle("Welcome!");
		this.setSize(WIDTH, HEIGHT);
		this.setMinimumSize(new Dimension(200 + LEFT_OFFSET, 200 + TOP_OFFSET));
		this.setPreferredSize(new Dimension(400 + 150, 400 + TOP_OFFSET));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ImageIcon imageIcon = new ImageIcon("./design.jpg");
		this.setIconImage(imageIcon.getImage());

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				if (MainFrame.this.hasAllSet) {
					int canvasWidth = MainFrame.this.getWidth() - MainFrame.LEFT_OFFSET;
					int canvasHeight = MainFrame.this.getHeight() - MainFrame.this.getBottomToolbarSize().height
							- MainFrame.TOP_OFFSET;

					if (showLeftPanelMenuItem.isSelected()) {
						canvasWidth -= MainFrame.this.getLeftPanel().getWidth();
					}
					MainFrame.this.setCanvasDimensionLabel(canvasWidth, canvasHeight);
				}
			}
		});

		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
	}

	/**
	 * Creates the frame's menu bar with "File" and "View" menus.
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");

		JMenuItem resizeMenuItem = new JMenuItem("Set room size");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		showLeftPanelMenuItem = new JCheckBoxMenuItem("Show design elements");
		showLeftPanelMenuItem.setSelected(true);

		resizeMenuItem.addActionListener(new ResizeActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		openMenuItem.addActionListener(new OpenFileActionListener());
		newMenuItem.addActionListener(new NewCanvasActionListener());
		exitMenuItem.addActionListener((event) -> System.exit(0));
		showLeftPanelMenuItem.addItemListener(new LeftToolbarCheckBoxListener());

		fileMenu.add(resizeMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(newMenuItem);
		fileMenu.add(exitMenuItem);

		viewMenu.add(showLeftPanelMenuItem);

		menuBar.add(fileMenu);
		menuBar.add(viewMenu);

		this.setJMenuBar(menuBar);
	}

	/**
	 * Builds the {@code JScrollPane} from which one can choose the design elements
	 * wanted on {@link CanvasPanel}.
	 */
	private void buildLeftToolbar() {
		JPanel designElementsPanel = new JPanel();
		designElementsPanel.setBackground(new Color(255, 255, 204));
		designElementsPanel.setLayout(new BoxLayout(designElementsPanel, BoxLayout.Y_AXIS));
		this.configureBordersForPanel(designElementsPanel, PANELS_BORDER);

		List<JButton> designElementsList = this.loadDesignElements(FileUtil.designElementsDirectory);

		for (JButton element : designElementsList) {
			element.addActionListener(new ToolbarItemsActionListener());
			element.setAlignmentX(CENTER_ALIGNMENT);
			designElementsPanel.add(element);
			designElementsPanel.add(Box.createVerticalStrut(5));
		}

		JScrollPane scrollPane = new JScrollPane(designElementsPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(100, 400));

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(getLeftPanel(), BoxLayout.Y_AXIS));

		JLabel messageLabel = new JLabel("Select an item.");
		messageLabel.setEnabled(false);

		leftPanel.add(scrollPane);
		leftPanel.add(messageLabel);
		pack();

		this.add(leftPanel, BorderLayout.WEST);
	}

	/**
	 * This method builds the tool bar on which the room's dimensions will be
	 * displayed.
	 */
	private void buildBottomToolBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		this.configureBordersForPanel(panel, PANELS_BORDER);

		canvasDimensionLabel = new JLabel();
		canvasDimensionLabel.setEnabled(false);

		panel.add(Box.createHorizontalGlue());
		panel.add(canvasDimensionLabel);
		pack();

		this.add(panel, BorderLayout.SOUTH);

		this.setBottomToolbarSize(panel.getSize());
	}

	/**
	 * Creates the {@link CanvasPanel} on which the design elements will be handled
	 * by the user.
	 */
	private void buildCanvas() {
		canvasPanel = CanvasPanel.getInstance();

		this.add(canvasPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates {@code JButton}s with tool tips and images loaded from the given directory. 
	 * 
	 * @param directory The directory in which the design elements' images are.
	 * 		  
	 * @return a {@code List<JButton>} representing the design elements loaded from images.
	 */
	private List<JButton> loadDesignElements(File directory) {
		List<JButton> elementsList = new ArrayList<JButton>();
		FilenameFilter imageFilter = FileUtil.filterFilesFromDirectory(directory, FileUtil.imageExtension);
		int imageSize = 50;

		for (File file : directory.listFiles(imageFilter)) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(file);
				ImageIcon imageIcon = FileUtil.getScaledImageIcon(image, imageSize, imageSize);

				JButton designElement = new JButton(imageIcon);

				designElement.setBorder(BorderFactory.createEmptyBorder());
				designElement.setContentAreaFilled(false);
				designElement.setToolTipText(file.getName().replaceFirst(FileUtil.imageExtension, ""));

				elementsList.add(designElement);
			} catch (IOException e) {
				DialogsUtil.showErrorMessage("Something went wrong.");
			}
		}
		return elementsList;
	}

	private void configureBordersForPanel(JPanel panel, int border) {
		panel.setBorder(new EmptyBorder(border, border, border, border));
	}

	public boolean isLeftPanelVisible() {
		return showLeftPanelMenuItem.isSelected();
	}

	public void setLeftToolbarVisible(boolean isVisible) {
		getLeftPanel().setVisible(isVisible);
	}

	public Dimension getBottomToolbarSize() {
		return bottomToolbarSize;
	}

	public void setBottomToolbarSize(Dimension bottomToolbarSize) {
		this.bottomToolbarSize = bottomToolbarSize;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}

	public boolean hasAllSet() {
		return hasAllSet;
	}

	public void setAllSet(boolean hasAllSet) {
		this.hasAllSet = hasAllSet;
	}

}
