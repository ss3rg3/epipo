package io.github.ss3rg3.pong.endpoints;

import io.github.ss3rg3.pong.models.AppConfig;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static java.util.Objects.requireNonNull;

@Path("/")
public class IndexEndpoint {

    private final Template page;

    @Inject
    AppConfig appConfig;

    public IndexEndpoint(Template page) {
        this.page = requireNonNull(page, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> get() {
        return Uni.createFrom().item(
                this.page.data("name", this.appConfig.name()));
    }

}
