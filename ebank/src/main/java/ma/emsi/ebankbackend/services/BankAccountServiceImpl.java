package ma.emsi.ebankbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.entities.*;
import ma.emsi.ebankbackend.enumes.OperationType;
import ma.emsi.ebankbackend.exceptions.BalanceNotSufficentExeption;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.exceptions.CustomerNotFoundExeption;
import ma.emsi.ebankbackend.mappers.BankAccountMapperImpl;
import ma.emsi.ebankbackend.repositories.AccountOperationRepository;
import ma.emsi.ebankbackend.repositories.BankAccountRepository;
import ma.emsi.ebankbackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;

    private BankAccountRepository bankAccountRepository;

    private AccountOperationRepository accountOperationRepository;

    private BankAccountMapperImpl bankAccountMapper;


    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("saving new customer");
        Customer savedCustomer = customerRepository.save(customer);

        return savedCustomer;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundExeption {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundExeption("customer not found");


        CurrentAccount currentAccount= new CurrentAccount();

        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankaccount = bankAccountRepository.save(currentAccount);
        return savedBankaccount;
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double intrestRate, Long customerId) throws CustomerNotFoundExeption {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer==null)
            throw new CustomerNotFoundExeption("customer not found");


        SavingAccount savingAccount= new SavingAccount();

        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(intrestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedBankaccount = bankAccountRepository.save(savingAccount);
        return savedBankaccount;


    }

    @Override
    public List<CustomerDTO> listCustemers() {
        List<Customer> customers = customerRepository.findAll();

        /*List<CustomerDTO>customerDTOS=new ArrayList<>();
        for(Customer customer:customers){
            CustomerDTO customerDTO=dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);*/

        List<CustomerDTO> CustomerDTOs = customers.stream()
                .map(cust->bankAccountMapper.fromCustomer(cust))
                .collect(Collectors.toList());
        return CustomerDTOs;
    }

    @Override
    public BankAccount getBankAccount(String accountId) throws BankAccountNotfoundExeption {
        BankAccount bankAccount= bankAccountRepository.findById(accountId).orElseThrow(
                ()->new BankAccountNotfoundExeption("bank Account not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountID, double amount, String description) throws BankAccountNotfoundExeption, BalanceNotSufficentExeption {
        BankAccount bankAccount = getBankAccount(accountID);
        if (bankAccount.getBalance()<amount)
            throw new BalanceNotSufficentExeption("Balance not sufficent");

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountID, double amount, String description) throws BankAccountNotfoundExeption{
        BankAccount bankAccount = getBankAccount(accountID);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIDSource, String accountIdDestination, double amount) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption {
        debit(accountIDSource,amount,"transfer to"+accountIdDestination);
        credit(accountIdDestination,amount,"transfer from"+accountIDSource);

    }
    @Override
    public List<BankAccount> bankAccountList(){
        return bankAccountRepository.findAll();
    }
    @Override
    public CustomerDTO getCustomer(Long custemerId) throws CustomerNotFoundExeption {
        Customer customer = customerRepository.findById(custemerId).orElseThrow(() -> new CustomerNotFoundExeption("Customer Not found !"));
        return bankAccountMapper.fromCustomer(customer);
    }

}
