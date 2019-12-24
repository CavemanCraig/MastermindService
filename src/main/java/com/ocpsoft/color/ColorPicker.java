package com.ocpsoft.color;

import java.util.List;

public class ColorPicker implements IColorPicker {

	IRandomColorGenerator generator = new RandomColorGenerator();
	
	@Override
	public MMColor pick(List<MMColor> alreadyPickedColors) {
		MMColor randColor;
		do {
			randColor = generator.randomColor();
		}while(alreadyPickedColors.contains(randColor));
		return randColor;
	}

	protected void setRandomColorGenerator(IRandomColorGenerator generator) {
		this.generator = generator;
	}

	
}
