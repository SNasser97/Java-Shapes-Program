
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;

	private final int btn_width =100; //constants for button size and spacing properties
	private final int btn_height =30;
	private final int btn_gap = 20;

	public App() { //Constructor for JFrame
		this.setTitle("PhotoJavaShop");
		this.setSize(430 + btn_width , 450); //expands frame so buttons fit on screen (btn_width)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);

		//JPanel p1 = new JPanel(); //creates new JPanel
		//XPanel p1 = new XPanel(); //extends JPanel and inherits properties below
		ZPanel p1 = new ZPanel();
		p1.setBounds(5, 5, 400, 400); //sets position, only if layout is null
		p1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(p1);

		JButton btn1 = new JButton("Clear"); //creates new JButton "Clear"
		btn1.setBounds(410, 1*btn_gap, btn_width , btn_height);
		this.add(btn1);

		JButton btn2 = new JButton("Load"); //button for Load
		btn2.setBounds(410, 2*btn_gap + btn_height, btn_width, btn_height);
		this.add(btn2);

		JButton btn3 = new JButton("Export"); //button for Export
		btn3.setBounds(410, 3*btn_gap + 2*btn_height, btn_width, btn_height);
		this.add(btn3);

		//event handler for btn1
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.clear(); //calls function clear for button event (clicked)
				p1.repaint(); //prevents border being removed after clear
			}
		});

		//event handler for btn2
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// choose a file dialog and sets file path
				JFileChooser fc = new JFileChooser("c:\\");
				 //creates new filter which specifies for textiles
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				fc.setFileFilter(filter);

				//sets directory (c:\\)

				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				//clears area after load
				p1.clear();

				//file is handled with exception
				try {
					BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()));
					String line;
					String[] aa;

					Color colour;
					int x1, x2, x3, x4;
					int counter_lines = 0;
					int counter_useful_lines = 0;
					String text; //var for drawing
					String temp_colour;

					while ((line = br.readLine()) != null) {
						counter_lines++;
						//skip # as comments and values less than 10 chars
						if ((line.length() > 10)  && (line.charAt(0) != '#')) {
							aa = line.split(",");  //splits string with commas

							if (aa.length >= 4) {

								//only accept shapes which are understood
								if ((aa[0].equals("OVAL")) || (aa[0].equals("RECT"))
										|| (aa[0].equals("LINE")) || (aa[0].equals("STRING"))) {
									counter_useful_lines++;
									//try to parse valid shapes
									System.out.println(line);
									//get colour
									//if hex colour
									temp_colour = aa[1];
									if (aa[1].charAt(0) == '#') {
										System.out.println(aa[1]);
										colour = Color.decode(aa[1]); //gets hex colour
									} else { //it is colour name
										try {
											colour = (Color)Color.class.getField(aa[1]).get(null);
										}
										catch(Exception ie) {
											colour = Color.GRAY; //if no colour defaults to grey
										}

									}

									switch(aa[0]) {
									case "OVAL": x1 = Integer.parseInt(aa[2]);
												 x2 = Integer.parseInt(aa[3]);
												 x3 = Integer.parseInt(aa[4]);
												 x4 = Integer.parseInt(aa[5]);
												 p1.drawOval(x1, x2, x3, x4, colour);
										break;
									case "RECT": x1 = Integer.parseInt(aa[2]);
												 x2 = Integer.parseInt(aa[3]);
												 x3 = Integer.parseInt(aa[4]);
												 x4 = Integer.parseInt(aa[5]);
												 p1.drawRect(x1, x2, x3, x4, colour);
										break;

									case "LINE": x1 = Integer.parseInt(aa[2]);
												 x2 = Integer.parseInt(aa[3]);
												 x3 = Integer.parseInt(aa[4]);
												 x4 = Integer.parseInt(aa[5]);
												 p1.drawLine(x1, x2, x3, x4, colour);
										break;
									case "STRING":
												 text = aa[2];
												 x2 = Integer.parseInt(aa[3]);
												 x3 = Integer.parseInt(aa[4]);
												 p1.drawString(text, x2, x3, colour);
									default: System.out.println("Shape does not exist or if statement isn't updated");
									}
								}
							}

						}

					}
					br.close();
					System.out.println("Lines processed" + counter_lines);
					System.out.println("Lines accepted" + counter_useful_lines);
					p1.repaint(); //shows loaded images on panel
				}
				catch (IOException ioe) {
					System.out.println("There was a file related problem" + ioe.getMessage()); //output err message
				}

			  }
			}
		});

		this.setVisible(true);
		//event handler for btn3 (Export)
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage image = new BufferedImage(p1.getWidth(), p1.getHeight(), BufferedImage.TYPE_INT_RGB);
				image  = p1.screenShot();
				String userHomeFolder = System.getProperty("user.home");
				try {
					// Find the user's home directory and export the shapes as an image to Desktop folder
					ImageIO.write(image, "jpg", new File(userHomeFolder+"\\Desktop"+"\\shapes-jpeg.jpg"));
					ImageIO.write(image, "png", new File(userHomeFolder+"\\Desktop"+"\\shapes-png.png"));
				}
				catch(IOException exp) {
					System.out.println(exp);
				}
			}
		});
	}


}
