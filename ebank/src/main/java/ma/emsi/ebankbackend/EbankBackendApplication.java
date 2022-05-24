package ma.emsi.ebankbackend;

import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.entities.*;
import ma.emsi.ebankbackend.enumes.AccountStatus;
import ma.emsi.ebankbackend.enumes.OperationType;
import ma.emsi.ebankbackend.exceptions.BalanceNotSufficentExeption;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.exceptions.CustomerNotFoundExeption;
import ma.emsi.ebankbackend.repositories.AccountOperationRepository;
import ma.emsi.ebankbackend.repositories.BankAccountRepository;
import ma.emsi.ebankbackend.repositories.CustomerRepository;
import ma.emsi.ebankbackend.services.BankAccountService;
import ma.emsi.ebankbackend.services.BankService;
import org.omg.CORBA.Current;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }
@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args->{
                Stream.of("Hassane","Anasse","Mehdi").forEach(name->{
                    CustomerDTO customer = new CustomerDTO();
                    customer.setName(name);
                    customer.setEmail(name+"@gmail.com");
                    bankAccountService.saveCustomer(customer);
                });
                bankAccountService.listCustemers().forEach(customer -> {
                    try {
                        bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000,customer.getId());
                        bankAccountService.saveSavingBankAccount(Math.random()*12000,5.5,customer.getId());
                        List<BankAccount> bankAccountList= bankAccountService.bankAccountList();
                        for (BankAccount bankAccount:bankAccountList){
                            for (int i = 0; i < 10; i++) {
                                bankAccountService.credit(bankAccount.getId(),10000+Math.random()*120000,"credit");
                                bankAccountService.debit(bankAccount.getId(),1000+Math.random()*9000,"debit");
                            }

                        }
                    } catch (CustomerNotFoundExeption e) {
                        e.printStackTrace();
                    } catch (BalanceNotSufficentExeption | BankAccountNotfoundExeption e) {
                        e.printStackTrace();
                    }


                });
        };
    }

    //@Bean
    CommandLineRunner  start(CustomerRepository customerRepository,
                             BankAccountRepository bankAccountRepository,
                             AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Hassan", "Yassine", "Aicha").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);


            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i < 5; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);

                }


            });
        };
    }
}
