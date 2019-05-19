package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// ToDo - зашифровать данные с помощью Jasypt (http://findnerd.com/list/view/Hibernate-Encryption-using-Jasypt/16928/)

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;
    @Column(name = "last_name", nullable = false, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "lastName", read = "pgp_sym_decrypt(lastName, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String lastName; // зашифровать
    @Column(name = "first_name", nullable = false, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "firstName", read = "pgp_sym_decrypt(firstName, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String firstName; // зашифровать
    @Column(name = "phone", nullable = false, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "phone", read = "pgp_sym_decrypt(phone, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String phone; // зашифровать
    @Column(name = "email", nullable = false, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "email", read = "pgp_sym_decrypt(email, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String email; // зашифровать
    @Column(name = "password", nullable = false, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "password", read = "pgp_sym_decrypt(password, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String password; // зашифровать
    @Column(name = "login", nullable = false, unique = true, columnDefinition = "bytea")
    @ColumnTransformer(forColumn = "login", read = "pgp_sym_decrypt(login, 'secretKey')", write = "pgp_sym_encrypt(?, 'secretKey')")
    private String login; // зашифровать
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "staff_login")
    private RoleStaff roleStaff; // зашифровать???
    @ManyToMany(mappedBy = "staffList")
    @JsonBackReference
    private List<Dish> dishesList;

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

    public List<Dish> getDishesList() {
        return dishesList;
    }

    public void setDishesList(List<Dish> dishesList) {
        this.dishesList = dishesList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id.equals(staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                '}';
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
