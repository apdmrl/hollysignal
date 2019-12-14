package com.enes.hollysignal.repository;

import com.enes.hollysignal.model.auth.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
