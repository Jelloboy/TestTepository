package com.fahno.escape.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.fahno.escape.Game;
import com.fahno.escape.state.State;

public class Trail extends Entity{
	
	private float life;
	private int alpha = 255;
	Color entityColor;
	
	public Trail(int x, int y, int width, int height, Game game, int life, Color color, State gameState, ID ID) {
		super(x, y, width, height, game, gameState, ID);
		this.life = life;
		entityColor = color;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(entityColor.getRed(), entityColor.getGreen(), entityColor.getBlue(), alpha));
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update() {
		alpha -= life;
		if(alpha <= 0) {
			gameState.removeEntity(this);
		}
	}
}
