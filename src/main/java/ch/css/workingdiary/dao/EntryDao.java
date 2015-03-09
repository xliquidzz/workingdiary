package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.EntryMapper;
import ch.css.workingdiary.representation.Entry;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by sandro on 04.03.2015.
 */
public interface EntryDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO entry (id, title, message, created, draft, fk_userId) VALUES (NULL, :title, :message, NOW(), :draft, :userId)")
    long create(@Bind("title") final String title,
                @Bind("message") final String message,
                @Bind("draft") final boolean draft,
                @Bind("userId") final long userId);

    @SqlQuery("SELECT * FROM entry")
    @Mapper(EntryMapper.class)
    List<Entry> getEntries();
}
