package ch.css.workingdiary.entry;

import ch.css.workingdiary.dao.EntryDao;
import ch.css.workingdiary.representation.Entry;
import ch.css.workingdiary.service.EntryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by sandro on 04.03.2015.
 */
public class EntryServiceShould {

    private static final EntryDao mockedEntryDao = mock(EntryDao.class);
    private EntryService entryService;

    private final Entry entry = new Entry(1, "entrytitle name");

    @Before
    public void setup() {
        entryService = new EntryService(mockedEntryDao);
        when(mockedEntryDao.create(anyString(), anyString(), anyBoolean(), anyLong())).thenReturn(entry.getId());
    }

    @After
    public void tearDown() {
        entryService = null;
    }

    @Test
    public void createNewEntry() {
        final Long newEntryId = entryService.create(entry);
        assertThat(newEntryId).isEqualTo(entry.getId());
        verify(mockedEntryDao, times(1)).create(anyString(), anyString(), anyBoolean(), anyLong());
    }
}
