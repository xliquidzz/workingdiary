package server.ch.workingdiary.user;

import ch.css.workingdiary.representation.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by sandro on 09.03.2015.
 */
public class UserShould {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJson() throws IOException {
        // Arrange
        final User user = new User("testUser", "12345", "testFirstName", "testLastName", 1);

        // Actual
        final String actual = MAPPER.writeValueAsString(user);

        // Assert
        assertThat(actual).isEqualTo(fixture("fixtures/serialize/user.json"));
    }

    @Test
    public void deserializeToJson() throws IOException {
        //Arrange
        final User expected = new User(1, "testUser", "12345", "testFirstName", "testLastName", 1);

        // Actual
        final User actual = MAPPER.readValue(fixture("fixtures/deserialize/user.json"), User.class);
        // Assert
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getUsername()).isEqualTo(expected.getUsername());
        assertThat(actual.getFirstname()).isEqualTo(expected.getFirstname());
        assertThat(actual.getLastname()).isEqualTo(expected.getLastname());
    }
}
