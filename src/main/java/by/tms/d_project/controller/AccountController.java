package by.tms.d_project.controller;

import by.tms.d_project.entity.Account;
import by.tms.d_project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    public AccountController(AccountService accountService) { this.accountService = accountService; }

    @Operation(summary = "creating an account")
    @PostMapping()
    public ResponseEntity<Account> create(@RequestBody Account account) {
        var saved = accountService.create(account);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "deleting an account with a username")
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable("username") String username/*, Authentication authentication*/) {
//        String username = authentication.getName();
//        Account account = accountService.checkAccount(username);
        accountService.delete(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}