package ma.emsi.ebankbackend.web;


import lombok.AllArgsConstructor;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;


}
