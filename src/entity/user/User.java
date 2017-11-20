package entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


/**
 * @author a.shestovsky
 */

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Date registrationDate;
    private Role role;

    public User(String firstName, String lastName, String email, String password,
                String phone, String address, Date registrationDate, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.registrationDate = registrationDate;
        this.role = role;
    }
}