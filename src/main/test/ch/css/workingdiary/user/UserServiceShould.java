package ch.css.workingdiary.user;

import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.representation.Entry;
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
        when(mockedUserDao.getByUsername("xliquidzz")).thenReturn(expected);

        Optional<Map<String,String>> optionalMap = userService.getAccessToken(expected);
        assertTrue(optionalMap.isPresent());

        final Map<String, String> accessTokenMap = optionalMap.get();
        final String accessToken = accessTokenMap.get("accessToken");

        assertTrue(accessToken instanceof String);
    }


}
