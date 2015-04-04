package com.fahno.escape.display;

import javax.swing.JFrame;

import com.fahno.escape.Game;

public class Display {
	
	JFrame frame;
	
	public Display(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
