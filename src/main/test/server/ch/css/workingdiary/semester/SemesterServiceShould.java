package server.ch.css.workingdiary.semester;

import ch.css.workingdiary.dao.EntryDao;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.service.EntryService;
import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by sandro on 04.03.2015.
 */
public class SemesterServiceShould {

    private static final EntryDao mockedEntryDao = mock(EntryDao.class);
    private EntryService entryService;

    private final Entry entry = new Entry(1, "entrytitle name");

    @Before
    public void setup() {
        entryService = new EntryService(mockedEntryDao);
    }

    @After
    public void tearDown() {
        entryService = null;
    }


    @Test
    public void createNewEntry() {
        when(mockedEntryDao.create(anyString(), anyString(), anyBoolean(), anyLong())).thenReturn(entry.getId());
        final Optional optionalNewEntryId = entryService.create(entry, 1);
        assertThat(optionalNewEntryId.isPresent());
        verify(mockedEntryDao, times(1)).create(anyString(), anyString(), anyBoolean(), anyLong());
    }

    @Test
    public void returnEntries() {
        List<Entry> expected = new ArrayList<Entry>();
        for (long id = 1; id <= 10; id++) {
            expected.add(new Entry(id, "testTitle" + id));
        }

        when(mockedEntryDao.getEntries()).thenReturn(expected);

        final Optional<List<Entry>> optionalEntries =  entryService.getEntries();
        assertThat(optionalEntries.isPresent());

        final List<Entry> entries = optionalEntries.get();

        assertTrue(entries.size() == 10);
        long testId = 0;
        for (Entry entry : entries) {
            assertThat(entry.getId()).isEqualTo(++testId);
        }

        verify(mockedEntryDao, times(1)).getEntries();
    }

    @Test
    public void deleteEntryById() {
        entryService.deleteEntryById(1);
        verify(mockedEntryDao, times(1)).deleteById(1);
    }

    @Test
    public void updateEntryById() {
        Entry expected = new Entry(1, "testTitle", "testMessage");
        entryService.updateById(expected.getId(), expected);
        verify(mockedEntryDao, times(1)).updateById(eq(expected.getId()), anyString(), anyString());
    }
}
