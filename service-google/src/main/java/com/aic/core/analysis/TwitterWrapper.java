package com.aic.core.analysis;

import com.aic.entities.SentimentResult;
import com.aic.enumerations.SentimentType;
import org.joda.time.DateTime;
import twitter4j.*;

import java.util.List;

public class TwitterWrapper {
	

	private TwitterFactory twitterFactory = new TwitterFactory();
	
	private Twitter twitter;


	private List<Status> getStatusForQuery(String queryString) throws Exception{
		twitter = twitterFactory.getInstance();
		Query query = new Query(queryString);

		try {
			//TODO retry
			QueryResult queryResult = twitter.search(query);
			return queryResult.getTweets();
		} catch (TwitterException twitterException) {
			twitterException.printStackTrace();
			throw new Exception("could not query on twitter");
		}
	}

	public SentimentResult analyze(String word, CombinedClassifier combinedClassifier) {
		SentimentResult sentimentResult = new SentimentResult();
		sentimentResult.setStart(new DateTime());

		try {
			System.out.println("get tweets for word " + word);
			List<Status> statusList = getStatusForQuery(word);

			System.out.println("classify each tweet. number of tweets " + statusList.size());
			for(Status twitterStatus : statusList){
				SentimentType type = combinedClassifier.classify(twitterStatus.getText());
				System.out.println(type + " " + twitterStatus.getText());

				sentimentResult.addResult(type);
			}

		} catch (Exception e) {
			System.out.println("error during twitter sentiment analysis " + e.getMessage());
			return sentimentResult;
		}

		sentimentResult.setEnd(new DateTime());
		sentimentResult.setSuccessfull(true);
		return sentimentResult;
	}

	public void test() {
		List<Status> statuse;
		try {
			statuse = getStatusForQuery("test");
			for(Status status : statuse){
				System.out.println(status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
