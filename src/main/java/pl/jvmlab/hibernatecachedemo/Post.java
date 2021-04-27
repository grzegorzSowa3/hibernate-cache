package pl.jvmlab.hibernatecachedemo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@NoArgsConstructor(access = PRIVATE)
public class Post {

    @Id
    private UUID id;
    private UUID author;
    private String text;

    public static Post newInstance(UUID author, String text) {
        Post post = new Post();
        post.id = UUID.randomUUID();
        post.author = author;
        post.text = text;
        return post;
    }

    public void edit(String newText) {
        this.text = newText;
    }

}
