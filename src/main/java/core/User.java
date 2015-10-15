package core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by biogerm on 15/10/11.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(
                name = "core.User.findAll",
                query = "SELECT u FROM User u"
        )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login1", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date login1;

    @Column(name = "login2", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date login2;

    @Column(name = "login3", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date login3;

    @Column(name = "login4", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date login4;

    @Column(name = "login5", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date login5;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

//    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLogin1() {
        return login1;
    }

    public void setLogin1(Date login1) {
        this.login1 = login1;
    }

    public Date getLogin2() {
        return login2;
    }

    public void setLogin2(Date login2) {
        this.login2 = login2;
    }

    public Date getLogin3() {
        return login3;
    }

    public void setLogin3(Date login3) {
        this.login3 = login3;
    }

    public Date getLogin4() {
        return login4;
    }

    public void setLogin4(Date login4) {
        this.login4 = login4;
    }

    public Date getLogin5() {
        return login5;
    }

    public void setLogin5(Date login5) {
        this.login5 = login5;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User that = (User) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.login1, that.login1) &&
                Objects.equals(this.login2, that.login2) &&
                Objects.equals(this.login3, that.login3) &&
                Objects.equals(this.login4, that.login4) &&
                Objects.equals(this.login5, that.login5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, login1, login2, login3, login4, login5);
    }
}
