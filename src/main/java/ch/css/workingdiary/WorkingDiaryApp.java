/**
 * This file is part of Working Diary.
 *
 * Working Diary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Working Diary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Working Diary.  If not, see <http://www.gnu.org/licenses/>.
 */

package ch.css.workingdiary;

import ch.css.workingdiary.dao.CompetenceDao;
import ch.css.workingdiary.dao.EntryDao;
import ch.css.workingdiary.resource.EntryResource;
import ch.css.workingdiary.resource.UserResource;
import ch.css.workingdiary.security.WorkingDiaryAuthenticator;
import ch.css.workingdiary.service.CompetenceService;
import ch.css.workingdiary.service.EntryService;
import ch.css.workingdiary.dao.UserDao;
import ch.css.workingdiary.service.Service;
import ch.css.workingdiary.service.UserService;
import com.github.toastshaman.dropwizard.auth.jwt.JWTAuthProvider;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.JsonWebTokenValidator;
import com.github.toastshaman.dropwizard.auth.jwt.hmac.HmacSHA512Verifier;
import com.github.toastshaman.dropwizard.auth.jwt.parser.DefaultJsonWebTokenParser;
import com.github.toastshaman.dropwizard.auth.jwt.validator.ExpiryValidator;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandro on 04.03.2015.
 */
public class WorkingDiaryApp extends Application<WorkingDiaryConfiguration> {

    private static final Map<Integer, Service> services = new HashMap<>();

    public static <T extends Service> T getService(Class<T> serviceClass) {
        return serviceClass.cast(services.get(serviceClass.hashCode()));
    }

    @Override
    public void initialize(Bootstrap<WorkingDiaryConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(WorkingDiaryConfiguration configuration, Environment environment) throws Exception {

        final JerseyEnvironment jerseyEnvironment = environment.jersey();

        // Setup DB
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        final byte[] tokenSecret = configuration.getTokenSecret();

        // Services
        services.put(EntryService.class.hashCode(), new EntryService(jdbi.onDemand(EntryDao.class)));
        services.put(UserService.class.hashCode(), new UserService(jdbi.onDemand(UserDao.class), tokenSecret));
        services.put(CompetenceService.class.hashCode(), new CompetenceService(jdbi.onDemand(CompetenceDao.class)));

        // Register security components
        final JsonWebTokenParser tokenParser = new DefaultJsonWebTokenParser();
        final HmacSHA512Verifier tokenVerifier = new HmacSHA512Verifier(tokenSecret);
        final JsonWebTokenValidator expiryValidator = new ExpiryValidator(configuration.getExpireIn());

        // Register Authenticator and AuthProvider
        jerseyEnvironment.register(new JWTAuthProvider<>(new WorkingDiaryAuthenticator(expiryValidator), tokenParser, tokenVerifier, "Login expired, please sign in again."));

        // Register Resources
        jerseyEnvironment.register(new EntryResource());
        jerseyEnvironment.register(new UserResource());


        // Sets the API prefix to '/api'
        environment.jersey().setUrlPattern("/api/*");
    }

    public static void main(String[] args) throws Exception {
        new WorkingDiaryApp().run(args);
    }


}
