package spittr.domain;

import javax.persistence.*;

/**
 * Created by Lusai on 8/31/16.
 */
@Entity
public class Spitter {

    public Spitter() {
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "username")
    private String username;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isUpdateByEmail() {
        return updateByEmail;
    }

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "updateByEmail")
    private boolean updateByEmail;

    public Spitter(Long id,String username, String password, String fullname, String email, boolean updateByEmail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.updateByEmail = updateByEmail;
    }
}
