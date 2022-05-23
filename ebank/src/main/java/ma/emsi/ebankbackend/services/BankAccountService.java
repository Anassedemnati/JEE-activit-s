package ma.emsi.ebankbackend.services;


import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;
import ma.emsi.ebankbackend.exceptions.BalanceNotSufficentExeption;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.exceptions.CustomerNotFoundExeption;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    BankAccount saveCurrentBankAccount(double initialBalance,double overDraft,Long customerId) throws CustomerNotFoundExeption;
    BankAccount saveSavingBankAccount(double initialBalance,double intrestRate,Long customerId) throws CustomerNotFoundExeption;
    List<CustomerDTO> listCustemers();
    BankAccount getBankAccount(String accountId) throws BankAccountNotfoundExeption;
    void debit(String accountID,double amount,String description) throws BankAccountNotfoundExeption, BalanceNotSufficentExeption;
    void credit(String accountID,double amount,String description) throws BankAccountNotfoundExeption, BalanceNotSufficentExeption;
    void transfer(String accountIDSource,String accountIdDestination,double amount) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption;

    List<BankAccount> bankAccountList();
}
