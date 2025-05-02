package su.kometa.kometabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

    List<Model> findAll();
    
    Optional<Model> findByProvider(String provider);

    List<Model> findAllByProvider(String provider);

    void delete(@NonNull Model model);
}
