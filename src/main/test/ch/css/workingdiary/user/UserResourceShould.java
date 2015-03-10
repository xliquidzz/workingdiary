package ch.css.workingdiary.user;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserResourceShould {

    private static final String CONFIG = "test_config.yaml";
    private String validToken;

    @ClassRule
    public static final DropwizardAppRule<WorkingDiaryConfiguration> RULE = new DropwizardAppRule(WorkingDiaryApp.class, CONFIG);

    @Before
    public void setup() {
        //validToken = authenticate();
    }

    @Test
    public void returnAccessToken() {

        User expected = new User("xliquidzz", "12345", "testFirstname", "testLastname", 1);

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
/*
    public String authenticate() {
        User user = new User("xliquidzz", "12345");

        final Client client = new Client();

        final ClientResponse authResponse = client.resource(String.format("http://localhost:%d/api/jwt/generate-token", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        Map<String, String> tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});
        String token = tokenMap.get("accessToken");
        return token;
    }*/
}
