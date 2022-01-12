import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Practice01 extends JFrame{
	private Canvas canvas;
	
	int x = 50, y = 50;
	int vx = 10, vy = 10;
	
	public Practice01() {
//		canvas = new Canvas();
//		canvas.setSize(640, 480);
//		
//		add(canvas);
		
		setTitle("Practice 01");
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
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
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		repaint();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Practice01();
			}
		});
	}

}
