package com.fahno.escape.display;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int levelCounter;
	public int level;
	public int score;
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Level: " + Integer.toString(level), 0, 10);
		g.drawString("Score: " + score, 0, 20);
	}
	
	public void update() {
		score++;
		levelCounter++;
		if(levelCounter == 500) {
			level++;
			levelCounter = 0;
		}
	}
}
