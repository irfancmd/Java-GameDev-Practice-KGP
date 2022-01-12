import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Practice03 extends JFrame {

	private int x = 50, y = 50;
	private int vx = 10, vy = 10;
	
	private Image dbImage; // Double buffer Image
	private Graphics dbGraphics; // Double buffer graphics
	
	private Image owlImage;

	public Practice03() {
		setTitle("Practice 03");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		owlImage = new ImageIcon("owl.png").getImage();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					x -= vx;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					x += vx;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					y -= vy;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					y += vy;
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		// Save an image of the actual screen in the image object
		dbImage = createImage(getWidth(), getHeight());
		// Get the graphics of the saved image
		dbGraphics = dbImage.getGraphics();
		// Paint necessary things
		paintComponent(dbGraphics);
		// Finally, draw the image to the screen. We don't need an ImageObserver
		g.drawImage(dbImage, 0, 0, null);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(owlImage, x, y, 70, 70, this);
		repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Practice03();
			}
		});
	}

}
