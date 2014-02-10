package com.aic.util;

import com.aic.entities.Companies;
import com.aic.entities.SentimentResult;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompanyProvider {

	private static CompanyProvider instance;

	private Logger logger;

	private List<Companies> companies;

	private CompanyProvider() {
		logger = Logger.getLogger(CompanyProvider.class);
		initialize();
	}

	public static CompanyProvider getInstance(){
		if(instance == null){
			instance = new CompanyProvider();
		}
		return instance;
	}

	public List<Companies> getCompanies(){
		logger.info("get companies");

		return companies;
	}

	public Companies getCompany(String companySlug) {
		logger.info("get company " + companySlug);

		companySlug = companySlug.toLowerCase();

		for(Companies companies : this.companies){
			if(companySlug.equals(companies.getSlug())){
				return companies;
			}
		}
		return null;
	}

	public void add(Companies companies) {
		logger.info("add companies " + companies.getName());
		this.companies.add(companies);
	}


	public void initialize() {
		logger.info("initialize");
		JSONParser parser = new JSONParser();
		companies = new ArrayList<Companies>();

		try {

			JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(CompanyProvider.class.getClassLoader().getResourceAsStream("companies.json")));

			for(Object companyObject : jsonArray){
				JSONObject jsonCompanyObject = (JSONObject) companyObject;
				String slug = (String) jsonCompanyObject.get("slug");
				String name = (String) jsonCompanyObject.get("name");
				String user = (String) jsonCompanyObject.get("user");
				JSONArray sentimentsResultsJSON = (JSONArray) jsonCompanyObject.get("sentimentResults");
				List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
				for(Object sentimentObject : sentimentsResultsJSON){
					JSONObject jsonSentimentObject = (JSONObject) sentimentObject;
					boolean successful = (Boolean) jsonSentimentObject.get("successfull");
					long positive = (Long) jsonSentimentObject.get("positive");
					long negative = (Long) jsonSentimentObject.get("negative");
					long neutral = (Long) jsonSentimentObject.get("neutral");
					DateTime start = new DateTime(((JSONObject)jsonSentimentObject.get("start")).get("millis"));

					DateTime end = null;
					if(successful){
						end = new DateTime(((JSONObject)jsonSentimentObject.get("end")).get("millis"));
					}

					sentimentResults.add(new SentimentResult(successful, (int) positive, (int) negative, (int) neutral, start, end));
				}

				companies.add(new Companies(slug, name, user, sentimentResults));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("initialized with " + companies.size() + " companies");
	}

}
