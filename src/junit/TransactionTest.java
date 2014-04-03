package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.teamjavatar.domain.AbstractTransaction;
import com.example.teamjavatar.domain.Deposit;
import com.example.teamjavatar.domain.Withdrawal;

/**
 * Junit test case for Transactions.
 *
 * commit() tests by Brian Dong
 *
 * @author Team Javatar
 *
 */
public class TransactionTest {
    /** The test deposit. */
    private AbstractTransaction deposit;
    /** The test withdrawal. */
    private AbstractTransaction withdrawal;
    /** The amount for the test transactions. */
    private final double amount = 50;

    /* Checkstyle error since these are javadoc'd. */
    @Before
    /**
     * Setup the test transactions.
     *
     * @throws Exception
     */
    public void setUp() throws Exception {
        deposit = new Deposit(0, null, 0, 0, amount, false);
        withdrawal = new Withdrawal(0, null, 0, 0, amount, false, null);
    }

    @Test
    /**
     * Commit a deposit if it has already been committed.
     */
    public void commitDepositTrue() {
        deposit.commit();
        assertEquals(0, deposit.commit(), 0);
    }

    @Test
    /**
     * Commit an uncommitted deposit.
     */
    public void commitDepositFalse() {
        assertEquals(deposit.getAmount(), deposit.commit(), 0);
    }

    @Test
    /**
     * Commit a withdrawal that has already been committed.
     */
    public void commitWithdrawalTrue() {
        withdrawal.commit();
        assertEquals(0, withdrawal.commit(), 0);
    }

    @Test
    /**
     * Commit an uncommitted withdrawal.
     */
    public void commitWithdrawalFalse() {
        assertEquals(withdrawal.getAmount(), withdrawal.commit(), 0);
    }

}
