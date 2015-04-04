package com.fahno.escape.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.fahno.escape.Game;
import com.fahno.escape.Utils;
import com.fahno.escape.state.GameState;

public class Player extends Entity {

	private int greenValue = 255, redValue = 110;
	private int health = 100;

	public Player(int x, int y, int width, int height, Game game,
			GameState gameState, ID ID) {
		super(x, y, width, height, game, gameState, ID);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(70, greenValue, 0));
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update() {
		if (game.getKeyHandler().getKey(KeyEvent.VK_W)
				|| game.getKeyHandler().getKey(KeyEvent.VK_UP)) {
			velY -= 4;
		}

		if (game.getKeyHandler().getKey(KeyEvent.VK_S)
				|| game.getKeyHandler().getKey(KeyEvent.VK_DOWN)) {
			velY += 4;
		}

		if (game.getKeyHandler().getKey(KeyEvent.VK_A)
				|| game.getKeyHandler().getKey(KeyEvent.VK_LEFT)) {
			velX -= 4;
		}

		if (game.getKeyHandler().getKey(KeyEvent.VK_D)
				|| game.getKeyHandler().getKey(KeyEvent.VK_RIGHT)) {
			velX += 4;
		}

		x += velX;
		y += velY;
		velX = 0;
		velY = 0;

		x = Utils.clamp(x, 0, game.getWidth() - width);
		y = Utils.clamp(y, 0, game.getHeight() - height);

		// Die if health = 0
		if (health <= 0) {
			game.stop();
			System.exit(0);
		}

		// Collision
		for (int i = 0; i < gameState.getEntityList().size(); i++) {
			if (gameState.getEntityList().get(i).getID() == ID.Enemy) {
				if (getBounds().intersects(
						gameState.getEntityList().get(i).getBounds())) {
					health--;
					System.out.println("Health: " + health);
					if (!(redValue + 5 > 255)) {
						redValue += 5;
					} else {
						greenValue -= 3;
						Utils.clamp(greenValue, 0, 254);
					}

				}
			}
		}
		gameState.addEntity(new Trail(x, y, width, height, game, 15, new Color(
				redValue, greenValue, 0), gameState, ID.Trail));
	}
}
