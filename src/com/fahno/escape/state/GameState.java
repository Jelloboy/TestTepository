package com.fahno.escape.state;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.fahno.escape.Game;
import com.fahno.escape.Utils;
import com.fahno.escape.display.HUD;
import com.fahno.escape.entity.BasicEnemy;
import com.fahno.escape.entity.Entity;
import com.fahno.escape.entity.ID;
import com.fahno.escape.entity.Player;
import com.fahno.escape.entity.SmartEnemy;
import com.fahno.escape.entity.VerticalEnemy;

public class GameState extends State {

	double randomVal;
	Random rand = new Random();
	int lastLevel;
	Game game;
	Player player;
	BasicEnemy enemy;
	HUD HUD;
	LinkedList<Entity> entityList;

	public GameState(Game game) {
		// Initialize variable
		this.game = game;
		entityList = new LinkedList<Entity>();
		HUD = new HUD();
		player = new Player(game.getGameWidth() / 2 - 32,
				game.getGameHeight() / 2 - 32, 32, 32, game, this, ID.Player);

		// Add entities to the list
		entityList.add(player);
		entityList.add(new BasicEnemy(rand.nextInt(game.getWidth()),
				rand.nextInt(game.getHeight()), 16, 16, game, this,
				ID.Enemy));
	}

	@Override
	public void update() {
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).update();
		}
		HUD.update();
		if (HUD.level > lastLevel) {
			lastLevel = HUD.level;
			randomVal = rand.nextDouble();
			if (randomVal <= 0.40) {
				entityList.add(new BasicEnemy(rand.nextInt(game.getWidth()),
						rand.nextInt(game.getHeight()), 16, 16, game, this,
						ID.Enemy));
			} else if (randomVal <= 0.80) {
				entityList.add(new VerticalEnemy(rand.nextInt(game.getWidth()),
						rand.nextInt(game.getHeight()), 16, 16, game, this,
						ID.Enemy));
			} else {
				entityList.add(new SmartEnemy(rand.nextInt(game.getWidth()),
						rand.nextInt(game.getHeight()), 16, 16, game, this,
						ID.Enemy));
			}
		}

	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).render(g);
		}
		HUD.render(g);
	}

	public void addEntity(Entity ent) {
		entityList.add(ent);
	}

	public void removeEntity(Entity ent) {
		entityList.remove(ent);
	}

	public LinkedList<Entity> getEntityList() {
		return entityList;
	}
}
