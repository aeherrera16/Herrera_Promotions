package ec.edu.espe.herrera_promotion.services;

import ec.edu.espe.herrera_promotion.models.entities.Promotion;
import ec.edu.espe.herrera_promotion.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private PromotionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Promotion> findAll() {
        return (List<Promotion>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Promotion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Promotion save(Promotion promotion) {
        return repository.save(promotion);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
