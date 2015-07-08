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
import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.representation.User;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Signer;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebToken;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenClaim;
import com.github.toastshaman.dropwizard.auth.jwt.model.JsonWebTokenHeader;
import com.google.common.base.Optional;
import org.joda.time.DateTime;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        return Optional.fromNullable(user);
    }

    public Optional<User> authenticate(final String username, final String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final User userToCompareWith = userDao.getByUsernameWithPassword(username);
        if (userToCompareWith != null) {
            final String hashedPassword = generateHashedPassword(password);
            if (username.equals(userToCompareWith.getUsername()) && hashedPassword.equals(userToCompareWith.getPassword())) {
                return Optional.fromNullable(userToCompareWith);
            }
        }
        return Optional.absent();
    }

    public Optional<Map<String, String>> getAccessToken(final User user) {
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

    public Optional<Long> create(final User newUser) throws InvalidKeySpecException, NoSuchAlgorithmException {
        final long newUserId = userDao.create(newUser.getUsername(), generateHashedPassword(newUser.getPassword()), newUser.getFirstname(), newUser.getLastname(), newUser.getRoleId());
        return Optional.of(newUserId);
    }

    public String generateHashedPassword(final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public void changePassword(final String newPassword, final long userId) throws InvalidKeySpecException, NoSuchAlgorithmException {
        userDao.changePassword(generateHashedPassword(newPassword), userId);
    }
}
