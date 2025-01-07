package by.tms.d_project.service;

import by.tms.d_project.entity.Form;
import by.tms.d_project.repository.FormRepository;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public Form create(Form form) {
        formRepository.save(form);
        return form;
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