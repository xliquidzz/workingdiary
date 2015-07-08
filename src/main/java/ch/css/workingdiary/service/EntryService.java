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

package ch.css.workingdiary.service;

import ch.css.workingdiary.dao.EntryDao;
import ch.css.workingdiary.representation.Entry;
import com.google.common.base.Optional;

import java.util.List;

/**
 * Created by sandro on 04.03.2015.
 */
public class EntryService implements Service {

    private final EntryDao entryDao;

    public EntryService(final EntryDao entryDao) {
        this.entryDao = entryDao;
    }

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

    public void deleteEntriesByUserId(final long userId) {
        entryDao.deleteUserReferenceToEntry(userId);
    }

    public Optional<Entry> getById(final long entryId) {
        final Entry entry = entryDao.getEntryById(entryId);
        return Optional.fromNullable(entry);
    }

    public void deleteEntryById(final long entryId) {
        entryDao.deleteById(entryId);
    }

    public void updateById(final long entryId, Entry entry) {
        entryDao.updateById(entryId, entry.getTitle(), entry.getMessage());
    }
}
