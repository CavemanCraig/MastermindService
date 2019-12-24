package com.ocpsoft.answer;

import java.util.ArrayList;
import java.util.List;

import com.ocpsoft.color.IColorPicker;
import com.ocpsoft.color.MMColor;

public class FakeColorPicker implements IColorPicker {

	public int pickCallCount = 0;
	public List<List<MMColor>> inputs = new ArrayList<>();
	public final MMColor firstPick = MMColor.BLACK;
	public final MMColor secondPick = MMColor.BLUE;
	public final MMColor thirdPick = MMColor.BROWN;
	public final MMColor fourthPick = MMColor.GREEN;
	
	public MMColor pick(List<MMColor> alreadyPickedColors) {
		inputs.add(alreadyPickedColors);
		pickCallCount++;
		switch (pickCallCount) {
		case 1:
			return firstPick;
		case 2:
			return secondPick;
		case 3:
			return thirdPick;
		case 4:
			return fourthPick;
		default:
			return null;
		}
	}

}
