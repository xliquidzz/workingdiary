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

import ch.css.workingdiary.representation.Semester;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sandro on 15.06.2015.
 */
public class SemesterMapper implements ResultSetMapper<Semester> {

    @Override
    public Semester map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Semester(
                r.getLong("id"),
                r.getDate("start_date"),
                r.getDate("end_date"),
                r.getBoolean("planned"),
                r.getBoolean("inter_company_course"),
                r.getBoolean("vocation_school")
        );
    }
}
