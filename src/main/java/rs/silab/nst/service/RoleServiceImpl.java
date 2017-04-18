package rs.silab.nst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.silab.nst.dao.RoleDao;
import rs.silab.nst.model.Role;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAllRoles();
    }

    @Override
    public List<Role> findRoles(List<String> roles) {
        return roleDao.findRoles(roles);
    }

    @Override
    public List<String> findAllStringRoles() {
        return roleDao.findAllStringRoles();
    }
}
