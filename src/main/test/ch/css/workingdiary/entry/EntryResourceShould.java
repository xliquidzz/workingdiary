package ch.css.workingdiary.entry;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.WorkingDiaryConfiguration;
import ch.css.workingdiary.representation.Entry;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.filter;

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

        final Entry expected = new Entry("testTitle", "testMessage", false);

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

    @Test
    public void returnEntries() {
        final Client client = new Client();

        final ClientResponse response = client.resource(String.format("http://localhost:%d/api/entry", RULE.getLocalPort()))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(200);

        List<Entry> entries = response.getEntity(new GenericType<List<Entry>>() {});

        assertThat(entries.isEmpty()).isEqualTo(false);
    }
}
