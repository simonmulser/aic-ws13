package com.aic.core.analysis;

import classifier.IClassifier;
import classifier.WeightedMajority;
import classifier.WekaClassifier;

import com.aic.enumerations.SentimentType;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class CombinedClassifier {
	
	private WeightedMajority classifier;
	
	public CombinedClassifier() throws Exception
	{
		  List<IClassifier> classifiers = new LinkedList<IClassifier>();
		  //we use only one model, because copying the model is very time-expensive
		  ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CombinedClassifier.class.getClassLoader().getResource("files/weka.classifiers.bayes.NaiveBayes.model").getPath()));
          WekaClassifier wc = (WekaClassifier)ois.readObject();
          ois.close();
		  classifiers.add (wc);
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
