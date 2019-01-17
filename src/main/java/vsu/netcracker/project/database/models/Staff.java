package vsu.netcracker.project.database.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "lastName")
    private String lastName;
    @Column(nullable = false, name = "firstName")
    private String firstName;
    @Column(nullable = false, name = "phone")
    private String phone;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = false, name = "login")
    private String login;
    @ManyToOne
    private RoleStaff roleStaff;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Set<Orders> ordersSet;

    public Staff() {

    }

    public Staff(String lastName, String firstName, String phone, String email, String password, String login, RoleStaff roleStaff) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.login = login;
        this.roleStaff = roleStaff;
    }

    // ToDo - какая связь между Orders и Staff???

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleStaff getRoleStaff() {
        return roleStaff;
    }

    public void setRoleStaff(RoleStaff roleStaff) {
        this.roleStaff = roleStaff;
    }

    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return getId().equals(staff.getId()) &&
                getLastName().equals(staff.getLastName()) &&
                getFirstName().equals(staff.getFirstName()) &&
                getPhone().equals(staff.getPhone()) &&
                getEmail().equals(staff.getEmail()) &&
                getPassword().equals(staff.getPassword()) &&
                getLogin().equals(staff.getLogin()) &&
                getRoleStaff().equals(staff.getRoleStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastName(), getFirstName(), getPhone(), getEmail(), getPassword(), getLogin(), getRoleStaff());
    }
}
