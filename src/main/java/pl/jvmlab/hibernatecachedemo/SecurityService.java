package pl.jvmlab.hibernatecachedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final PostRepository postRepository;

    public void checkPostEditAccess(UUID userId, UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);
        if (!post.getAuthor().equals(userId)) {
            throw new InvalidAccessException();
        }
    }

}
