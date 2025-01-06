package by.tms.d_project.service;

import by.tms.d_project.entity.Creek;
import by.tms.d_project.repository.CreekRepository;
import org.springframework.stereotype.Service;

@Service
public class CreekService {

    private final CreekRepository creekRepository;

    public CreekService(CreekRepository creekRepository) {
        this.creekRepository = creekRepository;
    }

    public Creek create(Creek creek) {
        creekRepository.save(creek);
        return creek;
    }

    // todo show, deleteById

//    public Account checkAccount(String username) {
//        Optional<Account> account = creekRepository.findByUsername(username);
//        if (account.isPresent()) {
//            return account.get();
//        } else {
//            throw new EntityNotFoundException("Account not found");
//        }
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Account> account = accountRepository.findByUsername(username);
//        if (account.isPresent()) {
//            var acc = account.get();
//            return User
//                    .withUsername(acc.getUsername())
//                    .password(acc.getPassword())
//                    .roles("USER")
//                    .build();
//        }
//        throw new UsernameNotFoundException(username);
//    }
}