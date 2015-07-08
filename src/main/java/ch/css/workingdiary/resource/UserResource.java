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

import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.service.UserService;
import ch.css.workingdiary.util.Role;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.fill;
import static java.util.Collections.singletonMap;

/**
 * Created by sandro on 09.03.2015.
 */

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserResource {

    private final UserService userService;

    public UserResource() {
        this.userService = WorkingDiaryApp.getService(UserService.class);
    }

    @POST
    @Path("/generate-token")
    public Response generateAccessToken(final User userToCheck) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final Optional<User> userOptional = userService.authenticate(userToCheck.getUsername(), userToCheck.getPassword());
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            final Optional<Map<String, String>> optionalAccessToken = userService.getAccessToken(user);
            if (optionalAccessToken.isPresent()) {
                final Map<String, String> accessToken = optionalAccessToken.get();
                return Response.ok(accessToken).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    public Response create(final User newUser, @Auth final User user) throws URISyntaxException, InvalidKeySpecException, NoSuchAlgorithmException {
        if (user.getRoleId() == Role.VOCATION_TRAINER.getRoleId()) {
            final Optional<Long> optionalNewUserId = userService.create(newUser);
            if (optionalNewUserId.isPresent()) {
                final Long newUserId = optionalNewUserId.get();
                return Response.created(new URI(String.valueOf(newUserId))).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/get-role")
    public Response getUserRole(@Auth User user) {
        return Response.ok(singletonMap("roleId", user.getRoleId())).build();
    }

    @GET
    @Path("/role/{roleId}")
    public Response getUsersWithRole(@PathParam("roleId") final long roleId, @Auth final User user) {
        if (user.getRoleId() == Role.VOCATION_TRAINER.getRoleId()) {
            final Optional<List<User>> optionalUsers = userService.getUsersWithRole(roleId);
            if (optionalUsers.isPresent()) {
                final List<User> users = optionalUsers.get();
                return Response.ok(users).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/apprentices")
    public Response getApprenticesOfTrainer(@Auth final User user) {
        if (user.getRoleId() == Role.TRAINER.getRoleId()) {
            final Optional<List<User>> optionalUsers = userService.getUsersWithTrainerId(user.getId());
            if (optionalUsers.isPresent()) {
                final List<User> users = optionalUsers.get();
                return Response.ok(users).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUserById(@PathParam("userId") final long userId, @Auth final User user) {
        if (user.getRoleId() == Role.VOCATION_TRAINER.getRoleId()) {
            userService.deleteById(userId);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/change-password")
    public Response changePassword(final LinkedHashMap passwords, @Auth final User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final String oldPassword = (String) passwords.get("oldPassword");
        final String newPassword = (String) passwords.get("newPassword");
        final String repeatedPassword = (String) passwords.get("repeatedPassword");

        if (newPassword.equals(repeatedPassword)) {
            final Optional<User> optionalUser = userService.authenticate(user.getUsername(), oldPassword);
            if(optionalUser.isPresent()) {
                userService.changePassword(newPassword, user.getId());
                return Response.noContent().build();
            }
        }
        return Response.status(412).build();
    }
}
