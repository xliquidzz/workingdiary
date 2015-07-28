package server.ch.css.workingdiary.user;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.DropwizardAppRule;
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
    private static String validVocationToken;
    private static String validTrainerToken;
    private static String validApprenticeToken;

    @ClassRule
    public static final DropwizardAppRule<WorkingDiaryConfiguration> RULE = new DropwizardAppRule(WorkingDiaryApp.class, CONFIG);

    @BeforeClass
    public static void authenticate() {
        // Get Vocation Trainer Token
        User vocationTrainer = new User("test_vocationTrainer", "12345");

        final Client client = new Client();

        ClientResponse authResponse = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .header("Authorization", validVocationToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, vocationTrainer);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        Map<String, String> tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});

        String token = tokenMap.get("accessToken");

        validVocationToken = "bearer " + token;

        // Get Trainer Token
        User trainer = new User("test_trainer", "12345");

        authResponse = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .header("Authorization", validVocationToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, trainer);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});

        token = tokenMap.get("accessToken");

        validTrainerToken = "bearer " + token;

        // Get Apprentice Token
        User apprentice = new User("test_trainer", "12345");

        authResponse = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .header("Authorization", validVocationToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, apprentice);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});

        token = tokenMap.get("accessToken");

        validApprenticeToken = "bearer " + token;
    }

    /*
    @Test
    public void createNewUser() {
        final User newUser = new User(1, "new_Apprentice", "secret","firstname", "lastname", Role.APPRENTICE.getRoleId());
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user", RULE.getLocalPort()))
                .header("Authorization", validVocationToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, newUser);

        assertThat(response.getStatus()).isEqualTo(201);

        assertThat(response.getHeaders().getFirst("Location")).matches(
                Pattern.quote(
                        String.format("http://localhost:%d/api/user/", RULE.getLocalPort())
                ).concat("[0-9]+")
        );
    }
    */

    @Test
    public void returnAccessToken() {

        User expected = new User("test_apprentice", "test", "testFirstname", "testLastname", 1);

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
                .header("Authorization", validVocationToken)
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

    @Test
    public void returnAllApprenticeOfTrainer() {

        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user/apprentices", RULE.getLocalPort()))
                .header("Authorization", validTrainerToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<User> users = response.getEntity(new GenericType<List<User>>() {});

        assertThat(users.isEmpty()).isEqualTo(false);
    }

    @Test
    public void return401WhenGetAllApprenticeOfTrainer() {

        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/user/apprentices", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(401);
    }

/*    @Test
    public void deleteUserById() {
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry/" + expected.getId(), RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(204);*/
}
