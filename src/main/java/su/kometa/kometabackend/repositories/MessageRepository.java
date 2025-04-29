
package su.kometa.kometabackend.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Message findById(long id);

    void delete(@NonNull Message message);
}
