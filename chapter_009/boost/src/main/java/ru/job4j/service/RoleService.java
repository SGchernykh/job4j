package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Role;
import ru.job4j.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(final int id) {
        return this.roleRepository.findById(id).get();
    }
}
