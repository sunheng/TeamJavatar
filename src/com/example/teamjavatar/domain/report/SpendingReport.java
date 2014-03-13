package com.example.teamjavatar.domain.report;

import java.util.List;

import com.example.teamjavatar.domain.Withdrawal;



public class SpendingReport extends Report {
	
	private long startDate;
	private long endDate;
	private List<String> sources;
	private List<Double> amounts;
	private List<Double> total;
	

	public SpendingReport(String userID, long startDate, long endDate, List<Withdrawal> withdrawals) {
		super(userID);
		this.startDate = startDate;
		this.endDate = endDate;
		processWithdrawals(withdrawals);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void processWithdrawals(List<Withdrawal> withdrawals) {
		//TODO process, use hashmap to get sources, amounts, and totals
	}
	
}