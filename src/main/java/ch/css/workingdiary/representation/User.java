package ch.css.workingdiary.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sandro on 09.03.2015.
 */
public class User {

    private long id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private long roleId;

    public User(final long id, final String username, final String password, final String firstname, final String lastname, final long roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roleId = roleId;
    }

    @JsonCreator
    public User(@JsonProperty("username") final String username, @JsonProperty("password") final String password,
                @JsonProperty("firstname") final String firstname, @JsonProperty("lastname") final String lastname, @JsonProperty("roleId") final long roleId) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roleId = roleId;
    }

    public User(final String username, final long id, final long roleId) {
        this.username = username;
        this.id = id;
        this.roleId = roleId;
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public long getRoleId() {
        return roleId;
    }
}
