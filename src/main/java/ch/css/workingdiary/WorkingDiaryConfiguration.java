package ch.css.workingdiary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.Duration;

import java.io.UnsupportedEncodingException;

/**
 * Created by sandro on 04.03.2015.
 */
public class WorkingDiaryConfiguration extends Configuration {

    private final static String TOKEN_SECRET = "dfwzsdzwh823zebdwdz772632gdsbd";

    private static final String FORMAT = "UTF-8";

    @JsonProperty
    private DataSourceFactory database;
    private Duration expireIn;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public byte[] getTokenSecret() throws UnsupportedEncodingException {
        return TOKEN_SECRET.getBytes(FORMAT);
    }

    public static Duration getExpireIn() {
        return Duration.standardHours(1);
    }
}
