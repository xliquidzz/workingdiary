package ch.css.workingdiary.user;

import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.UserService;
import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserServiceShould {

    private static final UserDao mockedUserDao = mock(UserDao.class);
    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService(mockedUserDao, "anySecretKeyForTesting".getBytes());
    }

    @After
    public void tearDown() {
        userService = null;
    }

    @Test
    public void returnAccessToken() {
        final User expected = new User("xliquidzz", "12345");
        when(mockedUserDao.getByUsernameWithPassword("xliquidzz")).thenReturn(expected);

        Optional<Map<String,String>> optionalMap = userService.getAccessToken(expected);
        assertTrue(optionalMap.isPresent());

        final Map<String, String> accessTokenMap = optionalMap.get();
        final String accessToken = accessTokenMap.get("accessToken");

        assertTrue(accessToken instanceof String);
    }

    @Test
    public void returnUserByName() {
        final User expected = new User("xliquidzz", "12345");
        when(mockedUserDao.getByUsername(expected.getUsername())).thenReturn(expected);

        Optional<User> optionalUser = userService.getUserByName(expected.getUsername());
        assertTrue(optionalUser.isPresent());

        final User actual = optionalUser.get();

        assertThat(actual.getUsername()).isEqualTo(expected.getUsername());
    }

    @Test
    public void returnAllApprentice() {
        List<User> expected = new ArrayList<>();
        final long roleId = 1;
        for (long id = 1; id <= 10; id++) {
            expected.add(new User("testUser", id, 1));
        }

        when(mockedUserDao.getUsersWithRole(roleId)).thenReturn(expected);

        final Optional<List<User>> optionalAllApprentice = userService.getUsersWithRole(roleId);

        assertTrue(optionalAllApprentice.isPresent());

        List<User> allApprentice = optionalAllApprentice.get();

        assertTrue(allApprentice.size() == 10);
        long testId = 0;
        for (User user : allApprentice) {
            assertThat(user.getId()).isEqualTo(++testId);
        }

        verify(mockedUserDao, times(1)).getUsersWithRole(roleId);
    }

    @Test
    public void returnAllApprenticeOfTrainer() {
        List<User> expected = new ArrayList<>();
        final long trainerId = 1;
        for (long id = 1; id <= 10; id++) {
            expected.add(new User("testUser", id, 1));
        }

        when(mockedUserDao.getUsersWithTrainerId(trainerId)).thenReturn(expected);

        final Optional<List<User>> optionalApprenticeOfTrainer = userService.getUsersWithTrainerId(trainerId);

        assertTrue(optionalApprenticeOfTrainer.isPresent());

        List<User> apprenticeOfTrainer = optionalApprenticeOfTrainer.get();

        assertTrue(apprenticeOfTrainer.size() == 10);
        long testId = 0;
        for (User user : apprenticeOfTrainer) {
            assertThat(user.getId()).isEqualTo(++testId);
        }

        verify(mockedUserDao, times(1)).getUsersWithTrainerId(trainerId);
    }
}
