package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.BossDTO;
import fr.pantheonsorbonne.exception.BossAlreadyExistWithTheSamegalacticRegistrationNumberBoss;
import fr.pantheonsorbonne.exception.InvalidBossException;
import fr.pantheonsorbonne.service.BossService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

// retourner un Boss
@Path("boss")
public class BossRessources {
    @Inject
    BossService bossService;
    @GET
    @Path("{id}")
    // retourner le JSON
    public Response getBossById(@PathParam("id") Long id) {

       BossDTO boss = bossService.getBossById(id);
       if(boss == null) {
           throw new WebApplicationException(Response.Status.NOT_FOUND);
       }
       return Response.ok(boss).build();
    }
    // créer un Boss
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postBoss(BossDTO bossDTO){
        try {
            Long bossId = bossService.checkAndSaveBoss(bossDTO);
            return Response.created(URI.create("/boss/" + bossId)).build();
        } catch (InvalidBossException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        } catch (BossAlreadyExistWithTheSamegalacticRegistrationNumberBoss e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).build());
        }
    }
}
