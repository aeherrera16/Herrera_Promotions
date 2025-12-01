package ec.edu.espe.herrera_promotion.repositories;

import ec.edu.espe.herrera_promotion.models.entities.Promotion;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
    
}
