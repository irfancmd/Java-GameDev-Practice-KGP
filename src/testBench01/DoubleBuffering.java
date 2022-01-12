package testBench01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DoubleBuffering{
	
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame();
		mainWindow.setTitle("Double Buffering");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainWindow.add(new GamePanel());
		mainWindow.pack();
		
		mainWindow.setVisible(true);;
	}
	
	static class GamePanel extends JPanel implements Runnable {
		private static final int WIDTH = 640;
		private static final int HEIGHT = 480;
		
		private Thread animationThread;
		private volatile boolean running = false;
		
		private volatile boolean gameOver = false;
		
		public GamePanel() {
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(WIDTH, HEIGHT));
		}
		
		// Will be called when this JPanel is added to any JFrame
		@Override
		public void addNotify() {
			super.addNotify();
			startGame();
		}
		
		private void startGame() {
			if(animationThread == null || !running) {
				animationThread = new Thread(this);
				animationThread.start();
			}
		}
		
		@Override
		public void run() {
			running = true;
			while(running) {
				// Update game components, change position etc.
				gameUpdate();
				// Render the current frame
				gameRender();
				// Display the current frame
				repaint();
				
				// Give the CPU some time to breath and do other stuff
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.exit(0);
		}
		
		private void stopGame() {
			running = false;
		}
		
		private void gameUpdate() {
			if(!gameOver) {
				// Do something
			}
		}
		
		private Graphics doubleBufferGraphics;
		private Image doubleBufferImage = null;
		
		// This function will draw the current frame in the background image of double buffer
		private void gameRender() {
			if(doubleBufferImage == null) {
				doubleBufferImage = createImage(WIDTH, HEIGHT);
				
				if(doubleBufferImage == null) {
					System.err.println("ERROR: Couldn't create image.");
					return;
				} else {
					doubleBufferGraphics = doubleBufferImage.getGraphics();
				}
			}
			
			// Clear Screen / Draw the background
			doubleBufferGraphics.setColor(Color.WHITE);
			doubleBufferGraphics.fillRect(0, 0, WIDTH, HEIGHT);
			
			// Draw other things of the frame
			doubleBufferGraphics.setColor(Color.GREEN);
			doubleBufferGraphics.drawString(System.currentTimeMillis() + "", 50, 50);
			
			if(gameOver) {
				showGameOverMsg(doubleBufferGraphics);
			}
		}
		
		private void showGameOverMsg(Graphics g) {
			g.drawString("GAME OVER", WIDTH/2, HEIGHT/2);
		}
		
		// This method will be automatically called by the repaint() method
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(doubleBufferImage != null) {
				g.drawImage(doubleBufferImage, 0, 0, null);
			}
		}
		
	}

}
