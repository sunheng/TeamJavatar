package com.example.teamjavatar.domain.report;

import android.content.Context;

import com.example.teamjavatar.domain.User;
import com.example.teamjavatar.domain.database.AccountDAO;
import com.example.teamjavatar.domain.database.TransactionDAO;

/**
 * Report factory creates the specific report based off the given report.
 * 
 * @author TeamJavatar
 * 
 */
public class ReportFactory {
	private User user;
	private AccountDAO accountDataSource;
	private TransactionDAO transactionDataSource;
	
	public ReportFactory(Context context, User user) {
		this.user = user;
		this.accountDataSource = new AccountDAO(context);
		this.transactionDataSource = new TransactionDAO(context);
	}
	
	public AbstractReport makeReport(String reportType, long startDate, long endDate) {
		if (reportType.equals(AbstractReport.SPENDING_REPORT)) {
			
		} else if (reportType.equals(AbstractReport.INCOME_REPORT)) {
			
		} else if (reportType.equals(AbstractReport.CASH_FLOW_REPORT)) {
			
		} else if (reportType.equals(AbstractReport.ACCOUNT_LISTING_REPORT)) {
			
		} else if (reportType.equals(AbstractReport.TRANSACTION_HISTORY_REPORT)) {
			
		}
		//TODO
		return null;
	}
	
	private AbstractReport makeSpendingReport() {
		//TODO
		return null;
		
	}
	
	private AbstractReport makeIncomeReport() {
		//TODO
		return null;
		
	}
	
	private AbstractReport makeCashFlowReport() {
		//TODO
		return null;
		
	}
	
	private AbstractReport makeAccountListingReport() {
		//TODO
		return null;
	}
	
	private AbstractReport makeTransactionHistoryReport() {
		//TODO 
		return null;
	}
}