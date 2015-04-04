package com.fahno.escape.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.fahno.escape.Game;
import com.fahno.escape.state.GameState;
import com.fahno.escape.state.State;

public abstract class Entity {

	protected int x, y, velX, velY, width, height;
	protected Game game;
	protected GameState gameState;
	protected ID ID;

	public abstract void render(Graphics g);

	public abstract void update();

	public Entity(int x, int y, int width, int height, Game game,
			State gameState, ID ID) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.game = game;
		this.gameState = (GameState) gameState;
		this.ID = ID;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public ID getID() {
		return ID;
	}

}
