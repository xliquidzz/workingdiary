package ch.css.workingdiary.service;

import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.representation.User;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

import javax.security.auth.login.Configuration;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserService implements Service {

    private final UserDao userDao;
    private byte[] tokenSecret;
    private Optional<User> allApprentice;

    public UserService(final UserDao userDao, final byte[] tokenSecret) {
        this.userDao = userDao;
        this.tokenSecret = tokenSecret;
    }

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getUserByName(final String username) {
        final User user = userDao.getByUsername(username);
        return Optional.of(user);
    }

    public Optional<User> authenticate(final String username, final String password) {
        final User userToCompareWith = userDao.getByUsername(username);
        if (userToCompareWith != null) {
            if (password.equals(userToCompareWith.getPassword())) {
                return Optional.of(userToCompareWith);
            }
        }
        return Optional.absent();
    }

    public Optional<Map<String,String>> getAccessToken(final User user) {
        final DateTime now = DateTime.now();
        final HmacSHA512Signer signer = new HmacSHA512Signer(tokenSecret);
        final JsonWebToken token = JsonWebToken.builder()
                .header(JsonWebTokenHeader.HS512())
                .claim(JsonWebTokenClaim.builder()
                        .param("principal", user.getUsername())
                        .param("userId", user.getId())
                        .issuedAt(now)
                        .expiration(now.plusHours(1))
                        .build())
                .build();
        final String signedToken = signer.sign(token);
        return Optional.of(singletonMap("accessToken", signedToken));
    }

    public Optional<List<User>> getAllApprentice() {
        final List<User> apprentices = userDao.getAllApprentice();
        return Optional.of(apprentices);
    }
}
