package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.SoldatDTO;
import fr.pantheonsorbonne.dto.Soldats;
import fr.pantheonsorbonne.exception.InvalidSoldatException;
import fr.pantheonsorbonne.exception.SoldatAlredayExistWithTheSameGalacticRegistrationNumberS;
import fr.pantheonsorbonne.gateway.SoldatGateway;
import fr.pantheonsorbonne.service.SoldatService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Path("soldat")
public class SoldatRessources {


    @Inject
    SoldatGateway soldatGateway;
    @Inject
    SoldatService soldatService;

    // Endpoint pour récupérer les statistiques des soldats
//    @GET
//    @Path("/stats")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getSoldatStats() {
//        String stats = soldatGateway.getSoldatStats(); // Appel à la méthode dans SoldatGateway
//        return Response.ok(stats).build();
//    }

//    // Endpoint pour générer des soldats via un corps JSON dans le POST
//    // Endpoint pour générer des soldats via un corps JSON dans le POST
//    @POST
//    @Path("/envoyerauchampdebataille")
//    @Consumes("application/json")
//    public Response genererSoldats(Map<String, Integer> stats) {
//        // Appel de la méthode pour générer les soldats
//        soldatGateway.genererSoldats(stats);
//
//        return Response.ok("Soldats envoyés au champ de bataille avec succès").build();
//    }

    @POST
    @Path("/envoyerAuchampDebataille")
    @Consumes("application/json")
    public Response envoyerAuChampDeBataille() {
        try {

            List<SoldatDTO> soldats = soldatService.getSoldats();
            soldatGateway.sendSoldatStatsToBattlefield(soldats);

            return Response.ok("Soldats envoyés au champ de bataille avec succès").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erreur lors de l'envoi des soldats au champ de bataille : " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}")
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
