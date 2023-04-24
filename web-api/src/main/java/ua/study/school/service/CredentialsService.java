package ua.study.school.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CredentialsService {
    private InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

    public CredentialsService() {

    }

    public InMemoryUserDetailsManager getUserDetailsManager() {
        // default credentials for testing
        createUser("user", "password");

        return userDetailsManager;
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    public void createUser(String username, String password) {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username(username)
                        .password(password)
                        .roles("USER")
                        .build();
        userDetailsManager.createUser(user);
    }
}
