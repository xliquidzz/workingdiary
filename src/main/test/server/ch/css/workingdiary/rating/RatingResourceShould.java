package server.ch.css.workingdiary.rating;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.Entry;
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
import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by sandro on 04.03.2015.
 */
public class RatingResourceShould {

    private static final String CONFIG = "test_config.yaml";
    private static final long AVAILABLE_ID = 8;

    @ClassRule
    public static final DropwizardAppRule<WorkingDiaryConfiguration> RULE = new DropwizardAppRule<WorkingDiaryConfiguration>(WorkingDiaryApp.class, CONFIG);

    private static String validToken;

    private long newEntryId;


    @BeforeClass
    public static void authenticate() {
        User user = new User("test_apprentice", "test");

        final Client client = new Client();

        final ClientResponse authResponse = client.resource(String.format("http://localhost:%d/api/user/generate-token", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);

        assertThat(authResponse.getStatus()).isEqualTo(200);

        Map<String, String> tokenMap = authResponse.getEntity(new GenericType<Map<String, String>>() {});

        String token = tokenMap.get("accessToken");

        validToken = "bearer " + token;
    }

    @Test
    public void createEntry() {
        final Client client = new Client();

        final Entry expected = new Entry("testTitle", "testMessage", false);

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(201);

        assertThat(response.getHeaders().getFirst("Location")).matches(
                Pattern.quote(
                    String.format("http://localhost:%d/api/entry/", RULE.getLocalPort())
                ).concat("[0-9]+")
        );
    }

    @Test
    public void returnEntries() {
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<Entry> entries = response.getEntity(new GenericType<List<Entry>>() {});

        assertThat(entries.isEmpty()).isEqualTo(false);
    }

    @Test
    public void return401UnauthorizedWhenGetEntries() {
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void return401UnauthorizedWhenCreateEntry() {
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(401);
    }

    /*
    @Test
    public void updateEntry() {
        // You hav to change always AVAILABLE_ID
        final Client client = new Client();
        final Entry expected = new Entry(1, "absoluteTest", "absoluteTest");

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry/" + AVAILABLE_ID, RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void deleteEntry() {
        final Client client = new Client();
        final Entry expected = new Entry(1, "testTitle", "testMessage");

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry/" + expected.getId(), RULE.getLocalPort()))
                .header("Authorization", validToken)
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(204);
    }
    */

    @Test
    public void return401onUpdateEntry() {
        final Client client = new Client();
        final Entry expected = new Entry(1, "testTitle", "testMessage");

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry/" + expected.getId(), RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, expected);

        assertThat(response.getStatus()).isEqualTo(401);
    }
}
