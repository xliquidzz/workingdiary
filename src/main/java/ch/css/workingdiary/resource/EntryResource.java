package ch.css.workingdiary.resource;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.representation.User;
import ch.css.workingdiary.service.EntryService;
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
        final Optional optionalNewEntryId = entryService.create(newEntry, user.getId());
        if(optionalNewEntryId.isPresent()) {
            final Long newEntryId = (Long) optionalNewEntryId.get();
            return Response.created(new URI(String.valueOf(newEntryId))).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
/*
    @GET
    @Path("/admin")
    public Response getEntries() {
        final Optional<List<Entry>> optionalEntries = entryService.getEntries();
        if (optionalEntries.isPresent()) {
            final List<Entry> entries = optionalEntries.get();
            return Response.ok(entries).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
*/

    @GET
    public Response getEntriesFromUser(@Auth User user) {
        final Optional<List<Entry>> optionalEntries = entryService.getEntriesByUserId(user.getId());
        if (optionalEntries.isPresent()) {
            final List<Entry> entries = optionalEntries.get();
            return Response.ok(entries).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}


