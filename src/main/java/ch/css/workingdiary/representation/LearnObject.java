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

import java.util.List;

/**
 * Created by sandro on 15.06.2015.
 */
public class LearnObject {

    private final long id;
    private final String designation;
    private final String description;
    private List<CompetenceArea> competenceAreas;

    public LearnObject(final long id, final String designation, final String description) {
        this.id = id;
        this.designation = designation;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setCompetenceAreas(final List<CompetenceArea> competenceAreas) {
        this.competenceAreas = competenceAreas;
    }
}
