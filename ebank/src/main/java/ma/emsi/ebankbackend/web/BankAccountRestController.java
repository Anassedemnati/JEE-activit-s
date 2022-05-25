package ma.emsi.ebankbackend.web;


import lombok.AllArgsConstructor;
import ma.emsi.ebankbackend.dtos.AccountHistoryDTO;
import ma.emsi.ebankbackend.dtos.AccountOperationDTO;
import ma.emsi.ebankbackend.dtos.BankAccountDTO;
import ma.emsi.ebankbackend.exceptions.BankAccountNotfoundExeption;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
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






}
