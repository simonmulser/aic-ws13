package com.aic.core.service;

import com.aic.entities.Analysis;

import java.util.List;

public interface AnalysisService {

	public Analysis startAnalysis(Analysis analysis);

	public List<Analysis> getAnalyses();

	public void updateAnalyses(Analysis analysis);
}
