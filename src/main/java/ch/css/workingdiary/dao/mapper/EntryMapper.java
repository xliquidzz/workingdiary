package ch.css.workingdiary.dao.mapper;

import ch.css.workingdiary.representation.Entry;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandro on 04.03.2015.
 */
public class EntryMapper implements ResultSetMapper<Entry>{

    @Override
    public Entry map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Entry(r.getLong("id"), r.getString("title"), r.getString("message"),
                new DateTime(r.getTimestamp("created")), r.getBoolean("draft"), r.getLong("fk_userId"));
    }
}
