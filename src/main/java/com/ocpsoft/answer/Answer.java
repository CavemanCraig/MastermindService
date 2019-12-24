package com.ocpsoft.answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ocpsoft.color.ColorPicker;
import com.ocpsoft.color.IColorPicker;
import com.ocpsoft.color.MMColor;

public class Answer {
	
	private List<MMColor> sequence;
	private IColorPicker colorPicker = new ColorPicker();

	public Answer() {
		sequence = generateNewSequence();
	}

	protected List<MMColor> generateNewSequence() {
		List<MMColor> newSequence = new ArrayList<MMColor>();
		newSequence.add(colorPicker.pick(new ArrayList<MMColor>()));
		newSequence.add(colorPicker.pick(Arrays.asList(newSequence.get(0))));
		newSequence.add(colorPicker.pick(Arrays.asList(newSequence.get(0), newSequence.get(1))));
		newSequence.add(colorPicker.pick(Arrays.asList(newSequence.get(0), newSequence.get(1), newSequence.get(2))));
		return newSequence;
	}

	public List<MMColor> getSequence() {
		return sequence;
	}

	public void setSequence(List<MMColor> input) {
		sequence = input;
	}

	public boolean isAnswerCorrect(List<MMColor> guess) {
		boolean isCorrect = true;
		if(guess.size() != sequence.size()) {
			isCorrect = false;
		} else {
			for(int pos=0; pos<sequence.size(); pos++) {
				if(guess.get(pos) != sequence.get(pos)) {
					isCorrect = false;
					break;
				}
			}
		}
		return isCorrect;
	}

	public void setPicker(IColorPicker picker) {
		this.colorPicker = picker;
	}
}
