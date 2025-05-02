
package su.kometa.kometabackend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

    Optional<Chat> findById(long id);

    List<Chat> findAllByUser(User user);

    void delete(@NonNull Chat chat);
}
