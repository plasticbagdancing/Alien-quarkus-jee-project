package fr.pantheonsorbonne.camel;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import jakarta.inject.Inject;
import fr.pantheonsorbonne.service.ArsenalService;


public class CamelRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:sendInfoArmesBoss")
                .log("Envoi des informations armes : ${body}")
                .to("sjms2:M1.infoArmesBoss")
                .log("Les informations ont ete  envoye avec succes : ${body}");

    }

}
