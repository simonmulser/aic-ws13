package com.aic.core.app;

import com.aic.core.service.AnalysisService;
import com.aic.core.service.AnalysisServiceImpl;
import com.aic.entities.Analysis;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

public class Worker{

	private static Logger logger = Logger.getLogger(Worker.class);

	private Analysis analysis;
	private AnalysisService analsisService;
	private AnalysisCloudObject analysisCloudObject;

	public Worker(Analysis analysis) {
		this.analysis = analysis;
		setAnalsisService(AnalysisServiceImpl.getInstance());
		setAnalysisCloudObject(new AnalysisCloudObject());
	}

	public void run(){
		logger.info("started new worker, now starting analysis");

		Analysis result = analysisCloudObject.analyze(analysis);

		result.setFinished(new DateTime());
		logger.info("worker finished result=" + result);

		analsisService.updateAnalyses(result);
	}

	public AnalysisService getAnalsisService() {
		return analsisService;
	}

	public void setAnalsisService(AnalysisService analsisService) {
		this.analsisService = analsisService;
	}

	public AnalysisCloudObject getAnalysisCloudObject() {
		return analysisCloudObject;
	}

	public void setAnalysisCloudObject(AnalysisCloudObject analysisCloudObject) {
		this.analysisCloudObject = analysisCloudObject;
	}

}
