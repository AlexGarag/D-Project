package by.tms.d_project.service;

import by.tms.d_project.entity.Account;
import by.tms.d_project.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create(Account account) {
        account.setPassword(new BCryptPasswordEncoder(11).encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    public void delete(String username) {
        accountRepository.deleteByUsername(username);
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