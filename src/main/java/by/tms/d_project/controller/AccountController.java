package by.tms.d_project.controller;

import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService) { this.accountService = accountService; }

    @Operation(summary = "creating an account")
    @PostMapping()
    public ResponseEntity<AccountDto> create(@RequestBody Account account) {
        log.info("Creating an account \'{}\'", account.getUsername());
        accountService.create(account);
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(account.getUsername());
        accountDto.setCreatedAt(account.getCreatedAt());
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

//    @Operation(summary = "deleting an account with a username")
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{username}")
//    public ResponseEntity<?> deleteByUsername(@PathVariable("username") String username, Authentication authentication) {
//        String username = authentication.getName();
//        Account account = accountService.checkAccount(username);
//        log.info("Deleting an account \'{}\'", username);
//        accountService.delete(username);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}