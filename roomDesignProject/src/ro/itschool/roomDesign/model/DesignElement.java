package ro.itschool.roomDesign.model;

import ro.itschool.roomDesign.UIDelegate.CanvasPanel;

/**
 * This class represents the important information of the design elements added
 * by the user on the {@link CanvasPanel}.
 * 
 * @author Cristina
 *
 */
public class DesignElement {

	private int x;
	private int y;
	private int width;
	private int height;
	private int layer;
	private String imageAddress;

	public DesignElement(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public DesignElement() {
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public String getImageAddress() {
		return imageAddress;
	}

	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + width + " " + height + " " + layer + " " + imageAddress;
	}

}
