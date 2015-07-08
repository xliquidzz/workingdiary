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

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.dao.CompetenceAreaDao;
import ch.css.workingdiary.representation.Competence;
import ch.css.workingdiary.representation.CompetenceArea;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Created by sandro on 15.06.2015.
 */
public class CompetenceAreaService implements Service {

    private final CompetenceAreaDao competenceAreaDao;
    private final CompetenceService competenceService;

    public CompetenceAreaService(final CompetenceAreaDao competenceAreaDao) {
        this.competenceAreaDao = competenceAreaDao;
        this.competenceService = WorkingDiaryApp.getService(CompetenceService.class);
    }

    public Optional<List<CompetenceArea>> getCompetenceAreas(final long id) {
        final List<CompetenceArea> competenceAreas = competenceAreaDao.getCompetenceAreas(id);
        return Optional.fromNullable(competenceAreas);
    }

    public Optional<List<Competence>> getCompetences(final long id) {
        final Optional<List<Competence>> competences = competenceService.getCompetences(id);
        return competences;
    }
}
