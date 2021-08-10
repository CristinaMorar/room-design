package ro.itschool.roomDesign.UIDelegate;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

/**
 * A {@code JLayeredPane} extension, representing a checked surface on which the user
 * will manage the selected design elements. This class can have only one instance.
 * 
 * @author Cristina
 *
 */
public class CanvasPanel extends JLayeredPane {

	private static final long serialVersionUID = 3117045178557642092L;

	/** Represents the number of cells on the horizontal axe **/
	public static int H_SIZE;
	/** Represents the number of cells on the vertical axe **/
	public static int V_SIZE;

	public static int CELL_SIZE_PX = 20;
	public static int CELL_SIZE_CM = 20;

	private static CanvasPanel canvasPanelInstance = null;

	private CanvasPanel() {
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
	}

	public static CanvasPanel getInstance() {
		if (canvasPanelInstance == null) {
			canvasPanelInstance = new CanvasPanel();
		}
		return canvasPanelInstance;
	}

	@Override
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Dimension dimension = this.getSize();
		H_SIZE = dimension.width / CELL_SIZE_PX + 1;
		V_SIZE = dimension.height / CELL_SIZE_PX + 1;

		this.paintLines((Graphics2D) g);
	}

	public void paintLines(Graphics2D graphics2D) {
		graphics2D.setColor(Color.GRAY);
		for (int row = 0; row < V_SIZE; row++) {
			for (int col = 0; col < H_SIZE; col++) {
				int x = col * CELL_SIZE_PX;
				int y = row * CELL_SIZE_PX;

				graphics2D.drawRect(x, y, CELL_SIZE_PX, CELL_SIZE_PX);

			}
		}
	}

	public void clearCanvas() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
}
