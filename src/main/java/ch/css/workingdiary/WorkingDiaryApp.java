package ch.css.workingdiary;

import ch.css.workingdiary.resource.EntryResource;
import ch.css.workingdiary.service.EntryService;
import ch.css.workingdiary.service.Service;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandro on 04.03.2015.
 */
public class WorkingDiaryApp extends Application<WorkingDiaryConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkingDiaryApp.class);

    private static final Map<Integer, Service> services = new HashMap<Integer, Service>();

    public static <T extends Service> T getService(Class<T> serviceClass) {
        return serviceClass.cast(services.get(serviceClass.hashCode()));
    }

    @Override
    public void initialize(Bootstrap<WorkingDiaryConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(WorkingDiaryConfiguration configuration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        // Services
        services.put(EntryService.class.hashCode(), new EntryService(jdbi));

        // Register Resources
        environment.jersey().register(new EntryResource());

        // Register security component

        // Register Authenticator and AuthProvider

        // Sets the API prefix to '/api'
        environment.jersey().setUrlPattern("/api/*");
    }

    public static void main(String[] args) throws Exception {
        new WorkingDiaryApp().run(args);
    }
}
