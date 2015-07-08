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

package ch.css.workingdiary.resource;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.LearnObject;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.CompetenceAreaService;
import ch.css.workingdiary.service.CompetenceService;
import ch.css.workingdiary.service.LearnObjectService;
import ch.css.workingdiary.util.Role;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by sandro on 15.06.2015.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/learn-object")
public class LearnObjectResource {

    private final LearnObjectService learnObjectService;

    public LearnObjectResource() {
        this.learnObjectService = WorkingDiaryApp.getService(LearnObjectService.class);
    }

    public Response getLearnObjects(@Auth User user) {
        if (user.getRoleId() == Role.APPRENTICE.getRoleId()) {
            Optional<List<LearnObject>> optionalLearnObjects = learnObjectService.getLearnObjectsAndCompetences(user.getId());
        }
        return null;
    }
}
