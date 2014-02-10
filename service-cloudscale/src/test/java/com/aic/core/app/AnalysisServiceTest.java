package com.aic.core.app;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.aic.core.service.AnalysisService;
import com.aic.core.service.AnalysisServiceImpl;
import com.aic.core.service.CompanyServiceImpl;
import com.aic.core.service.CompanyService;
import com.aic.entities.Analysis;
import com.aic.entities.Company;
import com.aic.util.Util;

public class AnalysisServiceTest{

	private Analysis analysis1;
	private Analysis analysis2;
	private AnalysisService analysisService;

	@Before
	public void setUp(){

		analysis1 = Util.getAnalysis();
		analysis2 = Util.getAnalysis();
		analysis2.setId(2);
		analysisService = (AnalysisService) AnalysisServiceImpl.getInstance();
		((AnalysisServiceImpl) analysisService).setAnalyses(new ArrayList<Analysis>());
	}
	
	@After
	public void setAfter(){
		((AnalysisServiceImpl) analysisService).setAnalyses(new ArrayList<Analysis>());
	}

	//TODO can we test the annoted startAnalysis class? remove annotation? (not so important)
	@Test
	@Ignore
	public void testStartAnalysis(){
		analysis1 = analysisService.startAnalysis(analysis1);

		assertThat(analysisService.getAnalyses().size(), is(1));
		assertThat(analysisService.getAnalyses().get(0), is(analysis1));


		assertThat(analysis1.getStarted(), is(notNullValue()));
		assertThat(analysis1.getFinished(), is(nullValue()));
		assertThat(analysis1.getId(), is(1));

		assertThat(analysis1.getCompanies().size(), is(3));
		assertThat(analysis1.getCompanies().get(0).getSentimentResults().size(), is(0));
		assertThat(analysis1.getCompanies().get(1).getSentimentResults().size(), is(0));
		assertThat(analysis1.getCompanies().get(2).getSentimentResults().size(), is(0));

	}

	@Test
	public void testUpdateAnalyses(){
		assertThat(analysisService.getAnalyses(), is(notNullValue()));
		assertThat(analysisService.getAnalyses().size(), is(0));

		analysisService.updateAnalyses(analysis1);

		assertThat(analysisService.getAnalyses().size(), is(1));
		assertThat(analysisService.getAnalyses().get(0), is(analysis1));

		DateTime dateTime = new DateTime();
		analysis1.setFinished(dateTime);

		analysisService.updateAnalyses(analysis1);

		assertThat(analysisService.getAnalyses().size(), is(1));
		assertThat(analysisService.getAnalyses().get(0), is(analysis1));
		assertThat(analysisService.getAnalyses().get(0).getFinished(), is(dateTime));

		analysisService.updateAnalyses(analysis2);

		assertThat(analysisService.getAnalyses().size(), is(2));
		assertThat(analysisService.getAnalyses().contains(analysis1), is(true));
		assertThat(analysisService.getAnalyses().contains(analysis2), is(true));
	}
}
