package com.aic.entities;

import com.aic.enumerations.SentimentType;
import com.aic.rest.DateTimeAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SentimentResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2588308065761586545L;

	private boolean successfull;

	private int positive;
	private int negative;
	private int neutral;

	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime start;

	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime end;

	public SentimentResult(){}

	public SentimentResult(boolean successfull, int positive, int negative,
			int neutral, DateTime start, DateTime end) {
		this.successfull = successfull;
		this.positive = positive;
		this.negative = negative;
		this.neutral = neutral;
		this.start = start;
		this.end = end;
	}

	public boolean isSuccessfull() {
		return successfull;
	}

	public void setSuccessfull(boolean successfull) {
		this.successfull = successfull;
	}

	public int getPositive() {
		return positive;
	}

	public void setPositive(int positive) {
		this.positive = positive;
	}

	public int getNegative() {
		return negative;
	}

	public void setNegative(int negative) {
		this.negative = negative;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public DateTime getEnd() {
		return end;
	}

	public void setEnd(DateTime end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "SentimentResult [successfull=" + successfull + ", positive="
				+ positive + ", negative=" + negative + ", neutral=" + neutral
				+ ", start=" + start + ", end=" + end + "]";
	}



	public void addResult(SentimentType classify) {
		switch(classify){
		case NEGATIVE:
			negative ++;
			break;
		case NEUTRAL:
			neutral ++;
			break;
		case POSITIVE:
			positive ++;
			break;
		default:
			break;
		}

	}

	@JsonIgnore
	public double getPositivePercentage(){
		int sum = positive + negative + neutral;
		return ((double) positive) / ((double) (sum));
	}
}
