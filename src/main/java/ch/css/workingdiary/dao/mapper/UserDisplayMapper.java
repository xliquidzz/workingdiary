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
