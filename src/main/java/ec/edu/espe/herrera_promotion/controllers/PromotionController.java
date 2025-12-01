package ec.edu.espe.herrera_promotion.controllers;

import ec.edu.espe.herrera_promotion.models.entities.Promotion;
import ec.edu.espe.herrera_promotion.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService service;

    /**
     * GET /api/promotions -> Listar todas las promociones
     */
    @GetMapping
    public ResponseEntity<List<Promotion>> findAll() {
        List<Promotion> promotions = service.findAll();
        return ResponseEntity.ok(promotions);
    }

    /**
     * GET /api/promotions/{id} -> Buscar una promoción por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Promotion> promotionOptional = service.findById(id);
        if (promotionOptional.isPresent()) {
            return ResponseEntity.ok(promotionOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID " + id + " not found");
    }

    /**
     * POST /api/promotions -> Crear una nueva promoción
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Promotion promotion) {
        Promotion savedPromotion = service.save(promotion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPromotion);
    }

    /**
     * PUT /api/promotions/{id} -> Actualizar una promoción existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Promotion promotion, @PathVariable Long id) {
        Optional<Promotion> promotionOptional = service.findById(id);
        
        if (promotionOptional.isPresent()) {
            Promotion promotionDB = promotionOptional.get();
            promotionDB.setName(promotion.getName());
            promotionDB.setDiscountPercentage(promotion.getDiscountPercentage());
            promotionDB.setStartDate(promotion.getStartDate());
            promotionDB.setEndDate(promotion.getEndDate());
            promotionDB.setStatus(promotion.getStatus());
            
            Promotion updatedPromotion = service.save(promotionDB);
            return ResponseEntity.ok(updatedPromotion);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID " + id + " not found");
    }

    /**
     * DELETE /api/promotions/{id} -> Eliminar una promoción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Promotion> promotionOptional = service.findById(id);
        if (promotionOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Promotion with ID " + id + " not found");
    }
}

