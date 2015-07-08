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
import ch.css.workingdiary.dao.LearnObjectDao;
import ch.css.workingdiary.representation.CompetenceArea;
import ch.css.workingdiary.representation.LearnObject;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.resource.CompetenceResource;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;

import java.util.List;

/**
 * Created by sandro on 15.06.2015.
 */
public class LearnObjectService implements Service {

    private final LearnObjectDao learnObjectDao;
    private final CompetenceAreaService competenceAreaService;
    private final CompetenceService competenceService;
    private final RatingService ratingService;

    public LearnObjectService(final LearnObjectDao learnObjectDao) {
        this.learnObjectDao = learnObjectDao;
        this.competenceService = WorkingDiaryApp.getService(CompetenceService.class);
        this.competenceAreaService = WorkingDiaryApp.getService(CompetenceAreaService.class);
        this.ratingService = WorkingDiaryApp.getService(RatingService.class);
    }

    public Optional<List<LearnObject>> getLearnObjectsAndCompetences(final long userId) {
        final List<LearnObject> learnObjects = learnObjectDao.getLearnObjects(userId);
        for (LearnObject learnObject : learnObjects) {
            final List<CompetenceArea> competenceAreas = getCompetenceAreasAndCompetences(learnObject.getId());
            learnObject.setCompetenceAreas(competenceAreas);
        }
        return Optional.fromNullable(learnObjects);
    }

    public List<CompetenceArea> getCompetenceAreasAndCompetences(final long id) {
        final Optional<List<CompetenceArea>> optionalCompetenceAreas = competenceAreaService.getCompetenceAreas(id);
        if (optionalCompetenceAreas.isPresent()) {
            return optionalCompetenceAreas.get();
        }
        return null;
    }
}
