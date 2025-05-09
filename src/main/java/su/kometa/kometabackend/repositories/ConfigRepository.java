package su.kometa.kometabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Config;

import java.util.Optional;

@Repository
public interface ConfigRepository extends CrudRepository<Config, Long> {

    Optional<Config> findByKey(String key);

    void deleteByKey(String key);
}
