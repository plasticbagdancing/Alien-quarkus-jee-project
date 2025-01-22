package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dto.Soldats;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class SoldierAggregatorService {
    @Inject
    SoldatService soldatService;


    public synchronized Soldats accumulate(Map<String, Integer> data) {

        int totalExperts = data.getOrDefault("experts", 0);
        int totalIntermediates = data.getOrDefault("intermediates", 0);
        int totalBeginners = data.getOrDefault("beginners", 0);
        soldatService.genererSoldats(data);

        return new Soldats(totalExperts, totalIntermediates, totalBeginners);
    }
}
