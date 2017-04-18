package rs.silab.nst.dao;

import org.springframework.stereotype.Repository;
import rs.silab.nst.model.Role;

import java.util.ArrayList;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
    @Override
    public List<Role> findAllRoles() {
        List<Role> roles = getEntityManager()
                .createQuery("SELECT r FROM Role r ORDER BY r.name ASC")
                .getResultList();
        return roles;
    }

    @Override
    public List<Role> findRoles(List<String> roles) {
        List<Role> allRoles = new ArrayList<>();

        for (String role : roles) {
            Role r = (Role) getEntityManager()
                    .createQuery("SELECT r FROM Role r WHERE r.name LIKE :role")
                    .setParameter("role", role)
                    .getSingleResult();
            allRoles.add(r);
        }

        return allRoles;
    }

    @Override
    public List<String> findAllStringRoles() {
        List<String> roles = getEntityManager()
                .createQuery("SELECT r.name FROM Role r ORDER BY r.name ASC")
                .getResultList();
        return roles;
    }
}