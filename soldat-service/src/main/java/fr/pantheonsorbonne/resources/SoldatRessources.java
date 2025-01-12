package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.SoldatDTO;
import fr.pantheonsorbonne.exception.InvalidSoldatException;
import fr.pantheonsorbonne.exception.SoldatAlredayExistWithTheSameGalacticRegistrationNumberS;
import fr.pantheonsorbonne.service.SoldatService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
@Path("soldat")
public class SoldatRessources {
    @Inject
    SoldatService soldatService;

    @GET
    @Path("{id}") // Correctement défini pour capturer l'ID
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSoldatById(@PathParam("id") Long id) {
        SoldatDTO soldat = soldatService.getSoldatById(id);
        if (soldat == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(soldat).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postSoldat(SoldatDTO soldatDTO) {
        try {
            Long soldatId = soldatService.checkAndSaveSoldat(soldatDTO);
            return Response.created(URI.create("/soldat/" + soldatId)).build();
        } catch (InvalidSoldatException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        } catch (SoldatAlredayExistWithTheSameGalacticRegistrationNumberS e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).build());
        }
    }
}
