package com.ocpsoft.tester.service;

public class Score {
	private int whitePegCount = 0;//right color wrong location
	private int blackPegCount = 0;//right color right location
	
	public int getWhitePegCount() {
		return whitePegCount;
	}
	public void setWhitePegCount(int whitePegCount) {
		this.whitePegCount = whitePegCount;
	}
	public int getBlackPegCount() {
		return blackPegCount;
	}
	public void setBlackPegCount(int blackPegCount) {
		this.blackPegCount = blackPegCount;
	}
}
