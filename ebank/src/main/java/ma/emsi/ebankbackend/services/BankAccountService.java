package ma.emsi.ebankbackend.services;


import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveBankAccount(double initialBalance,String type,Long customerId);
    List<Customer> listCustemers();
    BankAccount getBankAccount(String accountId);
    void debit(String accountID,double amount,String description);
    void credit(String accountID,double amount,String description);
    void transfer(String accountIDSource,String accountIdDestination,double amount);
}
