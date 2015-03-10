package ch.css.workingdiary.resource;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.UserService;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;

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
    public Response generateAccessToken(final User userToCheck) {
        final Optional<User> userOptional = userService.authenticate(userToCheck.getUsername(), userToCheck.getPassword());
        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            final Optional<Map<String, String>> optionalAccessToken = userService.getAccessToken(user);
            if (optionalAccessToken.isPresent()) {
                final Map<String, String> accessToken = optionalAccessToken.get();
                return Response.ok(accessToken).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
