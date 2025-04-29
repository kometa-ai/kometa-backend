package su.kometa.kometabackend.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Model;

@Repository
public interface ModelRepository extends CrudRepository<Model, Long> {

    Model findById(long id);

    void delete(@NonNull Model model);
}
