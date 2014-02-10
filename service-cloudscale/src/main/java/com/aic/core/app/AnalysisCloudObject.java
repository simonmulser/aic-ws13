package com.aic.core.app;


import twitter4j.internal.logging.Logger;

import com.aic.core.analysis.CombinedClassifier;
import com.aic.core.analysis.TwitterWrapper;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.entities.SentimentResult;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudObject;
import at.ac.tuwien.infosys.cloudscale.annotations.DestructCloudObject;
import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency;
import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency.FileAccess;

@CloudObject
@FileDependency(dependencyProvider = FileProvider.class, accessType = FileAccess.ReadOnly)
public class AnalysisCloudObject
{
	@DestructCloudObject
	public Analysis analyze(Analysis analysis) {
		try{
			System.out.println("create classifier and twitterwrapper");

			CombinedClassifier combinedClassifier = new CombinedClassifier();
			TwitterWrapper twitterWrapper = new TwitterWrapper();

			System.out.println("created classifier and twitterwrapper");
			System.out.println("now analyzing " + analysis.getCompanies().size() + " companies");

			for(Company company : analysis.getCompanies()){
				System.out.println("analyze company " + company.getName());
				SentimentResult result = twitterWrapper.analyze(company.getName(), combinedClassifier);
				company.getSentimentResults().add(result);
			}
		} catch(Exception e){
			System.out.println("could not create classifier, reason:" + e.getMessage());
		}
		System.out.println("finished analysis " + analysis);
		return analysis;
	}

}