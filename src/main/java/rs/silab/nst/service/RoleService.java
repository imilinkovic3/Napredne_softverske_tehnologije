package rs.silab.nst.service;

import rs.silab.nst.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    List<Role> findRoles(List<String> roles);

    List<String> findAllStringRoles();
}