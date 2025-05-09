package su.kometa.kometabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {

    boolean existsByKey(String key);

    Config findByKey(String key);
}
