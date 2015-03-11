package ch.css.workingdiary.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

/**
 * Created by sandro on 04.03.2015.
 */
public class Entry {

    private final long id;

    @NotBlank
    private final String title;

    private final String message;

    private final DateTime created;

    private final boolean draft;

    private long userId;

    @JsonCreator
    public Entry(@JsonProperty("title") final String title, @JsonProperty("message") final String message,
                 @JsonProperty("draft") final boolean draft) {
        this(0, title, message, null, draft);
    }

    public Entry(final long id, final String title, final String message, final DateTime created, final boolean draft) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.created = created;
        this.draft = draft;
    }

    public Entry(final long id, final String title, final String message, final DateTime created, final boolean draft, final long userId) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.created = created;
        this.draft = draft;
        this.userId = userId;
    }

    public Entry(final long id, final String title) {
        this(id, title, null, null, false);
    }



    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public DateTime getCreated() {
        return created;
    }

    public boolean isDraft() {
        return draft;
    }

    public long getUserId() {
        return userId;
    }
}
