package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.ArsenalDTO;
import fr.pantheonsorbonne.exception.ArsenalAlreadyExistsException;
import fr.pantheonsorbonne.exception.InvalidArsenalException;
import fr.pantheonsorbonne.service.ArsenalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("arsenal")
public class ArsenalResource {

    @Inject
    ArsenalService arsenalService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArsenalById(@PathParam("id") Long id) {
        ArsenalDTO arsenal = arsenalService.getArsenalById(id);
        if (arsenal == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(arsenal).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postArsenal(ArsenalDTO arsenalDTO) {
        try {
            Long arsenalId = arsenalService.checkAndSaveArsenal(arsenalDTO);
            return Response.created(URI.create("/arsenal/" + arsenalId)).build();
        } catch (InvalidArsenalException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        } catch (ArsenalAlreadyExistsException e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).build());
        }
    }
}

