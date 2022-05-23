package ma.emsi.ebankbackend.services;

import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;

import java.util.List;

public class BankAccountServiceImpl implements BankAccountService {
    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public BankAccount saveBankAccount(double initialBalance, String type, Long customerId) {
        return null;
    }

    @Override
    public List<Customer> listCustemers() {
        return null;
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return null;
    }

    @Override
    public void debit(String accountID, double amount, String description) {

    }

    @Override
    public void credit(String accountID, double amount, String description) {

    }

    @Override
    public void transfer(String accountIDSource, String accountIdDestination, double amount) {

    }
}
