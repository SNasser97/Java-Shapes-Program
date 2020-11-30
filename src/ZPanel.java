import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ZPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private BufferedImage img; //var for storing shapes

	private void validateImg() {

		if(img == null) {
			img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB); //refers to panel h and w
			Graphics g = img.getGraphics();
			g.setColor(getBackground()); //sets color of bg
			g.fillRect(0, 0, getWidth(), getHeight()); //fills panel to the same h and w of panel
		}

		if ((img.getWidth() != getWidth()) || (img.getHeight() != getHeight())) {
			BufferedImage new_img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = new_img.getGraphics();
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(img, 0, 0, null);
			g.dispose();
			img = new_img;
		}

	}

	@Override //if function is misspelled will provide err message due to override
	public void paintComponent(Graphics g) { //provides image on the panel
		super.paintComponent(g);
		validateImg();
		g.drawImage(img, 0, 0, null);
	}

	public void clear() { //clears after export
		img = null;
		validateImg();
	}

	//gets image
	public BufferedImage screenShot() {
		return img;
	}

	//storing drawings to img
	public void drawOval(int x, int y, int radius1, int radius2, Color colour) { //arguments for oval
		validateImg(); //checks if img is created
		Graphics g = img.getGraphics();
		g.setColor(colour);
		g.fillOval(x, y, radius1, radius2);
	}

	public void drawRect(int x, int y, int width, int height, Color colour) {
		validateImg();
		Graphics g = img.getGraphics();
		g.setColor(colour);
		g.fillRect(x, y, width, height);
	}
	public void drawLine(int x, int y, int x2, int y2, Color colour) {
		validateImg();
		Graphics g = img.getGraphics();
		g.setColor(colour);
		g.drawLine(x, y, x2, y2);
	}
	public void drawString(String s, int x, int y, Color colour) {
		validateImg();
		Graphics g = img.getGraphics();
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		g.setColor(colour);
		g.drawString(s, x, y);
	}
}
