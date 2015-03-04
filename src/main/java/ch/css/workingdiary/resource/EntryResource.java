package ch.css.workingdiary.resource;

import ch.css.workingdiary.WorkingDiaryApp;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.service.EntryService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

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
    public Response create(final Entry newEntry) throws URISyntaxException {
        final long newEntryId = entryService.create(newEntry);
        return Response.created(new URI(String.valueOf(newEntryId))).build();
    }
}


