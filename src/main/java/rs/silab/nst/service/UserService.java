package rs.silab.nst.service;

import rs.silab.nst.model.User;

import java.util.List;


public interface UserService {

    void saveUser(User user);

    User findByUsername(User user);

    User findByEmail(User user);

    User findByEmail(String email);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    User findByEmailOrUsername(String emailOrUsername);
}