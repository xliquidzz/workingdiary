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

import ch.css.workingdiary.dao.mapper.EntryMapper;
import ch.css.workingdiary.representation.Entry;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
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

    @SqlUpdate("delete from entry where fk_userId = :userId")
    void deleteUserReferenceToEntry(@Bind("userId") final long userId);
}
