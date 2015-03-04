package ch.css.workingdiary;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * Created by sandro on 04.03.2015.
 */
public class WorkingDiaryConfiguration extends Configuration {

    @JsonProperty
    private DataSourceFactory database;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
