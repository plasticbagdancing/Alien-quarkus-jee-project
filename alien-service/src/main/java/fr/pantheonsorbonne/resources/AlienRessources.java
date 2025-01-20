package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.AlienDTO;
import fr.pantheonsorbonne.exception.AlienAlreadyExistWithTheSameGalacticRegistrationNumber;
import fr.pantheonsorbonne.gateway.AlienGateway;
import fr.pantheonsorbonne.service.Alienservice;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("Alien")
public class AlienRessources {
    @Inject
    Alienservice alienservice;

    @Inject
    AlienGateway alienGateway;
    @GET
    @Path("/generate")
    public Response generateAlien() {
        alienGateway.sendRandomAlien();  // Appel à la méthode qui génère et envoie l'alien et les stats
        return Response.ok("Aliens générés et envoyés au champ de bataille avec leurs statistiques ").build();
    }


    // Endpoint pour récupérer les statistiques des aliens générés
    @GET
    @Path("/stats")
    @Produces("application/json")
    public Response getAlienStats() {
        String stats = alienGateway.getAlienStats();  // Appel sur l'instance injectée de AlienGateway
        return Response.ok(stats).build();
    }



    // retourner un alien
    @GET
    @Path("{id}")




    // retourner le JSON
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlienById(@PathParam("id") Long id) {


        AlienDTO alien = alienservice.getAlienByID(id);
        if (alien == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(alien).build();
    }




    // créer un alien
    @POST
    // consumer le JSON
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAlien(AlienDTO alienDTO) {
        try {Long alienId = alienservice.checkandAlien(alienDTO); // Appel du service
            return Response.created(URI.create("/Alien/" + alienId)).build();
        } catch (AlienAlreadyExistWithTheSameGalacticRegistrationNumber e) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT)
                            .entity("Alien already exists with the same Galactic Registration Number")
                            .build()
            );
        }
    }


}

