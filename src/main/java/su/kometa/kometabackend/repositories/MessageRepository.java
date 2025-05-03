package su.kometa.kometabackend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import su.kometa.kometabackend.models.Chat;
import su.kometa.kometabackend.models.Message;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Optional<Message> findById(long id);

    @Query("SELECT m FROM Message m WHERE m.chat = :chat AND m.timestamp < :before ORDER BY m.timestamp DESC LIMIT :limit")
    List<Message> findAllByChatAndTimestampBeforeOrderByTimestampDesc(@Param("chat") Chat chat, @Param("before") long before, @Param("limit") int limit);

    void delete(@NonNull Message message);
}
