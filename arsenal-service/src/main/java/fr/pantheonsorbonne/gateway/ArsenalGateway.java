package fr.pantheonsorbonne.gateway;
import fr.pantheonsorbonne.service.ArsenalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;


@ApplicationScoped
public class ArsenalGateway {
    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    ArsenalService arsenalService;

    public void sendArmes() {
        System.out.println("Sending armes");
        producerTemplate.sendBody("direct:sendInfoArmesBoss",arsenalService.getAllArmes());
    }
}
