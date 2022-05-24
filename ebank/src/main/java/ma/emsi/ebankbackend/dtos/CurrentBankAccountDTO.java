package ma.emsi.ebankbackend.dtos;


import lombok.Data;
import ma.emsi.ebankbackend.enumes.AccountStatus;

import java.util.Date;


@Data
public class CurrentBankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;


}