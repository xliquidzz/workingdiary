package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.UserMapper;
import ch.css.workingdiary.representation.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandro on 09.03.2015.
 */
@RegisterMapper(UserMapper.class)
public interface UserDao {

    @SqlQuery("select * from user where username = :username")
    User getByUsername(@Bind("username") final String username);

    @SqlQuery("select * from user where fk_roleId = :roleId")
    List<User> getUsersWithRole(@Bind("roleId") final long roleId);
}
