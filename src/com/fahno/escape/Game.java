package com.fahno.escape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.fahno.escape.display.Display;
import com.fahno.escape.input.KeyHandler;
import com.fahno.escape.state.GameState;
import com.fahno.escape.state.State;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 4351609082718451368L;

	private Thread gameThread;
	private boolean running = false;

	private Display gameDisplay;
	private int gameWidth, gameHeight;
	private String gameTitle;

	private GameState gameState;

	private KeyHandler keyHandler;

	public Game(int width, int height, String title) {
		this.gameWidth = width;
		this.gameHeight = height;
		this.gameTitle = title;
	}

	public void init() {
		gameDisplay = new Display(gameWidth, gameHeight, gameTitle, this);

		keyHandler = new KeyHandler();
		gameDisplay.getFrame().addKeyListener(keyHandler);

		gameState = new GameState(this);
		if (State.getState() == null) {
			State.setState(gameState);
		}
	}

	public void update() {
		State.getState().update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Clear the screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, gameWidth, gameHeight);
		// Start drawing
		
		State.getState().render(g);

		// Stop drawing

	}

	@Override
	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				repaint();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Fps: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getGameWidth() {
		return gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public KeyHandler getKeyHandler() {
		return keyHandler;
	}
	
	
	//No idea why the subtraction is needed but else it wont work
	public Rectangle getBoundsTop() {
		return new Rectangle(0, -1, gameWidth, 1);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(0, gameHeight - 30, gameWidth, 1);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(gameWidth - 7, 0, 1, gameHeight);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(-1, 0, 1, gameHeight);
	}
}
