package ch.css.workingdiary.service;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.representation.User;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserService implements Service {

    private final UserDao userDao;
    private final EntryService entryService;
    private byte[] tokenSecret;

    public UserService(final UserDao userDao, final byte[] tokenSecret) {
        this.userDao = userDao;
        this.tokenSecret = tokenSecret;
        this.entryService = WorkingDiaryApp.getService(EntryService.class);
    }

    public UserService(final UserDao userDao) {
        this.userDao = userDao;
        this.entryService = WorkingDiaryApp.getService(EntryService.class);
    }

    public Optional<User> getUserByName(final String username) {
        final User user = userDao.getByUsername(username);
        return Optional.of(user);
    }

    public Optional<User> authenticate(final String username, final String password) {
        final User userToCompareWith = userDao.getByUsernameWithPassword(username);
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

    public Optional<List<User>> getUsersWithRole(final long roleId) {
        final List<User> users = userDao.getUsersWithRole(roleId);
        final List<User> usersToReturn = new ArrayList<>();

        for (User user : users) {
            usersToReturn.add(new User(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getRoleId()));
        }
        return Optional.of(usersToReturn);
    }

    public Optional<List<User>> getUsersWithTrainerId(final long trainerId) {
        final List<User> users = userDao.getUsersWithTrainerId(trainerId);
        return Optional.of(users);
    }

    public void deleteById(final long userId) {
        userDao.deleteApprenticeReferenceToTrainer(userId);
        entryService.deleteEntriesByUserId(userId);
        userDao.deleteById(userId);
    }
}
