package by.tms.d_project.service;

import by.tms.d_project.controller.AccountController;
import by.tms.d_project.dao.AccountDao;
import by.tms.d_project.dto.AccountDto;
import by.tms.d_project.dto.AccountShortDto;
import by.tms.d_project.entity.Account;
import by.tms.d_project.mapper.AccountMapper;
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
    private final AccountMapper accountMapper;
    private final AccountDao accountDao;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper,
                          AccountDao accountDao) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.accountDao = accountDao;
    }

    public Optional<AccountShortDto> create(Account account) {
        account.setPassword(new BCryptPasswordEncoder(11).encode(account.getPassword()));
        accountRepository.save(account);
        Optional<Account> accountOptional = accountRepository.findByUsername(account.getUsername());
        Optional<AccountShortDto> accountShortDtoOptional = Optional.empty();
        if (accountOptional.isPresent()) {
            AccountShortDto accountShortDto = accountMapper.toAccountShortDto(accountOptional.get());
            accountShortDtoOptional = Optional.of(accountShortDto);
            log.info("сreating an account \'{}\'", account.getUsername());
        } else {
            log.info("account creation error \'{}\'", account.getUsername());
        }
        return accountShortDtoOptional;
    }

    public Optional<AccountDto> getAccountDtoByUsername(String username) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        Optional<AccountDto> accountDtoOptional = Optional.empty();
        if (accountOptional.isPresent()) {
            AccountDto accountDto = accountMapper.toAccountDto(accountOptional.get());
            accountDtoOptional = Optional.of(accountDto);
        }
        return accountDtoOptional;
    }

    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Optional<AccountShortDto> update(Account oldAccount, Account newAccount) {
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(new BCryptPasswordEncoder(11).encode(newAccount.getPassword()));
        accountRepository.save(oldAccount);
        Optional<Account> accountOptional = accountRepository.findByUsername(newAccount.getUsername());
        Optional<AccountShortDto> accountShortDtoOptional = Optional.empty();
        if (accountOptional.isPresent()) {
            AccountShortDto accountShortDto = accountMapper.toAccountShortDto(accountOptional.get());
            accountShortDtoOptional = Optional.of(accountShortDto);
            log.info("Updating an account \'{}\' to \'{}\'", oldAccount.getUsername(), newAccount.getUsername());
        } else {
            log.info("account editing error  \'{}\' to \'{}\'", oldAccount.getUsername(), newAccount.getUsername());
        }
        return accountShortDtoOptional;
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