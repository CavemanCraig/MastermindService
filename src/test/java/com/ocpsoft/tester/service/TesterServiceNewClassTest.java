package com.ocpsoft.tester.service;

import org.junit.Assert;
import org.junit.Test;

import com.ocpsoft.answer.Answer;

public class TesterServiceNewClassTest {

	@Test
	public void new_score_starts_with_0_black_and_0_white() {
		Score score = new Score();
		Assert.assertEquals(0, score.getWhitePegCount());
		Assert.assertEquals(0, score.getBlackPegCount());
	}
	
	@Test
	public void creating_a_new_TesterService_will_generate_a_new_answer() throws Exception {
		Answer answer = new Answer();
		new TesterService();
		Assert.assertFalse("new answer should not match the old one. Note by random, this may be flaky if it randoms the hard-coded answer in setup",
				answer.isAnswerCorrect(TesterService.answer.getSequence()));
	}
	
}
