package com.enes.hollysignal.service;

import com.enes.hollysignal.dto.request.AccountDto;
import com.enes.hollysignal.dto.request.SignupDto;
import com.enes.hollysignal.dto.response.GenericResponse;
import com.enes.hollysignal.model.Status;
import com.enes.hollysignal.model.auth.Account;
import com.enes.hollysignal.model.auth.Profile;
import com.enes.hollysignal.model.auth.Role;
import com.enes.hollysignal.model.system.FileResourceType;
import com.enes.hollysignal.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class AccountService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    /**
     * UserDetailsService (SpringSecurity) methodu.
     * Checks wheter firm is enabled..
     */
    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            logger.error("Account with username not found. [Username: " + username + " ]");
            throw new UsernameNotFoundException(username);
        } else {
            if (account.isEnabled()) {
                logger.info("User logged in. [Username: " + username + " ]");
                account.setRole(roleService.get(account.getRole().getAuthority()));
                return account;
            } else {
                logger.info("User is Disabled. [User: " + account.getProfile().getContact().getFullname() + "]");
                throw new UsernameNotFoundException(username);
            }
        }
    }

    /**
     * Yalnizca profile bilgilerini gunceller.
     *
     * @AuthenticationPrincipal set eder.
     */
    public Account saveProfile(Account account, Profile profile) {

        Account acct = accountRepository.findByUsername(account.getUsername());

        if (acct != null) {
            acct.setProfile(profile);
            acct = accountRepository.save(acct);
            acct.setRole(roleService.get(acct.getRole().getAuthority()));
            setAuthenticationPrincipal(acct);

            logger.info("Account profile is updated. [Account = " + acct.getUsername() + "]");

            return account;
        } else {
            return null;
        }
    }

    public void setPassword(Account account, String password) {
        Account acct = accountRepository.findByUsername(account.getUsername());

        if (acct != null) {
            acct.setPassword(passwordEncoder.encode(password));
            acct.setStatus(Status.ENABLED);
            accountRepository.save(acct);
            logger.info("Account password is set. [Account = " + acct.getUsername() + "]");
        }
    }

    public void setAuthenticationPrincipal(Account account) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(account, account.getPassword(), account.getAuthorities())
        );
    }

    public Account get(String id) {
        return accountRepository.findById(id).orElse(null);
    }

    public boolean exists(String username) {
        return accountRepository.exists(Example.of(new Account()
                .setUsername(username)));
    }


    public GenericResponse saveAccount(SignupDto signupDto) {
        if(!exists(signupDto.getUsername())){
            Account account = new Account()
                    .setUsername(signupDto.getUsername())
                    .setPassword(passwordEncoder.encode(signupDto.getPassword()))
                    .setProfile(signupDto.getProfile())
                    .setStatus(Status.ENABLED)
                    .setRole(Role.user())
                    .setCreated(new Date());

            account.setId(UUID.randomUUID().toString());
            save(account);

            return new GenericResponse()
                    .setCode(0)
                    .setData(account.getProfile());
        }else {
            return new GenericResponse()
                    .setCode(1)
                    .setMessage("Account exists.");
        }
    }

    public Account save(Account acct) {
        return accountRepository.save(acct);
    }

}
