package com.ocpsoft.tester.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ocpsoft.answer.Answer;
import com.ocpsoft.color.MMColor;


public class TesterServiceTest {
	
	Answer answer;
	TesterService service;
	Score score;
	
	@Before
	public void setup() {
		answer = new Answer();
		List<MMColor> answerSequence = Arrays.asList(MMColor.BLACK, MMColor.BLUE, MMColor.BROWN, MMColor.RED);
		answer.setSequence(answerSequence);
		service = new TesterService(answer);
	}

	@Test
	public void creating_a_new_TesterService_will_not_change_the_answer() throws Exception {
		Assert.assertEquals(answer.getSequence(), TesterService.answer.getSequence());
		service = new TesterService();
		Assert.assertEquals(answer.getSequence(), TesterService.answer.getSequence());
	}
	
	@Test
	public void when_no_colors_are_right_score_is_0_black_and_0_white() throws Exception {
		List<MMColor> guessSequence = Arrays.asList(MMColor.WHITE, MMColor.WHITE, MMColor.WHITE, MMColor.WHITE);
		score = service.calculateScore(guessSequence);
		Assert.assertEquals(0, score.getWhitePegCount());
		Assert.assertEquals(0, score.getBlackPegCount());
	}
	
	@Test
	public void perfect_guess_scores_4_black_0_white() throws Exception {
		score = service.calculateScore(answer.getSequence());
		Assert.assertEquals(0, score.getWhitePegCount());
		Assert.assertEquals(4, score.getBlackPegCount());
	}
	@Test
	public void three_right_colors_in_right_order_scores_3_black_0_white() throws Exception {
		List<MMColor> guessSequence = Arrays.asList(MMColor.BLACK, MMColor.BLUE, MMColor.BROWN, MMColor.WHITE);
		score = service.calculateScore(guessSequence);
		Assert.assertEquals(0, score.getWhitePegCount());
		Assert.assertEquals(3, score.getBlackPegCount());
	}
	
	@Test
	public void when_all_colors_are_correct_but_in_wrong_order_will_score_4_white_0_black() {
		List<MMColor> guessSequence = Arrays.asList(MMColor.BLUE, MMColor.BROWN, MMColor.RED, MMColor.BLACK);
		score = service.calculateScore(guessSequence);
		Assert.assertEquals(4, score.getWhitePegCount());
		Assert.assertEquals(0, score.getBlackPegCount());
	}

	@Test
	public void when_only_1_color_is_correct_and_in_wrong_place_will_score_1_white_0_black() {
		List<MMColor> guessSequence = Arrays.asList(MMColor.WHITE, MMColor.RED, MMColor.WHITE, MMColor.WHITE);
		score = service.calculateScore(guessSequence);
		Assert.assertEquals(1, score.getWhitePegCount());
		Assert.assertEquals(0, score.getBlackPegCount());
	}
	
}
