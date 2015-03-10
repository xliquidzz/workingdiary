package ch.css.workingdiary.security;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.UserService;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.exceptions.TokenExpiredException;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

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
    public Optional<User> authenticate(final JsonWebToken token) throws AuthenticationException {
        try {
            jsonWebTokenValidator.validate(token);
        } catch (TokenExpiredException e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
        Optional<User> optionalUser = userService.getUserByName(token.claim().getParameter("principal").toString());
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return Optional.of(new User(user.getUsername(), user.getId(), user.getRoleId()));
        }
        return Optional.absent();
    }
}


