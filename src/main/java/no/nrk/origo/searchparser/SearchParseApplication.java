package no.nrk.origo.searchparser;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.nrk.origo.searchparser.health.TemplateHealthCheck;
import no.nrk.origo.searchparser.resources.SearchParseResource;

public class SearchParseApplication extends Application<SearchParseConfiguration> {

    public static void main(final String[] args) throws Exception {
        new SearchParseApplication().run(args);
    }

    @Override
    public String getName() {
        return "SearchParse";
    }

    @Override
    public void initialize(final Bootstrap<SearchParseConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final SearchParseConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final SearchParseResource resource = new SearchParseResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

    }


}
