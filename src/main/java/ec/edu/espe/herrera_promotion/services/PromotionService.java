package ec.edu.espe.herrera_promotion.services;

import ec.edu.espe.herrera_promotion.models.entities.Promotion;
import java.util.List;
import java.util.Optional;

public interface PromotionService {
    List<Promotion> findAll();
    Optional<Promotion> findById(Long id);
    Promotion save(Promotion promotion);
    void deleteById(Long id);
}
