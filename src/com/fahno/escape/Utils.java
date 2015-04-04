package com.fahno.escape;

import java.util.Random;

public class Utils {
	
	private static Random rand = new Random();
	
	public static int clamp(int val, int minVal, int maxVal) {
		if(val >= maxVal) return val = maxVal;
		else if(val <= minVal) return val = minVal;
		else return val;
	}
	
	public static int choose(int val1, int val2) {
		switch(rand.nextInt(2)) {
		case 0: {
			return val1;
		}
		
		case 1: {
			return val2;
		}
		
		default: {
			return 0;
		}
		}
	}
}
