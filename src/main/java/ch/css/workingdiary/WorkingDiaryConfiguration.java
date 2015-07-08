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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
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
