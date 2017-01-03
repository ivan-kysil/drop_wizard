package jar;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jar.health.TemplateHealthCheck;
import jar.resources.HelloWorldResource;

public class some_nameApplication extends Application<some_nameConfiguration> {

    public static void main(final String[] args) throws Exception {
        new some_nameApplication().run(args);
    }

    @Override
    public String getName() {
        return "some_name";
    }

    @Override
    public void initialize(final Bootstrap<some_nameConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final some_nameConfiguration configuration,
                    final Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
