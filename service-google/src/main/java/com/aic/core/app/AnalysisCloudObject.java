package com.aic.core.app;


import com.aic.core.analysis.CombinedClassifier;
import com.aic.core.analysis.TwitterWrapper;
import com.aic.entities.Analysis;
import com.aic.entities.Companies;
import com.aic.entities.SentimentResult;


public class AnalysisCloudObject
{
	public Analysis analyze(Analysis analysis) {
		try{
			CombinedClassifier combinedClassifier = new CombinedClassifier();
			TwitterWrapper twitterWrapper = new TwitterWrapper();

			System.out.println("created classifier and twitterwrapper");
			System.out.println("now analyzing " + analysis.getCompanies().size() + " companies");

			for(Companies companies : analysis.getCompanies()){
				System.out.println("analyze companies " + companies.getName());
				SentimentResult result = twitterWrapper.analyze(companies.getName(), combinedClassifier);
				companies.getSentimentResults().add(result);
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("could not create classifier, reason:" + e.getMessage());
		}
		System.out.println("finished analysis " + analysis);
		return analysis;
	}

}