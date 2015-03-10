package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.UserMapper;
import ch.css.workingdiary.representation.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * Created by sandro on 09.03.2015.
 */
public interface UserDao {

    @Mapper(UserMapper.class)
    @SqlQuery("select * from user where username = :username")
    User getByUsername(@Bind("username") final String username);
}
