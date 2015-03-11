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

    public EntryService(final EntryDao entryDao) {
        this.entryDao = entryDao;
    }

    // TODO change user id - magic number
    public Optional<Long> create(final Entry newEntry, final long userId) {
        final long newEntryId = entryDao.create(newEntry.getTitle(), newEntry.getMessage(), newEntry.isDraft(), userId);
        return Optional.of(newEntryId);
    }

    public Optional<List<Entry>> getEntries() {
        return Optional.of(entryDao.getEntries());
    }


    public Optional<List<Entry>> getEntriesByUserId(final long userId) {
        final List<Entry> entries = entryDao.getEntriesByUserId(userId);
        return Optional.of(entries);
    }

    public Optional<Entry> getById(final long entryId) {
        final Entry entry = entryDao.getEntryById(entryId);
        return Optional.of(entry);
    }

    public void deleteEntryById(final long entryId) {
        entryDao.deleteById(entryId);
    }
}
