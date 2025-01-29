package by.tms.d_project.controller;

import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.service.AccountService;
import by.tms.d_project.utils.CheckerRights;
import by.tms.d_project.utils.ResponseGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static by.tms.d_project.constant_reference_etc.HttpCode.*;
import static by.tms.d_project.constant_reference_etc.Message.*;

@RestController
@RequestMapping("/account")
@Tag(name = "Accounts resource")
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
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseGenerator.errorReplay(bindingResult);
        }
        Optional<AccountShortDto> accountShortDtoOptional = accountService.create(account);
        if (accountShortDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountShortDtoOptional.get());
        } else {
            return responseGenerator.errorReplay(INTERNAL_SERVER_ERROR_CODE);
        }
    }

    @Operation(summary = "getting an account")
    @GetMapping("/{username}")
    public ResponseEntity<?> get(@PathVariable("username") String username) {
        Optional<AccountDto> accountDtoOptional = accountService.getAccountDtoByUsername(username);
        if (accountDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(accountDtoOptional.get());
        } else {
            return responseGenerator.errorReplay(NOT_FOUND_CODE);
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
                Optional<AccountShortDto> accountShortDtoOptional = accountService.update(oldAccount, newAccount);
                if (accountShortDtoOptional.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(accountShortDtoOptional.get());
                }
                else {
                    return responseGenerator.errorReplay(INTERNAL_SERVER_ERROR_CODE);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
            }
        } else {
            log.info("There was an attempt to edit the account \'{}\' by \'{}\'", username, authentication.getName());
            return responseGenerator.errorReplay(FORBIDDEN_CODE);
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
            return responseGenerator.errorReplay(FORBIDDEN_CODE);
        }
    }
}