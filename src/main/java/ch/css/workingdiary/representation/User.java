/**
 * This file is part of Working Diary.
 *
 * Working Diary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Working Diary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Working Diary.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.css.workingdiary.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by sandro on 09.03.2015.
 */
public class User {

    private long id;

    @NotBlank
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
        this(0, username, password, firstname, lastname, roleId);
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

    public User(final long id, final String username, final String firstname, final String lastname, final long roleId) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roleId = roleId;
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
