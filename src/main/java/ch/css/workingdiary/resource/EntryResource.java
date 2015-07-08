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

package ch.css.workingdiary.resource;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.EntryService;
import ch.css.workingdiary.util.Role;
import com.google.common.base.Optional;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by sandro on 04.03.2015.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/entry")
public class EntryResource {

    private final EntryService entryService;

    public EntryResource() {
        this.entryService = WorkingDiaryApp.getService(EntryService.class);
    }

    @POST
    public Response create(@Valid final Entry newEntry, @Auth User user) throws URISyntaxException {
        if (user.getRoleId() == Role.APPRENTICE.getRoleId()) {
            final Optional optionalNewEntryId = entryService.create(newEntry, user.getId());
            if(optionalNewEntryId.isPresent()) {
                final Long newEntryId = (Long) optionalNewEntryId.get();
                return Response.created(new URI(String.valueOf(newEntryId))).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    public Response getEntriesFromUser(@Auth User user) {
        if (user.getRoleId() == Role.APPRENTICE.getRoleId()) {
            final Optional<List<Entry>> optionalEntries = entryService.getEntriesByUserId(user.getId());
            if (optionalEntries.isPresent()) {
                final List<Entry> entries = optionalEntries.get();
                return Response.ok(entries).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getApprenticeEntries (@PathParam("userId") final long userId, @Auth User user) {
        if (user.getRoleId() == Role.VOCATION_TRAINER.getRoleId() || user.getRoleId() == Role.TRAINER.getRoleId()) {
            final Optional<List<Entry>> optionalEntries = entryService.getEntriesByUserId(userId);
            if (optionalEntries.isPresent()) {
                final List<Entry> entries = optionalEntries.get();
                return Response.ok(entries).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("/{entryId}")
    public Response deleteById(@PathParam("entryId") final long entryId, @Auth final User user) {
        if (user.getRoleId() == Role.APPRENTICE.getRoleId()) {
            final Optional<Entry> optionalEntry = entryService.getById(entryId);
            if (optionalEntry.isPresent()) {
                final Entry entryToDelete = optionalEntry.get();
                if (entryToDelete.getUserId() == user.getId()) {
                    entryService.deleteEntryById(entryId);
                    return Response.noContent().build();
                }
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("/{entryId}")
    public Response updateById(@PathParam("entryId") final long entryId, @Valid Entry entry, @Auth final User user) {
        final Optional<Entry> optionalEntry = entryService.getById(entryId);
        if (user.getRoleId() == Role.APPRENTICE.getRoleId()) {
            if (optionalEntry.isPresent()) {
                final Entry entryToDelete = optionalEntry.get();
                if (entryToDelete.getUserId() == user.getId()) {
                    entryService.updateById(entryId, entry);
                    return Response.noContent().build();
                }
                return Response.status(Response.Status.FORBIDDEN).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}


