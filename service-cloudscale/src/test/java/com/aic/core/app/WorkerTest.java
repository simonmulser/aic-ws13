package com.aic.core.app;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;

import com.aic.core.service.AnalysisService;
import com.aic.core.service.CompanyService;
import com.aic.entities.Analysis;
import com.aic.util.Util;
import com.espertech.esper.epl.generated.EsperEPL2GrammarParser.newAssign_return;

public class WorkerTest {
	
	private Worker worker;
	private Analysis analysis = Util.getAnalysis();
	private Analysis result = new Analysis();
	private AnalysisCloudObject analysisCloudObject = mock(AnalysisCloudObject.class);
	private AnalysisService analysisService = mock(AnalysisService.class);
	
	@Before
	public void setUp(){
		worker = new Worker(analysis);
		worker.setAnalysisCloudObject(analysisCloudObject);
		worker.setAnalsisService(analysisService);
	}
	
	@Test
	public void testRun(){
		when(analysisCloudObject.analyze(analysis)).thenReturn(result);
		
		worker.run();
		
		assertThat(result.getFinished(), is(notNullValue()));
		
		verify(analysisCloudObject).analyze(analysis);
		verify(analysisService).updateAnalyses(result);
	}

}
