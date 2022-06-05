package ma.emsi.ebankbackend.services;


import ma.emsi.ebankbackend.dtos.*;
import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;
import ma.emsi.ebankbackend.exceptions.BalanceNotSufficentExeption;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.exceptions.CustomerNotFoundExeption;

import java.util.List;

public interface BankAccountService {


    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double intrestRate, Long customerId) throws CustomerNotFoundExeption;
    List<CustomerDTO> listCustemers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotfoundExeption;
    void debit(String accountID,double amount,String description) throws BankAccountNotfoundExeption, BalanceNotSufficentExeption;
    void credit(String accountID,double amount,String description) throws BankAccountNotfoundExeption, BalanceNotSufficentExeption;
    void transfer(String accountIDSource,String accountIdDestination,double amount) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption;

    List<BankAccountDTO> bankAccountList();

    List<AccountOperationDTO> accountHistory(String accountId);

    CustomerDTO getCustomer(Long custemerId) throws CustomerNotFoundExeption;

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotfoundExeption;

    List<CustomerDTO> serachCustomer(String keyword);

    List<BankAccountDTO> getCustemerAccounts(Long custemrId);
}
