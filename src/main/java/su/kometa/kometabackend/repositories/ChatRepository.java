
package su.kometa.kometabackend.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.User;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

    Chat findById(long id);

    void delete(@NonNull Chat chat);
}
