package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.ArsenalDTO;
import fr.pantheonsorbonne.exception.ArsenalAlreadyExistsException;
import fr.pantheonsorbonne.exception.InvalidArsenalException;
import fr.pantheonsorbonne.gateway.ArsenalGateway;
import fr.pantheonsorbonne.service.ArsenalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("arsenal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ArsenalResource {

    @Inject
    ArsenalService arsenalService;

    @Inject
    ArsenalGateway arsenalGateway;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postArsenal(ArsenalDTO arsenalDTO) {
        try {
            Long arsenalId = arsenalService.checkAndSaveArsenal(arsenalDTO);
            System.out.println(arsenalId);
            return Response.created(URI.create("/arsenal/" + arsenalId) )
                    .entity("{\"id\": " + arsenalId + "}")
                    .build();
        } catch (InvalidArsenalException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        } catch (ArsenalAlreadyExistsException e) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT).build());
        }

    }

    @GET
    @Path("/sendArmes")
    public Response sendArmes() {
        try {
            arsenalGateway.sendArmes();

            return Response.ok("{\"message\": \"Le stock d'armes a été envoyé avec succès.\"}")
                    .header("Content-Type", "application/json")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"Erreur lors de l'envoi des armes : " + e.getMessage() + "\"}")
                    .header("Content-Type", "application/json")
                    .build();
        }
    }

    public Response getAllArmes() {
        return Response.ok(arsenalService.getAllArmes()).build();
    }

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
}

