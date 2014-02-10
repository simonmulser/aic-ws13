package com.aic.main;

import org.apache.log4j.Logger;
import com.aic.core.service.AnalysisService;
import com.aic.core.service.AnalysisServiceImpl;

import com.aic.util.Util;



public class MainCLI {
	
	private static Logger logger = Logger.getLogger(MainCLI.class);

	public static void main(String[] args) throws InterruptedException, Exception{
		

		logger.info("start app");

		AnalysisService analysisService = AnalysisServiceImpl.getInstance();
		
		analysisService.startAnalysis(Util.getAnalysis());
		
		Thread.sleep(100000);
		
		logger.info(analysisService.getAnalyses());

		logger.info("finish app");

	}

}