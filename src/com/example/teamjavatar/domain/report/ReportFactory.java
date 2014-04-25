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
    /** The user to generate reports for. */
    private final User user;
    /** The account database. */
    private final AccountDAO accountDataSource;
    /** The transaction database. */
    private final TransactionDAO transactionDataSource;

    /**
     * Construct a report factory for the given context and user.
     *
     * @param context  an activity to link to the report factory
     * @param aUser     the user
     */
    public ReportFactory(final Context context, final User aUser) {
        this.user = aUser;
        this.accountDataSource = new AccountDAO(context);
        this.transactionDataSource = new TransactionDAO(context);
    }

    /**
     * Returns a report based off the given inputs.
     *
     * @param reportType   the type of report to make
     * @param startDate    a start date (may not be used)
     * @param endDate      an end date (may not be used)
     * @param accountID    an account ID (may not be used)
     * @return a report based off the given inputs
     */
    public final AbstractReport makeReport(final String reportType,
            final long startDate, final long endDate, final int accountID) {
        AbstractReport report = null;
        if (reportType.equals(AbstractReport.SPENDING_REPORT)) {
            report = makeSpendingReport(startDate, endDate);
        } else if (reportType.equals(AbstractReport.INCOME_REPORT)) {
            report = makeIncomeReport(startDate, endDate);
        } else if (reportType.equals(AbstractReport.CASH_FLOW_REPORT)) {
            report = makeCashFlowReport(startDate, endDate);
        } else if (reportType.equals(AbstractReport.ACCOUNT_LISTING_REPORT)) {
            report = makeAccountListingReport();
        } else if (reportType.equals(
                AbstractReport.TRANSACTION_HISTORY_REPORT)) {
            report = makeTransactionHistoryReport(startDate, endDate,
                    accountID);
        }
        return report;
    }

    /**
     * Returns a spending report.
     *
     * @param startDate    a start date
     * @param endDate      an end date
     * @return a spending report
     */
    private AbstractReport makeSpendingReport(final long startDate,
            final long endDate) {
        transactionDataSource.open();
        AbstractReport report = new SpendingReport(user.getFullName(),
                startDate, endDate, transactionDataSource.getWithdrawalsList(
                        user.getID(), startDate, endDate));
        transactionDataSource.close();
        return report;
    }

    /**
     * Returns an income report.
     *
     * @param startDate    a start date
     * @param endDate      an end date
     * @return an income report
     */
    private AbstractReport makeIncomeReport(final long startDate,
            final long endDate) {
        transactionDataSource.open();
        AbstractReport report = new IncomeReport(user.getFullName(),
                startDate, endDate, transactionDataSource.getDepositsList(
                        user.getID(), startDate, endDate));
        transactionDataSource.close();
        return report;
    }

    /**
     * Returns a cash flow report.
     *
     * @param startDate    a start date
     * @param endDate      an end date
     * @return a cash flow report
     */
    private AbstractReport makeCashFlowReport(final long startDate,
            final long endDate) {
        transactionDataSource.open();
        AbstractReport report = new CashFlowReport(user.getFullName(),
                startDate, endDate, transactionDataSource.getTransactionsList(
                        user.getID(), startDate, endDate));
        return report;
    }

    /**
     * Returns an account listing report.
     *
     * @return an account listing report
     */
    private AbstractReport makeAccountListingReport() {
        accountDataSource.open();
        AbstractReport report = new AccountListingReport(user.getFullName(),
                accountDataSource.getAccountsList(user.getID()));
        accountDataSource.close();
        return report;
    }

    /**
     * Returns a transaction history report.
     *
     * @param startDate    a start date
     * @param endDate      an end date
     * @param accountID    the account ID to generate a report for
     * @return a transaction history report
     */
    private AbstractReport makeTransactionHistoryReport(final long startDate,
            final long endDate, final int accountID) {
        accountDataSource.open();
        String name = accountDataSource.getAccountName(accountID);
        accountDataSource.close();
        transactionDataSource.open();
        AbstractReport report = new TransactionHistoryReport(name, startDate, endDate, transactionDataSource.getTransactionsList(accountID, startDate, endDate));
        transactionDataSource.close();
        return report;
    }
}
