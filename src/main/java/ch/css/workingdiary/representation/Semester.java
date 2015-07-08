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

package ch.css.workingdiary.representation;

import java.sql.Date;

/**
 * Created by sandro on 15.06.2015.
 */
public class Semester {

    private final long id;
    private final Date start_date;
    private final Date end_date;
    private final boolean planned;
    private final boolean inter_company_course;
    private final boolean school;

    public Semester(final long id, final Date start_date, final Date end_date, final boolean planned,
                    final boolean inter_company_course, final boolean school) {

        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.planned = planned;
        this.inter_company_course = inter_company_course;
        this.school = school;
    }
}
