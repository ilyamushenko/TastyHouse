package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "login", nullable = false)
    private String login;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "role_staff_id")
    private RoleStaff roleStaff;
    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Orders> ordersSet;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(List<Orders> ordersSet) {
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
                getLogin().equals(staff.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastName(), getFirstName(), getPhone(), getEmail(), getPassword(), getLogin());
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", roleStaff=" + roleStaff +
                ", ordersSet=" + ordersSet +
                '}';
    }
}
