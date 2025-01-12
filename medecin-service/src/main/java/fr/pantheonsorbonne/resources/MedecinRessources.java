package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.MedecinDTO;
import fr.pantheonsorbonne.exception.MedecinAlreadyExistWithTheSameGalacticRegistrationNumberM;
import fr.pantheonsorbonne.service.MedecinService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
@Path("medecin") // Chemin principal sans accent
public class MedecinRessources {
    @Inject
    MedecinService medecinService;

    @GET
    @Path("{id}") // Accès dynamique par ID
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedecinById(@PathParam("id") Long id) {
        MedecinDTO medecin = medecinService.getMedecinById(id);
        if (medecin == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(medecin).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMedecin(MedecinDTO medecinDTO) {
        try {
            Long medecinId = medecinService.checkAndSaveMedecin(medecinDTO);
            return Response.created(URI.create("/medecin/" + medecinId)).build(); // Chemin harmonisé sans accent
        } catch (MedecinAlreadyExistWithTheSameGalacticRegistrationNumberM e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).build());
        }
    }
}

