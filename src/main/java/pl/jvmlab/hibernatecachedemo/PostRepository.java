package pl.jvmlab.hibernatecachedemo;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.UUID;

import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface PostRepository extends CrudRepository<Post, UUID> {

    @QueryHints({@QueryHint(name = HINT_CACHEABLE, value = "true")})
    List<Post> findAll();

}
