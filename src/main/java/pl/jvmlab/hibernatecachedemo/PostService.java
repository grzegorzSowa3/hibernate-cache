package pl.jvmlab.hibernatecachedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public UUID createPost(UUID userId, CreatePostDto dto) {
        final Post post = Post.newInstance(userId, dto.getText());
        postRepository.save(post);
        return post.getId();
    }

    public void editPost(UUID postId, EditPostDto dto) {
        final Post post = postRepository.findById(postId)
                .orElseThrow(NotFoundException::new);
        post.edit(dto.getText());
        postRepository.save(post);
    }

    public Post getPost(UUID postId) {
        return postRepository.findById(postId).orElseThrow(NotFoundException::new);
    }

}
