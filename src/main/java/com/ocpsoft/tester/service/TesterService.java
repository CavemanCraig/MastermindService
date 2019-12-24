package com.ocpsoft.tester.service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ext.Provider;

import com.ocpsoft.answer.Answer;
import com.ocpsoft.color.MMColor;

@Path("/testerService")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Provider
public class TesterService {

	protected static Answer answer = null;
	
	public TesterService(Answer answer) {
		TesterService.answer = answer;
	}
	
	public TesterService() {
		if(TesterService.answer == null) {
			TesterService.answer = new Answer();
		}
	}
	
	@GET
    @Path("/getAnswer")
	public List<MMColor> getAnswer() {
		return answer.getSequence();
	}
	
	@GET
    @Path("/getScore")
	public Score calculateScore(@QueryParam("colors") List<MMColor> guessSequence) {
		Score score = new Score();
		if(guessSequence.size() != answer.getSequence().size()) {
			System.err.println("ERROR: guess and answer sequence not the same size");
			//Just return a new score
		} else {			
			List<MMColor> answerSequence = answer.getSequence();
			score.setBlackPegCount(getBlackCount(guessSequence, answerSequence));
			score.setWhitePegCount(getWhiteCount(guessSequence, answerSequence));
		}
		return score;
	}
	@GET
    @Path("/getScore2/{color1}/{color2}/{color3}/{color4}")
	public Score calculateScore(@PathParam("color1") MMColor color1, @PathParam("color2") MMColor color2,
			@PathParam("color3") MMColor color3, @PathParam("color4") MMColor color4) {
		Score score = new Score();
		List<MMColor> guessSequence = Arrays.asList(color1, color2, color3, color4);
		if(guessSequence.size() != answer.getSequence().size()) {
			System.err.println("ERROR: guess and answer sequence not the same size");
			//Just return a new score
		} else {			
			List<MMColor> answerSequence = answer.getSequence();
			score.setBlackPegCount(getBlackCount(guessSequence, answerSequence));
			score.setWhitePegCount(getWhiteCount(guessSequence, answerSequence));
		}
		return score;
	}
	
	private int getWhiteCount(List<MMColor> guessSequence, List<MMColor> answerSequence) {
		int whiteCount = 0;
		for(int counter=0; counter<guessSequence.size(); counter++) {
			MMColor curAnswerColor = answerSequence.get(counter);
			MMColor curGuessColor = guessSequence.get(counter);
			if(answerSequence.contains(curGuessColor) && !curAnswerColor.equals(curGuessColor)) {
				whiteCount++;
			}
		}
		return whiteCount;
	}

	private int getBlackCount(List<MMColor> guessSequence, List<MMColor> answerSequence) {
		int blackCount = 0;
		for(int counter=0; counter<guessSequence.size(); counter++) {
			MMColor curAnswerColor = answerSequence.get(counter);
			MMColor curGuessColor = guessSequence.get(counter);
			if(curAnswerColor.equals(curGuessColor)) {
				blackCount++;
			}
		}
		return blackCount;
	}

}
