package rs.silab.nst.dao;

import rs.silab.nst.model.User;

import java.util.List;


public interface UserDao {

    User findById(int id);

    void save(User user);

    List<User> findAllUsers();

    User findByUsername(User user);

    User findByEmail(User user);

    void deleteUser(Integer id);

    User findByEmailOrUsername(String emailOrUsername);

    User findByEmail(String email);
}