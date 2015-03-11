package ch.css.workingdiary.dao.mapper;

import ch.css.workingdiary.representation.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandro on 11.03.2015.
 */
public class UserDisplayMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new User(r.getLong("id"), r.getString("username"), r.getString("firstname"),r.getString("lastname"), r.getLong("fk_roleId"));
    }
}
