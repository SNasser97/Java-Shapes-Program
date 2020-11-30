import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class XPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int x, y;
	private int width, height;
	private Color colour;

	public XPanel() {

	}
	@Override
	public void paintComponent(Graphics g) { //if function is misspelled will provide err message due to override
		super.paintComponent(g);
		clear(g);
	}

	public void clear(Graphics g) { //same functions, parameter required to specify that it will clear paintComp
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());

	}

	public void clear() { //clears panel
		clear(this.getGraphics());
	}

	public void drawOval(int x, int y, int radius1, int radius2, Color colour) { //arguments for oval
		Graphics g = this.getGraphics();
		g.setColor(colour);
		g.fillOval(x, y, radius1, radius2);
	}

	public void drawRect(int x, int y, int width, int height, Color colour) {
		Graphics g = this.getGraphics();
		g.setColor(colour);
		g.fillRect(x, y, width, height);
	}
	public void drawLine(int x, int y, int x2, int y2, Color colour) {
		Graphics g = this.getGraphics();
		g.setColor(colour);
		g.drawLine(x, y, x2, y2);
	}
	public void drawString(String s, int x, int y, Color colour) {
		Graphics g = this.getGraphics();
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		g.setColor(colour);
		g.drawString(s, x, y);
	}


}
