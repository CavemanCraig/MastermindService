package com.ocpsoft.color;

public class FakeGenerator_RED_25Times implements IRandomColorGenerator {

	private int callCount = 0;
	public MMColor otherColor = MMColor.BLUE;
	
	public MMColor randomColor() {
		callCount++;
		if(callCount <= 25) {
			return MMColor.RED;
		} else {
			return otherColor;
		}
	}

	public int getCallCount() {
		return callCount;
	}
}
