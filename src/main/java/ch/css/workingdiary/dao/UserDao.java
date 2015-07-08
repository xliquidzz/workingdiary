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

package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.UserDisplayMapper;
import ch.css.workingdiary.dao.mapper.UserMapper;
import ch.css.workingdiary.representation.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandro on 09.03.2015.
 */
@RegisterMapper(UserMapper.class)
public interface UserDao {

    @SqlQuery("select * from user where username = :username")
    User getByUsernameWithPassword(@Bind("username") final String username);

    @SqlQuery("select * from user where username = :username")
    User getByUsername(@Bind("username") final String username);

    @SqlQuery("select * from user where fk_roleId = :roleId")
    List<User> getUsersWithRole(@Bind("roleId") final long roleId);

    @Mapper(UserDisplayMapper.class)
    @SqlQuery("select id, username, firstname, lastname, fk_roleId from user u, apprentice_trainer at where at.trainerId = :trainerId AND u.id = at.apprenticeId;")
    List<User> getUsersWithTrainerId(@Bind("trainerId") final long trainerId);

    @Mapper(UserDisplayMapper.class)
    @SqlUpdate("delete from user where id = :userId")
    void deleteById(@Bind("userId") final long userId);

    @SqlUpdate("delete from apprentice_trainer where apprenticeId = :userId")
    void deleteApprenticeReferenceToTrainer(@Bind("userId") final long userId);

    @GetGeneratedKeys
    @SqlUpdate("insert into user (id, username, password, firstname, lastname, fk_roleId) values(NULL, :username, :password, :firstname, :lastname, :roleId)")
    long create(@Bind("username") final String username, @Bind("password") final String password, @Bind("firstname") final String firstname,
                @Bind("lastname") final String lastname, @Bind("roleId") final long roleId);

    @SqlUpdate("UPDATE user SET password = :newPassword WHERE id = :userId")
    void changePassword(@Bind("newPassword") final String newPassword, @Bind("userId") final long userId);
}
