package rs.silab.nst.dao;

import rs.silab.nst.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAllRoles();

    List<Role> findRoles(List<String> roles);

    List<String> findAllStringRoles();
}