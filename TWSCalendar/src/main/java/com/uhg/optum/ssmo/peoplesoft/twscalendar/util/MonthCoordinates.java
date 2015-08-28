package com.uhg.optum.ssmo.peoplesoft.twscalendar.util;

public enum MonthCoordinates {
	January(3,1),
	February(12,1),
	March(21,1),
	April(3,9),
	May(12,9),
	June(21,9),
	July(3,17),
	August(12,17),
	September(21,17),
	October(3,25),
	November(12,25),
	December(21,25)
	;
	
	private final int x;
	private final int y;
	
	private MonthCoordinates(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
