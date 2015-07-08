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

package ch.css.workingdiary.security;

import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.service.UserService;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.exceptions.InvalidSignatureException;
import com.github.toastshaman.dropwizard.auth.jwt.exceptions.TokenExpiredException;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.google.common.base.Optional;
import io.dropwizard.auth.Authenticator;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by sandro on 09.03.2015.
 */
public class WorkingDiaryAuthenticator implements Authenticator<JsonWebToken, User> {

    private final UserService userService;

    private final JsonWebTokenValidator jsonWebTokenValidator;

    public WorkingDiaryAuthenticator(final JsonWebTokenValidator jsonWebTokenValidator) {
        this.jsonWebTokenValidator = jsonWebTokenValidator;
        this.userService = WorkingDiaryApp.getService(UserService.class);
    }

    @Override
    public Optional<User> authenticate(final JsonWebToken token) {
        try {
            jsonWebTokenValidator.validate(token);
        } catch (TokenExpiredException | InvalidSignatureException e) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        Optional<User> optionalUser = userService.getUserByName(token.claim().getParameter("principal").toString());
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return Optional.of(new User(user.getUsername(), user.getId(), user.getRoleId()));
        }
        return Optional.absent();
    }
}


