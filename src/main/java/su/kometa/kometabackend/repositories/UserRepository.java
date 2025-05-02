package su.kometa.kometabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(long id);

    void delete(@NonNull User user);
}
