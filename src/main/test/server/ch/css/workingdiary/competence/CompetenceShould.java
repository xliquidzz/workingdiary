package server.ch.css.workingdiary.competence;

import ch.css.workingdiary.representation.Entry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by sandro on 04.03.2015.
 */
public class CompetenceShould {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJson() throws IOException {
        final Entry entry = new Entry(1, "testTitle", "testMessage", new DateTime(15,2,5,20,20), false);
        assertThat(MAPPER.writeValueAsString(entry)).isEqualTo(fixture("fixtures/serialize/entry.json"));
    }

    @Test
    public void deserializeToJson() throws IOException {
        final Entry expected = new Entry("testTitle", "testMessage", false);
        final Entry actual = MAPPER.readValue(fixture("fixtures/deserialize/entry.json"), Entry.class);

        assertThat(actual.getTitle()).isEqualTo(expected.getTitle());
        assertThat(actual.getMessage()).isEqualTo(expected.getMessage());
        assertThat(actual.isDraft()).isEqualTo(expected.isDraft());
    }
}
