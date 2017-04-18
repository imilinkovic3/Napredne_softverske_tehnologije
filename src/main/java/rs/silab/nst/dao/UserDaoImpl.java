package rs.silab.nst.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.User;

import javax.persistence.NoResultException;
import java.util.List;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    @Autowired
    private CompanyDao companyDao;

    public User findById(int id) {
        try {
            User user = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.id LIKE :id")
                    .setParameter("id", id)
                    .getSingleResult();
            return user;
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT u FROM User u ORDER BY u.firstname ASC")
                .getResultList();
        return users;
    }

    public void save(User user) {
        Company company = companyDao.findByPIB(user.getCompanyBean());
        if (company != null) {
            user.setCompanyBean(company);
        }
        companyDao.saveCompany(user.getCompanyBean());

        update(user);
    }

    @Override
    public User findByUsername(User user) {
        try {
            User u = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
                    .setParameter("username", user.getUsername())
                    .getSingleResult();
            return u;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByEmail(User user) {
        try {
            User u = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
                    .setParameter("email", user.getEmail())
                    .getSingleResult();
            return u;
        } catch (NoResultException ex) {
            return null;
        }
    }


    @Override
    public void deleteUser(Integer id) {
        User user = (User) getEntityManager()
                .createQuery("SELECT u FROM User u WHERE u.id LIKE :id")
                .setParameter("id", id)
                .getSingleResult();
        delete(user);
    }

    @Override
    public User findByEmailOrUsername(String emailOrUsername) {
        try {
            User u = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.email = :email OR u.username = :username")
                    .setParameter("email", emailOrUsername)
                    .setParameter("username", emailOrUsername)
                    .getSingleResult();
            return u;
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        try {
            User u = (User) getEntityManager()
                    .createQuery("SELECT u FROM User u WHERE u.email LIKE :email")
                    .setParameter("email", email)
                    .getSingleResult();
            return u;
        } catch (NoResultException ex) {
            return null;
        }
    }

}
