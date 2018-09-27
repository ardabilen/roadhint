package roadhint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roadhint.model.MessageHint;


@Repository
public interface
MessageHintRepository extends JpaRepository<MessageHint, Long> {

}

