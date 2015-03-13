package ch.css.workingdiary.dao;

import ch.css.workingdiary.dao.mapper.EntryMapper;
import ch.css.workingdiary.representation.Entry;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * Created by sandro on 04.03.2015.
 */
@RegisterMapper(EntryMapper.class)
public interface EntryDao {
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO entry (id, title, message, created, draft, fk_userId) VALUES (NULL, :title, :message, NOW(), :draft, :userId)")
    long create(@Bind("title") final String title,
                @Bind("message") final String message,
                @Bind("draft") final boolean draft,
                @Bind("userId") final long userId);

    @SqlQuery("SELECT * FROM entry")
    List<Entry> getEntries();

    @SqlQuery("SELECT * FROM entry WHERE fk_userId = :userId")
    List<Entry> getEntriesByUserId(@Bind("userId") final long userId);


    @SqlQuery("SELECT * FROM entry WHERE id = :entryId")
    Entry getEntryById(@Bind("entryId") final long entryId);

    @SqlUpdate("DELETE FROM entry WHERE id = :entryId")
    void deleteById(@Bind("entryId") final long entryId);

    @SqlUpdate("UPDATE entry SET title = :title, message = :message WHERE id = :entryId")
    void updateById(@Bind("entryId") final long entryId, @Bind("title") final String title, @Bind("message") final String message);
}
