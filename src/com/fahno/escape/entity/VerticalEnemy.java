package com.fahno.escape.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.fahno.escape.Game;
import com.fahno.escape.Utils;
import com.fahno.escape.state.State;

public class VerticalEnemy extends Entity{

	Random rand = new Random();

	public VerticalEnemy(int x, int y, int width, int height, Game game,
			State gameState, ID ID) {
		super(x, y, width, height, game, gameState, ID);

		velX = Utils.choose(-2, 2);
		velY = Utils.choose(-7, 7);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update() {
		// Collision
		if (getBounds().intersects(game.getBoundsTop())
				|| getBounds().intersects(game.getBoundsBottom())) {
			velY *= -1;
		}

		if (getBounds().intersects(game.getBoundsRight())
				|| getBounds().intersects(game.getBoundsLeft())) {
			velX *= -1;
		}

		x += velX;
		y += velY;
		
		gameState.addEntity(new Trail(x, y, width, height, game, 15, Color.CYAN, gameState, ID.Trail));
	}
}
