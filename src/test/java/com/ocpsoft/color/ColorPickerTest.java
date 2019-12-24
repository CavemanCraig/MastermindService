package com.ocpsoft.color;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class ColorPickerTest {

	@Test
	public void will_not_pick_a_color_that_was_already_picked() {
		FakeGenerator_RED_25Times redGenerator = new FakeGenerator_RED_25Times();
		ColorPicker picker = new ColorPicker();
		picker.setRandomColorGenerator(redGenerator);
		List<MMColor> alreadyPicked = Arrays.asList(MMColor.RED);
		
		MMColor actualColor = picker.pick(alreadyPicked);
		Assert.assertEquals(26, redGenerator.getCallCount());
		Assert.assertEquals(redGenerator.otherColor, actualColor);
	}

}
