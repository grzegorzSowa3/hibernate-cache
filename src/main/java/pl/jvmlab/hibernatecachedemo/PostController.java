package pl.jvmlab.hibernatecachedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestHeader UUID userId,
            @RequestBody CreatePostDto dto) {
        final UUID postId = postService.createPost(userId, dto);
        return ResponseEntity.status(CREATED).body(postId.toString());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> editPost(
            @RequestHeader UUID userId,
            @PathVariable UUID postId,
            @RequestBody EditPostDto dto) {
        securityService.checkPostEditAccess(userId, postId);
        postService.editPost(postId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

}
