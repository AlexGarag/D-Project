package by.tms.d_project.service;

import by.tms.d_project.controller.AccountController;
import by.tms.d_project.dao.AccountDao;
import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final AccountDao accountDao;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountDao accountDao) {
        this.accountRepository = accountRepository;
        this.accountDao = accountDao;
    }

    public Account create(Account account) {
        account.setPassword(new BCryptPasswordEncoder(11).encode(account.getPassword()));
        accountRepository.save(account);
        log.info("Creating an account \'{}\'", account.getUsername());
        return account;
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public AccountShortDto update(Account oldAccount, Account newAccount) {
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(new BCryptPasswordEncoder(11).encode(newAccount.getPassword()));
        accountRepository.save(oldAccount);
        log.info("Updating an account \'{}\' to \'{}\'", oldAccount.getUsername(), newAccount.getUsername());
        AccountShortDto accountShortDto = new AccountShortDto();
        accountShortDto.setUsername(newAccount.getUsername());
        return accountShortDto;
    }

    public void delete(String username, String actorUsername) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        accountOptional.ifPresent(account -> accountDao.delete(account.getId()));
        log.info("Deleting an account \'{}\' by \'{}\'", username, actorUsername);
    }

    public Account checkAccount(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);
        if (account.isPresent()) {
            var acc = account.get();
            return User
                    .withUsername(acc.getUsername())
                    .password(acc.getPassword())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}