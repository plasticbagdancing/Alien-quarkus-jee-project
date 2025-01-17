package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.ChampDeBatailleDTO;
import fr.pantheonsorbonne.service.ChampDeBatailleService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/champ-de-bataille")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChampDeBatailleRessources {

    @Inject
    ChampDeBatailleService service;

    @GET
    public List<ChampDeBatailleDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public ChampDeBatailleDTO getById(@PathParam("id") Long id) {
        return service.getById(id);
    }

    @POST
    public Response create(ChampDeBatailleDTO dto) {
        service.create(dto);
        return Response.created(URI.create("/champ-de-bataille")).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ChampDeBatailleDTO dto) {
        service.update(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
