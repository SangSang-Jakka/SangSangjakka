package com.jakka.model.dao;

public interface ReportCnt {
	
	public int addReportCnt(String seq, String userSeq);
	public boolean isReport(String seq, String userSeq);
	
}
