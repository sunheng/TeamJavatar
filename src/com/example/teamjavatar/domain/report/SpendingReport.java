package com.example.teamjavatar.domain.report;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.teamjavatar.domain.Withdrawal;



public class SpendingReport extends Report {
	
	private long startDate;
	private long endDate;
	private List<String> categories;
	private List<Double> amounts;
	private double total;
	

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
		Map<String,Double> map = new HashMap<String,Double>();
		double total = 0;
		for (Withdrawal w : withdrawals) {
			String k = w.getCategory();
			double v = w.getAmount();
			total += v;
			if (map.containsKey(k)) {
				v += map.remove(k);
			}
			map.put(k, v);
		}
		List<String> categories = new LinkedList<String>();
		List<Double> amounts = new LinkedList<Double>();
		for (Entry<String,Double> e : map.entrySet()) {
			categories.add(e.getKey());
			amounts.add(e.getValue());
		}
		this.categories = categories;
		this.amounts = amounts;
		this.total = total;
		
	}
	
}