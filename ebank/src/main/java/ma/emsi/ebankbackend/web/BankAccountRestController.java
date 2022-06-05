package ma.emsi.ebankbackend.web;


import lombok.AllArgsConstructor;
import ma.emsi.ebankbackend.dtos.*;
import ma.emsi.ebankbackend.exceptions.BalanceNotSufficentExeption;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;


    @GetMapping("/accounts/{accountsId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountsId) throws BankAccountNotfoundExeption {
        return bankAccountService.getBankAccount(accountsId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/Pageoperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotfoundExeption {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption {
        this.bankAccountService.debit(debitDTO.getAccountID(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption {
        this.bankAccountService.credit(creditDTO.getAccountID(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BalanceNotSufficentExeption, BankAccountNotfoundExeption {
        this.bankAccountService.transfer(transferRequestDTO.getAccountSource(),transferRequestDTO.getAccountDestination(),transferRequestDTO.getAmount());
    }







}
