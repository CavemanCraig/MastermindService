package com.ocpsoft.answer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ocpsoft.color.MMColor;

public class AnswerTest {
	
	Answer answer;
	@Before
	public void setup() {
		answer = new Answer();
	}

	@Test
	public void creating_new_answer_will_generate_a_new_sequence() throws Exception {
		//set sequence to something invalid (like duplicate colors)
		List<MMColor> invalidSequence = Arrays.asList(MMColor.RED, MMColor.RED, MMColor.RED, MMColor.RED);		
		answer.setSequence(invalidSequence);
		Assert.assertTrue(answer.isAnswerCorrect(invalidSequence));
		
		answer = new Answer();
		Assert.assertFalse(answer.isAnswerCorrect(invalidSequence));
	}
	
	@Test
	public void answer_sequence_consists_of_4_ordered_colors() {
		assertEquals(answer.getSequence().size(), 4);
		assertTrue(answer.getSequence().get(0) instanceof MMColor);
	}

	@Test
	public void each_of_the_4_colors_in_sequence_is_generated_from_a_picker() throws Exception {
		FakeColorPicker picker = new FakeColorPicker();
		answer.setPicker(picker);
		answer.generateNewSequence();
		assertEquals(4, picker.pickCallCount);
	}
	
	@Test
	public void for_each_pick_the_picker_is_passed_in_all_prior_picks() throws Exception {
		FakeColorPicker picker = new FakeColorPicker();
		answer.setPicker(picker);
		answer.generateNewSequence();
		List<List<MMColor>> expectedInputs = new ArrayList<List<MMColor>>();
		expectedInputs.add(new ArrayList<MMColor>());
		expectedInputs.add(Arrays.asList(picker.firstPick));
		expectedInputs.add(Arrays.asList(picker.firstPick, picker.secondPick));
		expectedInputs.add(Arrays.asList(picker.firstPick, picker.secondPick, picker.thirdPick));
		assertEquals(expectedInputs, picker.inputs);
	}

	@Test
	public void new_sequence_is_always_4_unique_colors() throws Exception {
		List<MMColor> allButFirst = Arrays.asList(answer.getSequence().get(1), answer.getSequence().get(2), answer.getSequence().get(3));
		List<MMColor> allButSecond = Arrays.asList(answer.getSequence().get(0), answer.getSequence().get(2), answer.getSequence().get(3));
		List<MMColor> allButThird = Arrays.asList(answer.getSequence().get(0), answer.getSequence().get(1), answer.getSequence().get(3));
		List<MMColor> allButFourth = Arrays.asList(answer.getSequence().get(0), answer.getSequence().get(1), answer.getSequence().get(2));
		Assert.assertFalse(allButFirst.contains(answer.getSequence().get(0)));
		Assert.assertFalse(allButSecond.contains(answer.getSequence().get(1)));
		Assert.assertFalse(allButThird.contains(answer.getSequence().get(2)));
		Assert.assertFalse(allButFourth.contains(answer.getSequence().get(3)));
	}
	
}
