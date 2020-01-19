package pl.angler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.angler.dto.PostDto;
import pl.angler.entity.Post;
import pl.angler.service.PostService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<PostDto>> getPosts(final Principal principal) {
        List<PostDto> allPosts = this.postService.getAllPosts(principal.getName());
        log.info("USER [email: " + principal.getName() + " gets all posts");
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/{nick}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<PostDto>> getPostsByNick(final Principal principal, @PathVariable("nick") String nick) {
        List<PostDto> allUserPosts = this.postService.getAllUserPosts(principal.getName(), nick);
        log.info("USER [email: " + principal.getName() + " gets user [nick: " + nick + "] posts");
        return new ResponseEntity<>(allUserPosts, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> createNewPost(final Principal principal, @RequestBody PostDto newPost) {
        Post post = this.postService.addNewPost(principal.getName(), newPost);
        log.info("USER [email: " + principal.getName() + "] create new post [id: " + post.getId() + "]");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> updatePost(final Principal principal, @RequestBody PostDto updatedPost) {
        this.postService.updateUserPost(principal.getName(), updatedPost);
        log.info("USER [email: " + principal.getName() + "] edit post [id: " + updatedPost.getId() + "]");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Void> deletePost(final Principal principal, @PathVariable("id") Long id) {
        this.postService.removeUserPost(principal.getName(), id);
        log.info("USER [email: " + principal.getName() + "] remove post [id: " + id + "]");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
