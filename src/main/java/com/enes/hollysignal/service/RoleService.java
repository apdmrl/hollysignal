package com.enes.hollysignal.service;

import com.enes.hollysignal.model.auth.Permission;
import com.enes.hollysignal.model.auth.Role;
import com.enes.hollysignal.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    private Map<String, Role> roles = new HashMap<>();

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        refreshMap(roleRepository.findAll());
    }

    private void refreshMap(List<Role> roleList) {
        roleList.forEach(role -> roles.put(role.getAuthority(), role));
    }

    public void save(Role role) {
        role = roleRepository.save(role);
        roles.put(role.getAuthority(), role);
    }

    public void saveAll(List<Role> roles) {
        roles = roleRepository.saveAll(roles);
        refreshMap(roles);
    }

    public List<Role> list() {
        return roleRepository.findAll();
    }

    public boolean hasPermission(String roleName, Permission permission) {
        Role role = roles.get(roleName);

        return role.getPermissions().contains(permission);
    }

    public List<String> listNames() {
        return new ArrayList<>(Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_PREDICTER"));
    }

    public Role get(String name) {
        return roles.get(name);
    }
}