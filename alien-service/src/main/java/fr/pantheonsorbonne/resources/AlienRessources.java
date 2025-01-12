package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.AlienDTO;
import fr.pantheonsorbonne.entity.Alien;
import fr.pantheonsorbonne.exception.AlienAlreadyExistWithTheSameGalacticRegistrationNumber;
import fr.pantheonsorbonne.service.Alienservice;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.attribute.standard.Media;
import java.net.URI;

@Path("Alien")
public class AlienRessources {
    @Inject
    Alienservice alienservice;

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
        try {
            Long alienId = alienservice.checkandAlien(alienDTO); // Appel du service
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
