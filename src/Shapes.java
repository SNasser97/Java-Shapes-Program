import java.awt.Color;
import java.awt.Shape;


public class Shapes extends XPanel {

	private static final long serialVersionUID = 1L;

	private String name;
	private String colour;
	private int x,y; //coords (position)
	private int width, height;

	public Shapes(String name, String colour, int x, int y, int width, int height) { //constructor for shape
		this.name = name;
		this.colour = colour;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
