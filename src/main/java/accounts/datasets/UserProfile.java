package accounts.dataSets;

/*
*/

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserProfile implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String pass;

    @Column(name = "email")
    private String email;

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
