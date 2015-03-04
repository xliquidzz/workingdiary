package ch.css.workingdiary.dao;

import ch.css.workingdiary.representation.Entry;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

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
}
