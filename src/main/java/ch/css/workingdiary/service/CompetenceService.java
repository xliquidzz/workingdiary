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

package ch.css.workingdiary.service;

import ch.css.workingdiary.dao.CompetenceDao;
import ch.css.workingdiary.representation.Competence;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Created by sandro on 15.06.2015.
 */
public class CompetenceService implements Service {

    private final CompetenceDao competenceDao;

    public CompetenceService(final CompetenceDao competenceDao) {
        this.competenceDao = competenceDao;
    }

    public Optional<List<Competence>> getCompetences(final long id) {
        final List<Competence> competences = competenceDao.getCompetences(id);
        return Optional.fromNullable(competences);
    }
}
