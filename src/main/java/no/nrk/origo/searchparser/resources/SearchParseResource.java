package no.nrk.origo.searchparser.resources;

import no.nrk.origo.searchparser.api.*;

import io.dropwizard.jersey.params.DateTimeParam;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class SearchParseResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchParseResource.class);

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public SearchParseResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @POST
    public void receiveHello(@Valid Saying saying) {
        LOGGER.info("Received a saying: {}", saying);
    }
    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveDate(@QueryParam("date") Optional<DateTimeParam> dateTimeParam) {
        if (dateTimeParam.isPresent()) {
            final DateTimeParam actualDateTimeParam = dateTimeParam.get();
            LOGGER.info("Received a date: {}", actualDateTimeParam);
            return actualDateTimeParam.get().toString();
        } else {
            LOGGER.warn("No received date");
            return null;
        }
    }
}
