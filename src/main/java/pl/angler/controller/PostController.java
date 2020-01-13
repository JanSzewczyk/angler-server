package pl.angler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.angler.dto.PostDto;
import pl.angler.service.PostService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<PostDto>> getPosts(final Principal principal) {
        return new ResponseEntity<>(this.postService.getAllPosts(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/{nick}")
    @PreAuthorize(value = "hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<PostDto>> getPostsByNick(final Principal principal, @PathVariable("nick") String nick) {
        return new ResponseEntity<>(this.postService.getAllUserPosts(principal.getName(), nick), HttpStatus.OK);
    }



}
