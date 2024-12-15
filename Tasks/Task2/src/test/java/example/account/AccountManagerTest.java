package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {
    AccountManager accountManager = new AccountManagerImpl();

    public AccountManagerTest() {
    }

    @Test
    //Deposit
    public void givenAccount_whenDepositPositive_thenSuccess() {
        //Arrange
        Customer customer = new Customer();
        //Act
        accountManager.deposit(customer, 10);
        //Assert
        Assertions.assertEquals(10, customer.getBalance());
    }

    @Test
    public void givenAccount_whenDepositNegative_thenFailed() {
        //Arrange
        Customer customer = new Customer();
        //Act And Assert
        try {
            accountManager.deposit(customer, -50);
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertEquals(0, customer.getBalance());
        }
    }


    //Withdraw
    @Test
    public void givenAccount_whenWithdrawPositive_thenSuccess() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        //Act
        String result = accountManager.withdraw(customer, 10);
        //Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(90, customer.getBalance());
    }

    @Test
    public void givenAccount_whenWithdrawNegative_thenFailed() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        //Act and Assert
        try {
            accountManager.withdraw(customer, -10);
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertEquals(100, customer.getBalance());
        }
    }

    @Test
    public void givenAccount_whenWithdrawPositive_thenFailedWithNoEnoughBalance() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        //Act
        String result = accountManager.withdraw(customer, 200);
        //Assert
        Assertions.assertEquals("insufficient account balance", result);
        Assertions.assertEquals(100, customer.getBalance());
    }

    @Test
    public void givenAccountWithCredit_whenWithdraw_thenSuccess() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        //Act
        String result = accountManager.withdraw(customer, 200);
        //Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-100, customer.getBalance());
    }

    @Test
    public void givenAccountWithCredit_whenWithdraw_thenFailedLimitExceeded() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        customer.setCreditAllowed(true);
        //Act
        String result = accountManager.withdraw(customer, 10000);
        //Assert
        Assertions.assertEquals("maximum credit exceeded", result);
        Assertions.assertEquals(100, customer.getBalance());
    }

    @Test
    public void givenAccountWithVIP_whenWithdraw_thenSuccess() {
        //Arrange
        Customer customer = new Customer();
        customer.setBalance(100);
        //No Need to Mark Credit Allowed as by default VIP mean You Can have Credit without any Limit
//        customer.setCreditAllowed(true);
        customer.setVip(true);
        //Act
        String result = accountManager.withdraw(customer, 10000);
        //Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(-9900, customer.getBalance());
    }
}
