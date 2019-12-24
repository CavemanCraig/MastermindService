package com.ocpsoft.color;

import java.util.Random;

public class RandomColorGenerator implements IRandomColorGenerator {

	public MMColor randomColor() {
		int min = 1;
		int max = MMColor.values().length;
		int randomNum = new Random().nextInt(max - min + 1) + min;
		switch (randomNum) {
		case 1:
			return MMColor.BLACK;
		case 2:
			return MMColor.BLUE;
		case 3:
			return MMColor.BROWN;
		case 4:
			return MMColor.GREEN;
		case 5:
			return MMColor.ORANGE;
		case 6:
			return MMColor.RED;
		case 7:
			return MMColor.WHITE;
		case 8:
			return MMColor.YELLOW;
		default:
			System.err.println("ERROR: Invalid Random Number for picker: " + randomNum);
			return null;
		}
	}

}
