package ch.css.workingdiary.service;

import ch.css.workingdiary.dao.EntryDao;
import ch.css.workingdiary.representation.Entry;
import com.google.common.base.Optional;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * Created by sandro on 04.03.2015.
 */
public class EntryService implements Service {

    private final EntryDao entryDao;

    public EntryService(EntryDao entryDao) {
        this.entryDao = entryDao;
    }

    public EntryService(DBI dbi) {
        this.entryDao = dbi.onDemand(EntryDao.class);
    }

    // TODO change user id - magic number
    public long create(final Entry newEntry) {
        final long newEntryId = entryDao.create(newEntry.getTitle(), newEntry.getMessage(), newEntry.isDraft(), 1);
        return newEntryId;
    }

    public Optional getEntries() {
        return Optional.of(entryDao.getEntries());
    }
}
