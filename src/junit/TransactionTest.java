package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.teamjavatar.domain.Deposit;
import com.example.teamjavatar.domain.Transaction;
import com.example.teamjavatar.domain.Withdrawal;

/**
 * 
 * @author Brian Dong
 *
 */
public class TransactionTest {
	private Transaction deposit;
	private Transaction withdrawal;

	@Before
	public void setUp() throws Exception {
		deposit = new Deposit(0, null, 0, 0, 50, false);
		withdrawal = new Withdrawal(0, null, 0, 0, 50, false, null);
	}
	
	@Test
	public void commitDepositTrue() {
		assertEquals(0, deposit.commit(), 0);
	}
	
	@Test
	public void commitDepositFalse() {
		assertEquals(deposit.getAmount(), deposit.commit(), 0);
	}
	
	@Test
	public void commitWithdrawalTrue() {
		assertEquals(0, withdrawal.commit(), 0);
	}
	
	@Test
	public void commitWithdrawalFalse() {
		assertEquals(withdrawal.getAmount(), withdrawal.commit(), 0);
	}
	

}
