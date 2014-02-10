package com.aic.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.joda.time.DateTime;

import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.entities.Instance;
import com.aic.entities.InstancePool;
import com.aic.entities.SentimentResult;

public class Util {

	private static final DateTime DEFAULT = new DateTime();
	
	public static Analysis getAnalysis() {
		List<Company> companies = new ArrayList<Company>();
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,new DateTime(), DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		List<SentimentResult> sentimentResults2 = new ArrayList<SentimentResult>();
		sentimentResults2.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults2.add(new SentimentResult(false,5,0,0,DEFAULT, null));

		companies.add(new Company("ibm","IBM", "admin",sentimentResults));
		companies.add(new Company("google","Google", "admin",null));
		companies.add(new Company("motorola","Motorola", "admin",sentimentResults2));		

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

	public static List<Company> getCompanies() {
		List<Company> companies = new ArrayList<Company>();
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		List<SentimentResult> sentimentResults2 = new ArrayList<SentimentResult>();
		sentimentResults2.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults2.add(new SentimentResult(false,5,0,0,DEFAULT, null));

		companies.add(new Company("ibm","IBM", "admin",sentimentResults));
		companies.add(new Company("google","Google", "admin",null));
		companies.add(new Company("motorola","Motorola", "admin",sentimentResults2));		

		return companies;
	}

	public static Company getCompany() {
		List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>();
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));
		sentimentResults.add(new SentimentResult(true,5,4,2,DEFAULT, DEFAULT));

		return new Company("ibm","IBM", "admin",sentimentResults);
	}

	public static InstancePool getInstancePool(){
		Instance instance1 = new Instance();
		instance1.setCloudObjectsCount(1);
		instance1.setCurrentCPULoad(50);
		instance1.setFreeMemory(1534);
		instance1.setUsedMemory(514);
		instance1.setMaxMemory(2048);
		instance1.setIpAddress("168.10.10.5");
		instance1.setLastRequestTime(DEFAULT.minusHours(1));
		instance1.setStartupTime(DEFAULT.minusDays(1));
		
		Instance instance2 = new Instance();
		instance2.setCloudObjectsCount(3);
		instance2.setCurrentCPULoad(50);
		instance2.setFreeMemory(510);
		instance2.setUsedMemory(514);
		instance2.setMaxMemory(1024);
		instance2.setIpAddress("168.10.10.1");
		instance2.setLastRequestTime(DEFAULT.minusHours(1));
		instance2.setStartupTime(DEFAULT.minusDays(1));
		
		List<Instance> instances = new ArrayList<Instance>();
		instances.add(instance1);
		instances.add(instance2);
		InstancePool instancePool = new InstancePool();
		instancePool.setInstances(instances);

		return instancePool;
	}
}
