package by.tms.d_project.controller;

import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.model.Response;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.utils.CheckerRights;
import by.tms.d_project.utils.ResponseGenerator;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.d_project.constant_reference_etc.HttpCode.FORBIDDEN_CODE;
import static by.tms.d_project.constant_reference_etc.HttpCode.NOT_FOUND_CODE;
import static by.tms.d_project.constant_reference_etc.Message.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final CheckerRights checkerRights;
    private final ResponseGenerator responseGenerator;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountController(AccountService accountService,
                             CheckerRights checkerRights,
                             ResponseGenerator responseGenerator) {
        this.accountService = accountService;
        this.checkerRights = checkerRights;
        this.responseGenerator = responseGenerator;
    }

    @Operation(summary = "creating an account")
    @PostMapping()
    public ResponseEntity<AccountShortDto> create(@RequestBody Account account) {
        accountService.create(account);
        AccountShortDto accountShortDto = new AccountShortDto();
        accountShortDto.setUsername(account.getUsername());
        return new ResponseEntity<>(accountShortDto, HttpStatus.CREATED);
    }

    @Operation(summary = "getting an account")
    @GetMapping("/{username}")
    public ResponseEntity<?> get(@PathVariable("username") String username) {
        Optional<Account> optionalAccount = accountService.getAccountByUsername(username);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setUsername(account.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(accountDto);
        } else {
            return responseGenerator.replay(NOT_FOUND_CODE);
        }
    }

    @Operation(summary = "Editing account")
    @PatchMapping("/{username}")
    public ResponseEntity<?> edit(@PathVariable("username") String username,
                                  @RequestBody Account newAccount,
                                  Authentication authentication) {
        boolean isRights = checkerRights.checkRights(username, authentication);
        if (isRights) {
            Optional<Account> optionalAccount = accountService.getAccountByUsername(username);
            if (optionalAccount.isPresent()) {
                Account oldAccount = optionalAccount.get();
                AccountShortDto accountShortDto = accountService.update(oldAccount, newAccount);
                return ResponseEntity.status(HttpStatus.OK).body(accountShortDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
            }
        } else {
            log.info("There was an attempt to edit the account \'{}\' by \'{}\'", username, authentication.getName());
            return responseGenerator.replay(FORBIDDEN_CODE);
        }
    }

    @Operation(summary = "deleting an account with a username")
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteByUsername(@PathVariable("username") String username, Authentication authentication) {
        boolean isRights = checkerRights.checkRights(username, authentication);
        if (isRights) {
            accountService.delete(username, authentication.getName());
            return ResponseEntity.status(HttpStatus.OK).body(DELETED_MESSAGE);
        } else {
            log.info("There was an attempt to delete the account \'{}\' by \'{}\'", username, authentication.getName());
            return responseGenerator.replay(FORBIDDEN_CODE);
        }
    }
}