package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.UserDisplayMapper;
import ch.css.workingdiary.dao.mapper.UserMapper;
import ch.css.workingdiary.representation.User;
import org.skife.jdbi.v2.sqlobject.Bind;
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
}
