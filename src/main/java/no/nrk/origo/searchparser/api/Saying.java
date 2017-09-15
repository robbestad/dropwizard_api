package no.nrk.origo.searchparser.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Length;

public class Saying {
    private long id;

    @Length(max = 3)
    private String greeting;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(long id, String greeting) {
        this.id = id;
        this.greeting = greeting;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getGreeting() {
        return greeting;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("greeting", greeting)
                .toString();
    }
}
