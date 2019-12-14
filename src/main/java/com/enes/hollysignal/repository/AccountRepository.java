package com.enes.hollysignal.repository;

import com.enes.hollysignal.model.auth.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    Account findByUsername(String username);
}
