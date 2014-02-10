package com.aic.core.service;

import java.util.List;

import com.aic.entities.Analysis;

public interface AnalysisService {

	public Analysis startAnalysis(Analysis  analysis);

	public List<Analysis> getAnalyses();

	public void updateAnalyses(Analysis analysis);
}
