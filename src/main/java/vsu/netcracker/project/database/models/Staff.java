package vsu.netcracker.project.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

// ToDo - сделать шифрацию данных в бд
// ToDo - кэширование некоторых данных (блюд например, того что нужно)
// ToDo - подумать над инфой для залогиненного/незалогиненного пользователя
// ToDo - эскиз странички пользователя???

/**
 * @author Кушнеренко Виктор
 */
@Entity
@Table(name = "staff")
@Data
@ToString(exclude = { "roleStaff", "dishesList" })
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"lastName", "firstName", "phone", "email", "password", "login", "roleStaff", "dishesList"})
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
    @ManyToMany(mappedBy = "staffList")
    @JsonBackReference
    private List<Dish> dishesList;

    public Staff(String lastName, String firstName, String phone, String email, String password, String login, RoleStaff roleStaff) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.login = login;
        this.roleStaff = roleStaff;
    }
}
