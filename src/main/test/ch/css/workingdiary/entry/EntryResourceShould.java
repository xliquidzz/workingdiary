package ch.css.workingdiary.entry;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.Entry;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by sandro on 04.03.2015.
 */
public class EntryResourceShould {

    private static final String CONFIG = "test_config.yaml";

    @ClassRule
    public static final DropwizardAppRule<WorkingDiaryConfiguration> RULE = new DropwizardAppRule<WorkingDiaryConfiguration>(WorkingDiaryApp.class, CONFIG);

    @Test
    public void createEntry() {
        final Client client = new Client();

        Entry expected = new Entry("testTitle", "testMessage", false);

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
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
}
