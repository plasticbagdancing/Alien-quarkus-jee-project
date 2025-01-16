package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;
import fr.pantheonsorbonne.service.ChampDeBatailleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("champDeBataille")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChampDeBatailleRessources {

    private final ChampDeBatailleService champDeBatailleService;

    @Inject
    public ChampDeBatailleRessources(ChampDeBatailleService champDeBatailleService) {
        this.champDeBatailleService = champDeBatailleService;
    }

    @GET
    @Path("etat")
    public Response getEtatChampDeBataille() {
        ChampDeBatailleDTO champDeBatailleDTO = champDeBatailleService.getEtatChampDeBataille();
        return Response.ok(champDeBatailleDTO).build();
    }

    @PUT
    @Path("etat")
    public Response mettreAJourEtatChampDeBataille(ChampDeBatailleDTO champDeBatailleDTO) {
        champDeBatailleService.mettreAJourEtatChampDeBataille(champDeBatailleDTO);
        return Response.noContent().build();
    }
}