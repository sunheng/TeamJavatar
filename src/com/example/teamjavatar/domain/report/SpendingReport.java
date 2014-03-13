package com.example.teamjavatar.domain.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.teamjavatar.domain.User;
import com.example.teamjavatar.domain.Withdrawal;



public class SpendingReport extends Report {
	
	private long startDate;
	private long endDate;
	private List<String> categories;
	private List<Double> amounts;
	private double total;
	private int categoryMax;
	private int amountMax;
	

	public SpendingReport(User user, long startDate, long endDate, List<Withdrawal> withdrawals) {
		super(user);
		this.startDate = startDate;
		this.endDate = endDate;
		processWithdrawals(withdrawals);
	}

	@Override
	public String toString() {
		if (!report.equals("")) {
			return report;
		}
		String sep = "\n";
		report += "    Spending Report for " + userFullName + sep;
		report += "    " + longToDateString(startDate) + " - "
				+ longToDateString(endDate) + sep;
		Iterator<String> c = categories.iterator();
		Iterator<Double> a = amounts.iterator();
		while( c.hasNext() ) {
			report += String.format("    %-" + categoryMax + "s", c.next())
					+ "    " + floatToString(amountMax, a.next()) + sep;
		}
		report += String.format("    %-" + categoryMax + "s", "Total") + "    "
				+ floatToString(amountMax, total);
		return report;
	}
	
	private void processWithdrawals(List<Withdrawal> withdrawals) {
		Map<String,Double> map = new HashMap<String,Double>();
		total = 0;
		categoryMax = 0;
		amountMax = 0;
		for (Withdrawal w : withdrawals) {
			String k = w.getCategory();
			categoryMax = k.length() > categoryMax ? k.length() : categoryMax;
			double v = w.getAmount();
			total += v;
			String vs = floatToString(v);
			amountMax = vs.length() > amountMax ? vs.length() : amountMax;
			if (map.containsKey(k)) {
				v += map.remove(k);
			}
			map.put(k, v);
		}
		String c = "Total";
		categoryMax = c.length() > categoryMax ? c.length() : categoryMax;
		categoryMax += 4 - (categoryMax % 4);
		String t = floatToString(total);
		amountMax = t.length() > amountMax ? t.length() : amountMax;
		amountMax += 4 - (amountMax % 4);
		categories = new LinkedList<String>();
		amounts = new LinkedList<Double>();
		for (Entry<String,Double> e : map.entrySet()) {
			categories.add(e.getKey());
			amounts.add(e.getValue());
		}
	}
	
}