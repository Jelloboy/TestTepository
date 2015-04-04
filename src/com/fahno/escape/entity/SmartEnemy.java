package com.fahno.escape.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.fahno.escape.Game;
import com.fahno.escape.state.State;

public class SmartEnemy extends Entity{

	public SmartEnemy(int x, int y, int width, int height, Game game,
			State gameState, ID ID) {
		super(x, y, width, height, game, gameState, ID);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void update() {
		if(gameState.getEntityList().get(0).getX() + gameState.getEntityList().get(0).getWidth() / 2 > x + width / 2) {
			velX++;
		}
		
		if(gameState.getEntityList().get(0).getX() + gameState.getEntityList().get(0).getWidth() / 2 < x + width / 2) {
			velX--;
		}
		
		if(gameState.getEntityList().get(0).getY() + gameState.getEntityList().get(0).getHeight() / 2 < y + height / 2) {
			velY--;
		}
		
		if(gameState.getEntityList().get(0).getY() + gameState.getEntityList().get(0).getHeight() / 2 > y + height / 2){
			velY++;
		}
		
		x += velX;
		y += velY;
		velX = 0;
		velY = 0;
		
		gameState.addEntity(new Trail(x, y, width, height, game, 15, Color.GREEN, gameState, ID.Trail));
	}

}
