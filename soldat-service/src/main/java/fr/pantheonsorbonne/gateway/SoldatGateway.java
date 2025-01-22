package fr.pantheonsorbonne.gateway;

import fr.pantheonsorbonne.dto.SoldatDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;

import java.util.List;

@ApplicationScoped
public class SoldatGateway {

    @Inject
    ProducerTemplate producerTemplate;


    public void sendSoldatStatsToBattlefield(List<SoldatDTO> soldats) {

        producerTemplate.sendBody("direct:sendSoldatStats", soldats);
    }
}
