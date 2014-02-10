package com.aic.core.service;


import com.aic.core.app.Worker;
import com.aic.entities.Analysis;
import com.aic.entities.Companies;
import com.aic.entities.SentimentResult;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class AnalysisServiceImpl implements AnalysisService {

	private Logger logger;

	private static AnalysisServiceImpl instance;



	private int id = 1;

	private List<Analysis> analyses = new ArrayList<Analysis>();


	private AnalysisServiceImpl() {
		logger = Logger.getLogger(CompanyServiceImpl.class);

	}


	public static AnalysisService getInstance(){
		if(instance == null){
			instance = new AnalysisServiceImpl();
		}
		return instance;
	}

	@Override
	public Analysis startAnalysis(Analysis analysis) {
		flushSentimentResults(analysis.getCompanies());
		analysis.setStarted(new DateTime());
		analysis.setFinished(null);
		analysis.setId(id++);


		logger.info("start analysis");
		updateAnalyses(analysis);

		Worker worker = new Worker(analysis);
		worker.run();
		
		logger.info("end analysis");
		return analysis;
	}

	@Override
	public synchronized void updateAnalyses(Analysis analysis) {
		if(analyses.contains(analysis)){
			analyses.remove(analysis);
		}
		analyses.add(analysis);
		logger.info("updated analyses");
	}

	@Override
	public synchronized List<Analysis> getAnalyses() {
		logger.info("get analyses");
		return analyses;
	}

	public void setAnalyses(List<Analysis> analyses) {
		this.analyses = analyses;		
	}

	private void flushSentimentResults(List<Companies> companies) {
		for(Companies company : companies){
			company.setSentimentResults(new ArrayList<SentimentResult>());
		}
	}


}
