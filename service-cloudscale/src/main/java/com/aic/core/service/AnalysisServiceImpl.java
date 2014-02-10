package com.aic.core.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import at.ac.tuwien.infosys.cloudscale.annotations.CloudScaleShutdown;
import at.ac.tuwien.infosys.cloudscale.configuration.CloudScaleConfiguration;
import at.ac.tuwien.infosys.cloudscale.vm.CloudScaleClient;
import at.ac.tuwien.infosys.cloudscale.vm.IHostPool;

import com.aic.core.app.CSConfiguration;
import com.aic.core.app.EsperEventsListener;
import com.aic.core.app.Worker;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.entities.SentimentResult;

public class AnalysisServiceImpl implements AnalysisService {

	private Logger logger;

	private static AnalysisServiceImpl instance;
	
	private EsperEventsListener eventsListener;

	private CloudScaleInstance cloudScaleInstance;

	private int id = 1;

	private List<Analysis> analyses = new ArrayList<Analysis>();


	private AnalysisServiceImpl() {
		logger = Logger.getLogger(CompanyServiceImpl.class);
		cloudScaleInstance = new CloudScaleInstance();
		cloudScaleInstance.start();
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
		worker.start();

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

	private void flushSentimentResults(List<Company> companies) {
		for(Company company : companies){
			company.setSentimentResults(new ArrayList<SentimentResult>());
		}
	}

	private class CloudScaleInstance extends Thread{

		@CloudScaleShutdown
		@Override
		public void run() {
			synchronized (this) {
				try {
					CloudScaleConfiguration cfg = CSConfiguration.getConfiguration();
					System.out.println("scaling policy " + cfg.common().scalingPolicy());
					CloudScaleClient.setConfiguration(cfg);
					logger.info("cloudScaleClient configuration set. now sleeping in @CloudScaleShutdown annoted function.");
					wait();
					logger.info("cloud shutdown. end sleep.");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
}
