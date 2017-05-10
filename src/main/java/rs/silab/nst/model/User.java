package rs.silab.nst.model;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String email;

    private String firstname;

    private String lastname;

    private String password;

    @Transient
    private String confirmPassword;

    private String username;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company companyBean;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @OrderBy("priority")
    private List<Role> roles;

    @Transient
    private List<String> stringRoles;

    public User() {
        stringRoles = new ArrayList<>();
    }

    public User(int id,String email, String firstname, String lastname, String password, String confirmPassword, String username, Company companyBean, List<Role> roles, List<String> stringRoles) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        this.companyBean = companyBean;
        this.roles = roles;
        this.stringRoles = stringRoles;
    }

    public List<String> getStringRoles() {
        if (roles != null) {
            for (Role role : roles) {
                this.stringRoles.add(role.getName());
            }
        }
        return stringRoles;
    }

    public void setStringRoles(List<String> rolesS) {
        this.stringRoles = rolesS;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Company getCompanyBean() {
        return this.companyBean;
    }

    public void setCompanyBean(Company companyBean) {
        this.companyBean = companyBean;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", companyBean=" + companyBean +
                ", roles=" + roles +
                ", rolesS=" + stringRoles +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (user.getId() == this.getId()) {
                return true;
            }
        }

        return false;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
}