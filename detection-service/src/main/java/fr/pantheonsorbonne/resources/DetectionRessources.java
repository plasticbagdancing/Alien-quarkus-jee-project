package fr.pantheonsorbonne.resources;

import fr.pantheonsorbonne.dto.DetectionDTO;
import fr.pantheonsorbonne.entity.Detection;
import fr.pantheonsorbonne.gateway.DetectionGateway;
import fr.pantheonsorbonne.service.DetectionService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/detections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DetectionRessources {

    @Inject
    DetectionGateway detectionGateway;

    @Inject
    private DetectionService detectionService;

    @POST
    public Response addDetection(DetectionDTO detectionDTO) {
        Detection detection = new Detection();
        detection.setIdAlerte(detectionDTO.idAlerte());
        detection.setDatetime(detectionDTO.datetime());
        detection.setNbAliens(detectionDTO.nbAliens());
        detection.setMessage(detectionDTO.message());

        detectionService.handleDetection(detection);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response getAllDetections() {
        return Response.ok(detectionService.getAllDetections()).build();
    }


    @GET
    @Path("/sendDetections")
    public Response sendDetections() {
        try {
            detectionGateway.sendDetections();
            // Renvoie une réponse JSON avec un message de succès
            return Response.ok("{\"message\": \"La détection a été envoyée avec succès.\"}")
                    .header("Content-Type", "application/json")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"Erreur lors de l'envoi de la détection : " + e.getMessage() + "\"}")
                    .header("Content-Type", "application/json")
                    .build();
        }
    }


}
