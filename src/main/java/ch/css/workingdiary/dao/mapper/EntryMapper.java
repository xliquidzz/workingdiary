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
