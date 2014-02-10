package com.aic.core.analysis;

import java.util.LinkedList;
import java.util.List;

import com.aic.enumerations.SentimentType;

import classifier.ClassifierBuilder;
import classifier.IClassifier;
import classifier.WeightedMajority;
import classifier.WekaClassifier;

public class CombinedClassifier {
	
	private WeightedMajority classifier;
	
	public CombinedClassifier() throws Exception
	{
		  List<IClassifier> classifiers = new LinkedList<IClassifier>();
		  ClassifierBuilder builder = new ClassifierBuilder();
		  //we use only one model, because copying the model is very time-expensive
		  WekaClassifier wekaClassifier = builder.retrieveClassifier("weka.classifiers.bayes.NaiveBayes");
		  classifiers.add (wekaClassifier);
		  classifier = new WeightedMajority(classifiers);
	}
	
	public SentimentType classify(String text){
		try {
			String polarity = classifier.weightedClassify(text).getPolarity();
			
			if(polarity.equals("4"))
				return SentimentType.POSITIVE;
			if(polarity.equals("0"))
				return SentimentType.NEGATIVE;
			return SentimentType.NEUTRAL;
						
		} catch (Exception e) {
			return SentimentType.NEUTRAL;
		}
		
	}

}
