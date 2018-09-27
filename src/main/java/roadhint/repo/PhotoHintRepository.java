package roadhint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roadhint.model.PhotoHint;


@Repository
public interface
PhotoHintRepository extends JpaRepository<PhotoHint, Long> {

}

