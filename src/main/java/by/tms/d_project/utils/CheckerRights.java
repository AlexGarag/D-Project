package by.tms.d_project.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Класс описывает процедуру проверки прав на форирование, редактирование и удаление
 * Разового решения
 */
@Component
public class CheckerRights {

    public boolean checkRights(String author, Authentication authentication) {
        String actorUsername = authentication.getName();
        boolean isAdmin = Arrays.stream(Arrays.toString(authentication.getAuthorities()
                                .toArray(GrantedAuthority[]::new))
                        .split(","))
                .anyMatch(role -> role.contains("ROLE_ADMIN"));
        return actorUsername.equals(author) || isAdmin;
    }
}