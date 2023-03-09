package br.com.ecoffee.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoffee.model.role.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
