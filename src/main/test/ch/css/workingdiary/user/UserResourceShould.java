package ch.css.workingdiary.user;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.representation.User;
import com.google.common.base.Optional;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.junit.Assert.assertTrue;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserResourceShould {

    private static final String CONFIG = "test_config.yaml";
    private static String validToken;

    @ClassRule
    public static final DropwizardAppRule<WorkingDiaryConfiguration> RULE = new DropwizardAppRule(WorkingDiaryApp.class, CONFIG);

    @BeforeClass
    public static void authenticate() {
        User userO = new User("test_vocationTrainer", "12345");

        final Client client = new Client();

        final ClientResponse authResponse = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, userO);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        Map<String, String> tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});

        String token = tokenMap.get("accessToken");

        validToken = "bearer " + token;
    }

    @Test
    public void returnAccessToken() {

        User expected = new User("test_apprentice", "12345", "testFirstname", "testLastname", 1);

        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(200);

        Map<String, String> tokenMap = response.getEntity(new GenericType<Map<String, String>>() {});

        String token = tokenMap.get("accessToken");

        assertTrue(token instanceof String);
    }

    @Test
    public void returnAllApprentice() {

        final Client client = new Client();
        long roleId = 1;

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user/role/" + roleId, RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<User> users = response.getEntity(new GenericType<List<User>>() {});

        assertThat(users.isEmpty()).isEqualTo(false);

    }

    @Test
    public void return403WhenAllApprentice() {

        final Client client = new Client();
        long roleId = 1;

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user/role/" + roleId, RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(401);
    }
}
