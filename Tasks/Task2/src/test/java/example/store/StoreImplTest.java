package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreImplTest {
    AccountManagerWithAlwaysSuccess accountManager = new AccountManagerWithAlwaysSuccess();
    StoreImpl store = new StoreImpl(accountManager);

    public StoreImplTest() {
    }

    @Test
    public void givenProduct_inStock_thenSuccessAndQuantityUpdated() {
        Product product = new Product();
        product.setQuantity(10);
        Customer customer = new Customer();
        store.buy(product, customer);
        Assertions.assertEquals(9, product.getQuantity());
    }

    @Test
    public void givenProduct_outOfStock_thenFailed() {
        Product product = new Product();
        Customer customer = new Customer();
        try {
            store.buy(product, customer);
            Assertions.fail();
        } catch (Exception exception) {
            Assertions.assertEquals(0, customer.getBalance());
        }
    }

    static class AccountManagerWithAlwaysSuccess implements AccountManager {

        @Override
        public void deposit(Customer customer, int amount) {

        }

        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }
}
