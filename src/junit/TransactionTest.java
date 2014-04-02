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
    private AbstractTransaction deposit;
    private AbstractTransaction withdrawal;
    private final double AMOUNT = 50;

    @Before
    public void setUp() throws Exception {
        deposit = new Deposit(0, null, 0, 0, AMOUNT, false);
        withdrawal = new Withdrawal(0, null, 0, 0, AMOUNT, false, null);
    }

    @Test
    public void commitDepositTrue() {
        deposit.commit();
        assertEquals(0, deposit.commit(), 0);
    }

    @Test
    public void commitDepositFalse() {
        assertEquals(deposit.getAmount(), deposit.commit(), 0);
    }

    @Test
    public void commitWithdrawalTrue() {
        withdrawal.commit();
        assertEquals(0, withdrawal.commit(), 0);
    }

    @Test
    public void commitWithdrawalFalse() {
        assertEquals(withdrawal.getAmount(), withdrawal.commit(), 0);
    }

}
