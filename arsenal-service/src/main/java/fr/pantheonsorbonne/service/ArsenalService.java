//package fr.pantheonsorbonne.service;
//
//import fr.pantheonsorbonne.dao.ArsenalDAO;
//import fr.pantheonsorbonne.dto.ArsenalDTO;
//import fr.pantheonsorbonne.entity.Arsenal;
//import fr.pantheonsorbonne.exception.ArsenalAlreadyExistsException;
//import fr.pantheonsorbonne.exception.InvalidArsenalException;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//
//import java.util.List;
//
//@ApplicationScoped
//public class ArsenalService {
//
//    @Inject
//    ArsenalDAO arsenalDAO;
//
//    public ArsenalDTO getArsenalById(Long id) {
//        Arsenal arsenal = arsenalDAO.getById(id);
//        if (arsenal == null) {
//            return null;
//        }
//        return new ArsenalDTO(arsenal.getId(), arsenal.getType(), arsenal.getDuration(), arsenal.getQuantity());
//    }
//
////    @Transactional
////    public Long checkAndSaveArsenal(ArsenalDTO arsenalDTO) throws InvalidArsenalException, ArsenalAlreadyExistsException {
////        if (arsenalDTO.Type() == null || arsenalDTO.Type().isBlank()) {
////            throw new InvalidArsenalException("Le type d'arme est invalide.");
////        }
////
////        if (arsenalDAO.isArsenalPresent(arsenalDTO.Type())) {
////            throw new ArsenalAlreadyExistsException();
////        }
////
////        Arsenal arsenal = new Arsenal();
////        arsenal.setType(arsenalDTO.Type());
////        arsenal.setDuration(arsenalDTO.Duration());
////        arsenal.setQuantity(arsenalDTO.Quantity());
////
////        return arsenal.getId();
////    }
//@Transactional
//public Long checkAndSaveArsenal(ArsenalDTO arsenalDTO) throws ArsenalAlreadyExistsException {
//       if(arsenalDAO.isArsenalPresent(arsenalDTO.Type())){
//        throw new ArsenalAlreadyExistsException();
//    }
//        Arsenal arsenal = new Arsenal();
//        arsenal.setType(arsenalDTO.Type());
//        arsenal.setDuration(arsenalDTO.Duration());
//        arsenal.setQuantity(arsenalDTO.Quantity());
//
//        return arsenal.getId();
//}
//
//    public List<Arsenal> getAllArmes(){
//        return arsenalDAO.getAll();
//    }
//}
package fr.pantheonsorbonne.service;

import fr.pantheonsorbonne.dao.ArsenalDAO;
import fr.pantheonsorbonne.dto.ArsenalDTO;
import fr.pantheonsorbonne.entity.Arsenal;
import fr.pantheonsorbonne.exception.ArsenalAlreadyExistsException;
import fr.pantheonsorbonne.exception.InvalidArsenalException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class ArsenalService {

    @Inject
    ArsenalDAO arsenalDAO;
    @Inject
    EntityManager entityManager;

    public ArsenalDTO getArsenalById(Long id) {
        Arsenal arsenal = arsenalDAO.getById(id);
        if(arsenal == null){
            System.out.println("Arsenal not found");
            return null;
        }
        return new ArsenalDTO(arsenal.getId(), arsenal.getType(), arsenal.getDuration(), arsenal.getQuantity());
    }

    public Long checkAndSaveArsenal(ArsenalDTO arsenalDTO) throws InvalidArsenalException, ArsenalAlreadyExistsException {
        // Validation des champs
        if (arsenalDTO.Type() == null ) {
            throw new InvalidArsenalException("Le type d'arme est invalide.");
        }
        if (arsenalDTO.Duration() <= 0) {
            throw new InvalidArsenalException("La durée de construction doit être supérieure à 0.");
        }
        if (arsenalDTO.Quantity() < 0) {
            throw new InvalidArsenalException("La quantité en stock ne peut pas être négative.");
        }

        Arsenal arsenal = new Arsenal();
        arsenal.setId(arsenalDTO.id());
        arsenal.setType(arsenalDTO.Type());
        arsenal.setDuration(arsenalDTO.Duration());
        arsenal.setQuantity(arsenalDTO.Quantity());
        arsenalDAO.saveArsenal(arsenal);
        System.out.println("Saving Arsenal: " + arsenalDTO);
        System.out.println("Fetching Arsenal with ID: " + arsenal.getId());

        return arsenal.getId();
    }

    public List<Arsenal> getAllArmes() {
        return arsenalDAO.getAll();
    }
}
