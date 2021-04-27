package pl.jvmlab.hibernatecachedemo;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {

}
