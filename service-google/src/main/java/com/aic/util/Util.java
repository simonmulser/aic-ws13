package com.aic.util;

import com.aic.entities.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class Util {

	private static final DateTime DEFAULT = new DateTime();
	
	public static Analysis getAnalysis() {
		List<Companies> companies = new ArrayList<Companies>();
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,new DateTime(), DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		List<SentimentResult> sentimentResults2 = new ArrayList<SentimentResult>();
		sentimentResults2.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults2.add(new SentimentResult(false,5,0,0,DEFAULT, null));

		companies.add(new Companies("ibm","IBM", "admin",sentimentResults));
		companies.add(new Companies("google","Google", "admin",null));
		companies.add(new Companies("motorola","Motorola", "admin",sentimentResults2));

		Analysis analysis = new Analysis();
		analysis.setCompanies(companies);
		analysis.setId(1);
		return analysis;
	}

	public static List<Analysis> getAnalyses() {
		List<Analysis> analysis = new ArrayList<Analysis>();
		analysis.add(getAnalysis());
		analysis.add(getAnalysis());
		analysis.add(getAnalysis());

		return analysis;
	}

	public static List<Companies> getCompanies() {
		List<Companies> companies = new ArrayList<Companies>();
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		List<SentimentResult> sentimentResults2 = new ArrayList<SentimentResult>();
		sentimentResults2.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults2.add(new SentimentResult(false,5,0,0,DEFAULT, null));

		companies.add(new Companies("ibm","IBM", "admin",sentimentResults));
		companies.add(new Companies("google","Google", "admin",null));
		companies.add(new Companies("motorola","Motorola", "admin",sentimentResults2));

		return companies;
	}

	public static Companies getCompany() {
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		return new Companies("ibm","IBM", "admin",sentimentResults);
	}


}
